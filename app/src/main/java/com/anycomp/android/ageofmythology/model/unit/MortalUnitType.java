package com.anycomp.android.ageofmythology.model.unit;

import com.anycomp.android.ageofmythology.R;

/**
 * Contains a reference to the image to use for each mortal unit
 * Created by mike on 4/4/15.
 */
public enum MortalUnitType {
    //EGYPTIAN
    SPEARMAN(R.drawable.unit_spearman, 0, 1, 0, 1),
    ELEPHANT(R.drawable.unit_elephant, 0, 2, 1, 0),
    CHARIOT_ARCHER(R.drawable.unit_chariot_archer, 0, 0, 1, 1),


    //GREEK
    TOXOTE(R.drawable.unit_toxote, 0, 1, 0, 1),
    HOPLITE(R.drawable.unit_hoplite, 0, 1, 0, 1),
    HIPPOKON(R.drawable.unit_hippokon, 0, 1, 1, 0),

    //NORSE
    JARL(R.drawable.unit_jarl, 0, 1, 1, 0),
    HUSKARL(R.drawable.unit_huskarl, 0, 1, 2, 0),
    THROWING_AXEMAN(R.drawable.unit_throwing_axeman, 0, 1, 0, 1);

    int imageID;
    int favorCost;
    int foodCost;
    int goldCost;
    int woodCost;

    private MortalUnitType(int imageID,  int favorCost, int foodCost, int goldCost, int woodCost) {
        this.imageID = imageID;
        this.favorCost = favorCost;
        this.foodCost = foodCost;
        this.goldCost = goldCost;
        this.woodCost = woodCost;
    }

}
