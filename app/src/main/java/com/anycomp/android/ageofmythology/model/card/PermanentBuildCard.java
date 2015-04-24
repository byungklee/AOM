package com.anycomp.android.ageofmythology.model.card;


import android.app.FragmentManager;
import android.content.Context;

import com.anycomp.android.ageofmythology.BuildingSelectionController;
import com.anycomp.android.ageofmythology.BuildingSelectionDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentBuildCard extends PermanentActionCard {
    
    public PermanentBuildCard(Culture culture) {
         setName("Build");
        setCulture(culture);
        setImagePath(culture.getPermanentBuildCardImage());
        setValue(2);
    }


    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
                setPlayed(true);
                openBuildingPopup(fm, pc);
        }
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            setPlayed(true);
            BuildingSelectionController bsc = BuildingSelectionController.getInstance(pc);
            //bsc.
            for (int j = 0; j < getValue(); j++) {
                for (int i = 0; i < bsc.getBuildingList().size(); i++) {
                    if (bsc.verifyAvailability(i) && bsc.verifyResource(i)) {
                        bsc.addBuilding(i);
                    }
                }
            }

        }
        pc.nextRound();
    }


    private void openBuildingPopup(FragmentManager fm, PlayerController pc) {
        BuildingSelectionController bsc = BuildingSelectionController.getInstance(pc);
        bsc.playBuildCard(this);
        BuildingSelectionDialogFragment bsdf = BuildingSelectionDialogFragment.newInstance(bsc);
        bsdf.setMaxAllowedPick(getValue());
        bsdf.show(fm, "Building Selection Dialog");
    }

}
