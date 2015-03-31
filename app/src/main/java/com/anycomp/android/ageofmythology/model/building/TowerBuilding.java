package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 *  
 * Tower provides a defense for Production Area.
 * Effect: a defender gets 2 dice to all encounters during that battle.
 * 			Effect can be negated by an attacker with siege engine building.
 * 
 */
public class TowerBuilding extends Building {
    public TowerBuilding() {
        this.setBuildingName("Tower");
        this.setImagePath(R.drawable.tower);
        setWoodCost(3);
        setFoodCost(0);
        setGoldCost(3);
        setFavorCost(0);
    }
}
