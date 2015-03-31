package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * 
 * @author byung
 *
 */
public class ArmoryBuilding extends Building {

    public ArmoryBuilding() {
        this.setBuildingName("Armory");
        this.setImagePath(R.drawable.armory);
        setWoodCost(3);
        setFoodCost(0);
        setGoldCost(2);
        setFavorCost(0);

    }
}
