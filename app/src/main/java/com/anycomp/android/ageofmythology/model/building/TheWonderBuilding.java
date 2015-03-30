package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * 
 * The Wonder allows the game to be end.
 * A player must be in the Mythic age.
 * The player obtains all Victory point on "The Wonder" card.
 *
 */
public class TheWonderBuilding extends Building {
    public TheWonderBuilding() {
        this.setBuildingName("TheWonder");
        this.setImagePath(R.drawable.wonder);
    }
}
