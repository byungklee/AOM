package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * Market ignores the resource cost when a Trade card is played.
 */
public class MarketBuilding extends Building {
    public MarketBuilding() {
        this.setBuildingName("Market");
        this.setImagePath(R.drawable.market);
        setWoodCost(0);
        setFoodCost(0);
        setGoldCost(3);
        setFavorCost(2);
    }
}
