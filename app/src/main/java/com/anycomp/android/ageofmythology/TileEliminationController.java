package com.anycomp.android.ageofmythology;

import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;
import com.anycomp.android.ageofmythology.model.tile.ResourceProductionTile;
import com.anycomp.android.ageofmythology.model.tile.Tile;
import com.anycomp.android.ageofmythology.model.tile.TileDecorator;

import java.util.ArrayList;

/**
 * Created by bert on 5/1/15.
 */
public class TileEliminationController {
    private PlayerController pc;
    private Player player;
    private ArrayList<Tile> greekTiles;
    private ArrayList<Integer> greekIndex;
    private ArrayList<Tile> norseTiles;
    private ArrayList<Integer> norseIndex;
    private ArrayList<Tile> egyptianTiles;
    private ArrayList<Integer> egyptianIndex;
    static TileEliminationController instance;

    public static TileEliminationController getInstance(PlayerController pc) {
        if (instance == null)
            instance = new TileEliminationController(pc);
        return instance;
    }

    private TileEliminationController(PlayerController pc) {
        this.pc = pc;
        greekTiles = new ArrayList<>();
        greekIndex = new ArrayList<>();
        norseTiles = new ArrayList<>();
        norseIndex = new ArrayList<>();
        egyptianTiles = new ArrayList<>();
        egyptianIndex = new ArrayList<>();
    }

    public void makeTileList() {
        ArrayList<Tile> tiles;
        int count = 0;
        int size;

        player = pc.getPlayerByCulture("Greek");
        tiles = player.getPlayerBoard().getProductionArea().getTiles();
        size = tiles.size();

        // Extract out the production tiles
        for (int i = 0; i < size; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile) {
                greekTiles.add(tiles.get(i));
                greekIndex.add(i);
            }
        }

        player = pc.getPlayerByCulture("Norse");
        tiles = player.getPlayerBoard().getProductionArea().getTiles();
        size = tiles.size();

        // Extract out the production tiles
        for (int i = 0; i < size; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile) {
                norseTiles.add(tiles.get(i));
                norseIndex.add(i);
            }
        }

        player = pc.getPlayerByCulture("Egypt");
        tiles = player.getPlayerBoard().getProductionArea().getTiles();
        size = tiles.size();

        // Extract out the production tiles
        for (int i = 0; i < size; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile) {
                egyptianTiles.add(tiles.get(i));
                egyptianIndex.add(i);
            }
        }
    }

    public void makeFoodTileList() {
        ArrayList<Tile> tiles;
        int count = 0;
        int size;

        player = pc.getPlayerByCulture("Greek");
        tiles = player.getPlayerBoard().getProductionArea().getTiles();
        size = tiles.size();

        // Extract out the production tiles
        for (int i = 0; i < size; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile
                    && ((ResourceProductionTile) tiles.get(i))
                    .getResourceType() == ResourceType.FOOD) {
                greekTiles.add(tiles.get(i));
                greekIndex.add(i);
            }
        }

        player = pc.getPlayerByCulture("Norse");
        tiles = player.getPlayerBoard().getProductionArea().getTiles();
        size = tiles.size();

        // Extract out the production tiles
        for (int i = 0; i < size; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile
                    && ((ResourceProductionTile) tiles.get(i))
                    .getResourceType() == ResourceType.FOOD) {
                norseTiles.add(tiles.get(i));
                norseIndex.add(i);
            }
        }

        player = pc.getPlayerByCulture("Egypt");
        tiles = player.getPlayerBoard().getProductionArea().getTiles();
        size = tiles.size();

        // Extract out the production tiles
        for (int i = 0; i < size; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile
                    && ((ResourceProductionTile) tiles.get(i))
                    .getResourceType() == ResourceType.FOOD) {
                egyptianTiles.add(tiles.get(i));
                egyptianIndex.add(i);
            }
        }
    }

    public ArrayList<Tile> getGreekTiles() { return greekTiles; }

    public ArrayList<Tile> getNorseTiles() { return norseTiles; }

    public ArrayList<Tile> getEgyptianTiles() { return egyptianTiles; }

    public void greekEliminate(int position) {
        pc.getPlayerByCulture("Greek").getPlayerBoard().getProductionArea()
                .removeTileAt(greekIndex.get(position), ((TileDecorator)
                greekTiles.get(position)).getBasicTile());
    }

    public void norseEliminate(int position) {
        pc.getPlayerByCulture("Norse").getPlayerBoard().getProductionArea()
                .removeTileAt(norseIndex.get(position), ((TileDecorator)
                norseTiles.get(position)).getBasicTile());
    }

    public void egyptianEliminate(int position) {
        pc.getPlayerByCulture("Egypt").getPlayerBoard().getProductionArea()
                .removeTileAt(egyptianIndex.get(position), ((TileDecorator)
                egyptianTiles.get(position)).getBasicTile());
    }
}
