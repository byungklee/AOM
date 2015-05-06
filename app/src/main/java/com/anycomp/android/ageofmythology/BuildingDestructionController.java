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
    int currCount = 0;
    boolean isGodBuild = false;
    int maxTotal;
    public BuildingDestructionController(PlayerController pc, boolean isGodBuild, int maxTotal) {
        this.pc = pc;
        this.isGodBuild = isGodBuild;
        this.maxTotal = maxTotal;
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
        System.out.println("maxTotal " + maxTotal + " currCount "  + currCount );
        if(maxTotal > currCount) {
            if (targetPlayerIndex == -1) {
                if(((CityArea) pc.getCurrentPlayer().getPlayerBoard().getCityArea()).getNumberOfBuilding() ==0) {
                    return;
                }
                ((CityArea) pc.getCurrentPlayer().getPlayerBoard().getCityArea()).destroyBuilding(index);
            } else {
                if(((CityArea)((Player)pc.getPlayers().get(targetPlayerIndex)).getPlayerBoard().getCityArea()).getNumberOfBuilding() ==0) {
                    return;
                }
                System.out.println("DESTORY BUILDING");
                Player p = (Player) pc.getPlayers().get(targetPlayerIndex);
                ((CityArea) p.getPlayerBoard().getCityArea()).destroyBuilding(index);
            }
            currCount++;
        }
    }
    public boolean isGodBuild() {
        return isGodBuild;
    }
    public void nextRound() {
        pc.nextRound();
    }
}
