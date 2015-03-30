package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * For each house, the player gains one new Villager.
 * New villagers are placed in their owner's Holding Area until 
 * the player plays a Gather action.
 * If a house is a destroyed, one villager must be removed.
 *
 */
public class HouseBuilding extends Building {
    public HouseBuilding() {
        this.setBuildingName("House");
        this.setImagePath(R.drawable.house);
    }
}	
