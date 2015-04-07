package com.anycomp.android.ageofmythology;

import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.tile.Tile;
import com.anycomp.android.ageofmythology.model.tile.TileDecorator;
import com.anycomp.android.ageofmythology.model.tile.TileType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by byung on 3/29/15.
 */
public class TileSelectionController {
    private PlayerController pc;
    private HashMap<String, Integer> playerInfo;
   // private HashMap<Integer, Tile> tileInfo;
    private ArrayList<Tile> tileSelectionDeck;
    private int maxPick;

    public TileSelectionController(PlayerController pc, int maxPick) {
        this.pc = pc;
        this.maxPick = maxPick;
        playerInfo = new HashMap<String, Integer>();
        playerInfo.put("user", 0);
        playerInfo.put("AI1", 0);
        playerInfo.put("AI2", 0);
        tileSelectionDeck = TileManager.getInstance().getTileSelectionDeck();
//        tileInfo = new HashMap<Integer, Tile>();
//        for(int i=0;i<al.size();i++) {
//            tileInfo.put(i,al.get(i));
//        }
        if(getPlayerController().getCurrentPlayer().getName().contains("AI")) {
            aiPickTile();
           // nextPlayer();
        }
    }

    public void execute(int pick) {
//        if(pick)
//        Player p = pc.getCurrentPlayer();
//        String currentPlayerName = p.getName();
//        playerInfo.get(currentPlayerName);
        if(tileSelectionDeck.get(pick) == null) {
            return;
        }

        String playerName = pc.getCurrentPlayer().getName();

        Tile t = tileSelectionDeck.get(pick);
        //		System.out.println("Clicked Geo " + geo.getName() + " " + t.getTileType() + t.getImagePath() );
        //t.print();
        // TODO Auto-generated method stub
        //		String playerName = main.getCurrentPlayer().getName();
        System.out.println("MAX_SIZE: " + maxPick);
        if (playerInfo.get(playerName) > maxPick -1) {

            playerInfo.put(playerName, playerInfo.get(playerName) + 1);
            if (isAllResourceTilePlaced()) {
                //goToNextState(controller);
                // controller.setCurrentPlayer();
                //turnOff();

                return;
            }
            nextPlayer();
            System.out.println("picked already over the max size ");
            return;
        }


        // controller.getCurrentPlayer().getName();
        // System.out.println(playerInfo.get(geo.getName()) + geo.getName());

        ArrayList<Tile> productionTiles = pc.getCurrentPlayer()
                .getPlayerBoard().getProductionArea().getTiles();

        TileManager tManager = TileManager.getInstance();
        Tile selectedTile = tileSelectionDeck.get(pick);

        boolean taken = false;
        for (int i = 0; i < productionTiles.size(); i++) {
            Tile tile = productionTiles.get(i);
            System.out.println(tile);
            if (!(tile instanceof TileDecorator)
                    && tile.getTileType() == selectedTile.getTileType()) {
                //productionTiles.set(i, selectedTile);
                pc.getCurrentPlayer().getPlayerBoard().getProductionArea().setTileAt(i, selectedTile);
                //(temp);
                //TileManager.getInstance().takeTile(selectedTile.getName());
                tManager.removeTileFromTileSelectionDeck(selectedTile);
//                tileInfo.put(pick, null);
                System.out.println("b4 setting it to invisible. " + pick);


//                if(nifty.findPopupByName("TileSelectionPopup") == null) {
//                    System.out.println("this is null....");
//                }
//                if(screen == null) {
//                    System.out.println("screen is null...");
//                }
//                screen.findElementByName("tile"+pick).setVisible(false);

                System.out.println("after setting it to invisible");
                taken =true;
                break;
            }
        }
        if(!taken) {
            //Show you don't have matching tile
            System.out.println("No matching tile");
            //playerInfo.put(playerName, playerInfo.get(playerName) - 1);
            return;
        }
        playerInfo.put(playerName, playerInfo.get(playerName) + 1);
        // controller.getPlayerController().pickTerrainTile(controller.getCurrentPlayer(),
        // geo);
        //
        // //Checking if all players got resources
        if (isAllResourceTilePlaced()) {
//            turnOff();
            return;
        }
        //
        // //If the selection deck requires new deck, refresh the deck.
        // if(TileManager.getInstance().getTileSelectionDeck().isEmpty()) {
        // TileManager.getInstance().refreshTileSelectionDeck();
        // }

        nextPlayer();
    }

    public boolean isAllResourceTilePlaced() {
        System.out.println(playerInfo.get("user") + " " + playerInfo.get("AI1") + " " + playerInfo.get("AI2"));
        if(playerInfo.get("AI1") >= maxPick  &&
                playerInfo.get("AI2") >= maxPick  &&
                playerInfo.get("user") >= maxPick ) {

            return true;
        }
        return false;
    }

    public void pass() {
        String playerName = pc.getCurrentPlayer().getName();

            playerInfo.put(playerName, playerInfo.get(playerName) + 1);
            if (isAllResourceTilePlaced()) {
      //          turnOff();
                //turn off tile popup
                return;
            }
            System.out.println("Nothing to choose or already picked so passing ");
            nextPlayer();

        }

    public void nextPlayer() {
//            System.out.println("to next player ");

    //    Player p = pc.getNextPlayerForTileSelection();
        if(maxPick < 6) {
            pc.getNextPlayerForTileSelectionLinear();
        } else {
            pc.getNextPlayerForTileSelection();
        }
        System.out.println("to next player " + pc.getCurrentPlayer().getName());
        //System.out.println("CurrentPlayer = " + currentPlayer.getName());
        if(pc.getCurrentPlayer().getName().contains("AI")) {
            aiPickTile();
        }
    }

    public boolean isTileAvailable(Tile tile) {
        ArrayList<Tile> productionTiles = pc.getCurrentPlayer()
                .getPlayerBoard().getProductionArea().getTiles();

        for(int i=0;i<productionTiles.size();i++) {
            Tile t = productionTiles.get(i);

            if (!(t instanceof TileDecorator)
                    && t.getTileType() == tile.getTileType()) {
                return true;
            }
        }

        return false;
    }

    private void aiPickTile() {
        System.out.println(pc.getCurrentPlayer().getName() + " is playing");
        //TileType tileType = pc.getCurrentPlayer().getPlayerBoard().getProductionArea().getFirstAvailableTile().getTileType();
        //Tile tile = TileManager.getInstance().getFirstAvailableTileByTileType(tileType);
        //ArrayList al = TileManager.getInstance().getTileSelectionDeck();
        boolean isExecuted = false;
        for(int i=0; i<tileSelectionDeck.size();i++ ) {
            if(isTileAvailable(tileSelectionDeck.get(i))) {
                execute(i);
                isExecuted=true;
                break;
            }
        }
        if(!isExecuted) {
            pass();
        }

//        if(tile == null) {
//            pass();
//        } else {
//            execute(tileSelectionDeck.indexOf(tile));
//        }
        //System.out.println("AI Chooses");
    }

    public void nextRound() {
        pc.nextRound();
    }

    public PlayerController getPlayerController() {
        return pc;
    }

}
