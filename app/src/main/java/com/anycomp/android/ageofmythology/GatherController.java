package com.anycomp.android.ageofmythology;

import com.anycomp.android.ageofmythology.model.bank.Bank;
import com.anycomp.android.ageofmythology.model.building.Building;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.building.GoldMintBuilding;
import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;
import com.anycomp.android.ageofmythology.model.tile.ResourceProductionTile;
import com.anycomp.android.ageofmythology.model.tile.Tile;
import com.anycomp.android.ageofmythology.model.tile.TileType;

import java.util.ArrayList;

/**
 * Created by bert on 4/6/15.
 */
public class GatherController {
    private PlayerController pc;
    private ArrayList<Object> list;
    private static GatherController instance;
    private Card card;
    private int pick;

    public static GatherController getInstance(PlayerController pc) {
        if(instance == null) {
            instance = new GatherController(pc);
        }
        instance.setPlayerController(pc);
        return instance;
    }

    private GatherController(PlayerController pc) {
        TileType[] tts = TileType.values();
        ResourceType[] rts = ResourceType.values();
        int length = tts.length + rts.length;

        this.pc = pc;
        pick = 0;
        list = new ArrayList<>();

        for (int i = 1; i < length; i++) {
            if (i < tts.length)
                list.add(tts[i]);
            else
                list.add(rts[i - tts.length]);
        }
    }

    public PlayerController getPlayerController() { return pc; }
    public void setPlayerController(PlayerController pc) { this.pc = pc; }

    public ArrayList<Object> getList() { return list; }

    public void setPick(int pick) { this.pick = pick; }

    public void playGatherCard(Card card) { this.card = card; }

    public void nextRound() {
        pc.nextRound();
    }

    public void gather(boolean played_card) {
        ResourceType resource = null;
        TileType terrain = null;
        TileType[] tts = TileType.values();
        Bank bank = Bank.getInstance();
        Player player = pc.getCurrentPlayer();
        ArrayList<Tile> tiles = player.getPlayerBoard().getProductionArea()
                .getTiles();
        int numTiles = tiles.size();
        //ArrayList<Tile> cityTiles = player.getPlayerBoard().getCityArea().getTiles();
        //int citySize = cityTiles.size();

        if (pick < tts.length - 1)
            terrain = (TileType) list.get(pick);
        else
            resource = (ResourceType) list.get(pick);

        for (int i = 0; i < numTiles; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile
                    && (tiles.get(i).getTileType() == terrain && terrain
                    != null || ((ResourceProductionTile) tiles.get(i))
                    .getResourceType() == resource && resource != null)) {
                switch(((ResourceProductionTile) tiles.get(i))
                        .getResourceType()) {
                    case WOOD:
                        player.takeWood(
                                ((ResourceProductionTile) tiles.get(i))
                                .getResourceNumber());
                        break;
                    case GOLD:
                        player.takeGold(
                                ((ResourceProductionTile) tiles.get(i))
                                .getResourceNumber());
                        break;
                    case FOOD:
                        player.takeFood(
                                ((ResourceProductionTile) tiles.get(i))
                                .getResourceNumber());
                        break;
                    case FAVOR:
                        player.takeFavor(
                                ((ResourceProductionTile) tiles.get(i))
                                .getResourceNumber());
                        break;
                }
            }
        }

        // Check if if the player played the gather card
        if (played_card) {
            // Check for a Monument
            if (player.hasBuilding(BuildingType.MONUMENT))
                player.takeFavor(2);

            // Check for a Gold Mint
            if (player.hasBuilding(BuildingType.GOLD_MINT))
                player.takeGold(2);

            // Check for a Granary
            if (player.hasBuilding(BuildingType.GRANARY))
                player.takeFood(2);

            // Check for a Wood Workshop
            if (player.hasBuilding(BuildingType.WOOD_WORKSHOP))
                player.takeWood(2);
        }
    }
}
