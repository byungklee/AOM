package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 * Great Temple allows the owner to trade one Victory point
 * for every 8 favor(blue) cubes. A player must perform a Trade action.
 *
 */
public class GreatTempleBuilding extends Building {
    public GreatTempleBuilding() {
        this.setBuildingName("GreatTemple");
        this.setImagePath(R.drawable.greattemple);
        setWoodCost(4);
        setFoodCost(4);
        setGoldCost(4);
        setFavorCost(4);
        setBuildingType(BuildingType.GREAT_TEMPLE);

    }
}
