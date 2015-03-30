package com.anycomp.android.ageofmythology.model.building;

import com.anycomp.android.ageofmythology.R;

/**
 *	Siege Engine Workshop negates the effect of walls and towers,
 * 	and when an attacker wins the battle, it allows the attacker 
 *  to destroy one building in the target city.
 *
 */
public class SiegeEngineWorkshopBuilding extends Building {
    public SiegeEngineWorkshopBuilding() {
        this.setBuildingName("SiegeEngineWorkshop");
        this.setImagePath(R.drawable.siegework);
    }
}
