package com.anycomp.android.ageofmythology;

import com.anycomp.android.ageofmythology.model.area.CityArea;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.tile.BuildingTile;
import com.anycomp.android.ageofmythology.model.tile.Tile;

import java.util.ArrayList;

/**
 * Created by byung on 4/19/15.
 */
public class BuildingDestructionController {
    PlayerController pc;
    int targetPlayerIndex = -1;
    public BuildingDestructionController(PlayerController pc) {
        this.pc = pc;
    }

    public void setTargetPlayer(int i) {
        targetPlayerIndex = i;
    }
    public ArrayList<Tile> getBuildingList() {
        if(targetPlayerIndex != -1) {
            return ((Player)pc.getPlayers().get(targetPlayerIndex)).getPlayerBoard().getCityArea().getTiles();
        }
        return pc.getCurrentPlayer().getPlayerBoard().getCityArea().getTiles();
    }

    public void destroyBuilding(int index) {
        if(targetPlayerIndex == -1) {
            ((CityArea) pc.getCurrentPlayer().getPlayerBoard().getCityArea()).destroyBuilding(index);
        } else
            ((Player)pc.getPlayers().get(targetPlayerIndex)).getPlayerBoard().getCityArea().getTiles();
    }
    public void nextRound() {
        pc.nextRound();
    }
}
