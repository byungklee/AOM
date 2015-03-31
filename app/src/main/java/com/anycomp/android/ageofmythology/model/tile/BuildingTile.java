package com.anycomp.android.ageofmythology.model.tile;

import com.anycomp.android.ageofmythology.model.building.Building;

/**
 * Building Tile allows you to place a building.
 */
public class BuildingTile extends Tile {
    private Building building;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
    public int getImagePath() {
        if(building == null) {
            return 0;
        }
        return building.getImagePath();
    }


}
