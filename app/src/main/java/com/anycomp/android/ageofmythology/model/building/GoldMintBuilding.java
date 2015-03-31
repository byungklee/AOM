package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * Gold Mint grants 2 extra Gold cubes each time a player performs
 * a Gather action.
 *
 */
public class GoldMintBuilding extends Building{
    public GoldMintBuilding() {
        this.setBuildingName("GoldMint");
        this.setImagePath(R.drawable.goldmint);
        setWoodCost(2);
        setFoodCost(3);
        setGoldCost(0);
        setFavorCost(0);
    }
}
