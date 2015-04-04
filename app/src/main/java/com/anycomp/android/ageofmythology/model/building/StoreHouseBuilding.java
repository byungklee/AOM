package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * Store House increases the maximum capacity to 8 for each resource cubes.
 *
 */
public class StoreHouseBuilding extends Building {
    public StoreHouseBuilding() {
        this.setBuildingName("StoreHouse");
        this.setImagePath(R.drawable.storehouse);
        setWoodCost(2);
        setFoodCost(2);
        setGoldCost(2);
        setFavorCost(2);
        setBuildingType(BuildingType.STOREHOUSE);
    }
}
