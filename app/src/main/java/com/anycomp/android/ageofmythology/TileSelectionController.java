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
    private boolean select2more;
    private boolean onlyPlayerPick;
    private int selectedCounter;
    private boolean onlyOnePlayerPlayed = false;
    private int currentPlayerIndex;
    public TileSelectionController(PlayerController pc, int maxPick) {
        this.pc = pc;
        this.maxPick = maxPick;
        playerInfo = new HashMap<String, Integer>();
        playerInfo.put("user", 0);
        playerInfo.put("AI1", 0);
        playerInfo.put("AI2", 0);
        tileSelectionDeck = TileManager.getInstance().getTileSelectionDeck();
        select2more = false;
        onlyPlayerPick = false;
        selectedCounter = 0;
        if(getPlayerController().getCurrentPlayer().getName().contains("AI")) {
             aiPickTile();
        }
        this.currentPlayerIndex = pc.getCurrentPlayerID();
    }

    public TileSelectionController(PlayerController pc, int maxPick, boolean onlyPlayerPick, boolean select2more) {
        this.pc = pc;
        this.maxPick = maxPick;
        playerInfo = new HashMap<String, Integer>();
        playerInfo.put("user", 0);
        playerInfo.put("AI1", 0);
        playerInfo.put("AI2", 0);
        tileSelectionDeck = TileManager.getInstance().getTileSelectionDeck();
        this.select2more = select2more;
        this.onlyPlayerPick = onlyPlayerPick;
        selectedCounter = 0;

        if(getPlayerController().getCurrentPlayer().getName().contains("AI")) {
            if(select2more) {
                aiPickTile();
                aiPickTile();
                aiPickTile();
            } else if(onlyPlayerPick) {
                aiPickTile();
            }
        }
        this.currentPlayerIndex = pc.getCurrentPlayerID();

    }


    public void execute(int pick) {
        if(tileSelectionDeck.get(pick) == null) {
            System.out.println("Your selection in tile is null "  + tileSelectionDeck.size());
            return;
        }
        String playerName = pc.getCurrentPlayer().getName();
        System.out.println("MAX_SIZE: " + maxPick);
        if (playerInfo.get(playerName) > maxPick+(select2more && pc.getTurnManager().getCurrentPlayer() == pc.getCurrentPlayerID() ? 2 : 0) - 1 || onlyOnePlayerPlayed) {
            playerInfo.put(playerName, playerInfo.get(playerName) + 1);
            if (isAllResourceTilePlaced()) {
                return;
            }
            nextPlayer();
            System.out.println("picked already over the max size ");
            return;
        }

        ArrayList<Tile> productionTiles = pc.getCurrentPlayer()
                .getPlayerBoard().getProductionArea().getTiles();

        TileManager tManager = TileManager.getInstance();
        Tile selectedTile = tileSelectionDeck.get(pick);

        boolean taken = false;
        for (int i = 0; i < productionTiles.size(); i++) {
            Tile tile = productionTiles.get(i);
            //System.out.println("tileLeft " + tile.getTileType() + "  tileRight " + selectedTile.getTileType() );
            if (!(tile instanceof TileDecorator)
                    && tile.getTileType() == selectedTile.getTileType()) {
                pc.getCurrentPlayer().getPlayerBoard().getProductionArea().setTileAt(i, selectedTile);
                tManager.removeTileFromTileSelectionDeck(selectedTile);
                taken =true;
                break;
            }
        }
        if(!taken) {
            //Show you don't have matching tile
            System.out.println("No matching tile");
            return;
        }
        playerInfo.put(playerName, playerInfo.get(playerName) + 1);

        // //Checking if all players got resources
        if (isAllResourceTilePlaced()) {
            return;
        }
        if(onlyPlayerPick) {
            onlyOnePlayerPlayed = true;
        }
        if(select2more) {
            selectedCounter++;
        }
        if(!select2more || (select2more && selectedCounter >= 3)) {
            nextPlayer();
        }
    }

    public boolean isAllResourceTilePlaced() {
        System.out.println(playerInfo.get("user") + " " + playerInfo.get("AI1") + " " + playerInfo.get("AI2"));
        if(playerInfo.get("AI1") >= maxPick  &&
                playerInfo.get("AI2") >= maxPick  &&
                playerInfo.get("user") >= maxPick || onlyOnePlayerPlayed ) {

            return true;
        }
        return false;
    }

    public void pass() {
        String playerName = pc.getCurrentPlayer().getName();

            playerInfo.put(playerName, playerInfo.get(playerName) + 1);
            if (isAllResourceTilePlaced()) {
                //turn off tile popup
                return;
            }
            System.out.println("Nothing to choose or already picked so passing ");
            if(onlyPlayerPick) {
                onlyOnePlayerPlayed = true;
            }
            if(select2more) {
                selectedCounter++;
            }
            if(!select2more || (select2more && selectedCounter >= 3) ) {
                nextPlayer();
            }
        }

    public void nextPlayer() {
        if(maxPick < 6) {
            pc.getNextPlayerForTileSelectionLinear();
        } else {
            pc.getNextPlayerForTileSelection();
        }
        System.out.println("to next player " + pc.getCurrentPlayer().getName());

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
    }

    public void nextRound() {
        pc.setCurrentPlayer(currentPlayerIndex);
        pc.nextRound();
    }

    public PlayerController getPlayerController() {
        return pc;
    }

}
