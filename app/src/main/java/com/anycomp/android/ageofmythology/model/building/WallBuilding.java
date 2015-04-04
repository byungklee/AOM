package com.anycomp.android.ageofmythology.model.building;


import com.anycomp.android.ageofmythology.R;

/**
 *  
 * Wall provides a defense for City Area.
 * Effect: a defender gets 2 dice to all encounters during that battle.
 * 			Effect can be negated by an attacker with siege engine building.
 * 
 */
public class WallBuilding extends Building {
    public WallBuilding() {
        this.setBuildingName("Wall");
        this.setImagePath(R.drawable.wall);
        setWoodCost(3);
        setFoodCost(0);
        setGoldCost(3);
        setFavorCost(0);
        setBuildingType(BuildingType.WALL);

    }
}
