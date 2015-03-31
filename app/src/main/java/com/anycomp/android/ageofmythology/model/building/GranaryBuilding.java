package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * Granary allows the owning player 2 extra Food cubes each time
 * a player performs a Gather action.
 *
 */
public class GranaryBuilding extends Building {
    public GranaryBuilding() {
        this.setBuildingName("Granary");
        this.setImagePath(R.drawable.granary);
        setWoodCost(2);
        setFoodCost(0);
        setGoldCost(3);
        setFavorCost(0);
    }
}
