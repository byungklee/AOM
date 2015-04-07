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
  //  private int quantity;
    public HouseBuilding() {
        this.setBuildingName("House");
        this.setImagePath(R.drawable.house);
//        quantity = 0;
        setWoodCost(2);
        setFoodCost(2);
        setGoldCost(0);
        setFavorCost(0);
        setBuildingType(BuildingType.HOUSE);

    }


//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
}
