package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * Quarry reduces the building cost of all future buildings by one 
 * resource cube. This effect takes effect starting on the action after
 * the purchase of the quarry.
 *
 */
public class QuarryBuilding extends Building {
 public QuarryBuilding() {
     this.setBuildingName("Quarry");
        this.setImagePath(R.drawable.quarry);
     setWoodCost(0);
     setFoodCost(4);
     setGoldCost(1);
     setFavorCost(0);
     setBuildingType(BuildingType.QUARRY);

 }
}
