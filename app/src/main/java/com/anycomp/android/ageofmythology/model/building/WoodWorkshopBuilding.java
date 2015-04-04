package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * Wood Workshop grants the owning player 2 extra Wood cubes each time,
 * the player performs Gather action. 
 *
 */
public class WoodWorkshopBuilding extends Building {
    public WoodWorkshopBuilding() {
        this.setBuildingName("WoodWorkshop");
        this.setImagePath(R.drawable.woodwork);
        setWoodCost(0);
        setFoodCost(2);
        setGoldCost(3);
        setFavorCost(0);
        setBuildingType(BuildingType.WOOD_WORKSHOP);

    }
}
