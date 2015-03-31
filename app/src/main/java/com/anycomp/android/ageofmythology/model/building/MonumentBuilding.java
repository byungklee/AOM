package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * Monument grants the owning player 2 extra Favor cubes each time
 * the player performs a Gather action.
 *
 */
public class MonumentBuilding extends Building{
    public MonumentBuilding() {
        this.setBuildingName("Monument");
        this.setImagePath(R.drawable.monument);
        setWoodCost(0);
        setFoodCost(3);
        setGoldCost(2);
        setFavorCost(0);
    }
}
