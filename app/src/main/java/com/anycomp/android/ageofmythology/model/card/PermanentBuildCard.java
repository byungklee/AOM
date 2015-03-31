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
    }


    @Override
    public void play(FragmentManager fm, PlayerController pc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        openBuildingPopup(fm,pc);
    }
    int i=0;
    private void openBuildingPopup(FragmentManager fm, PlayerController pc) {
        BuildingSelectionController bsc = BuildingSelectionController.getInstance(pc);
        bsc.playBuildCard(this);
        BuildingSelectionDialogFragment bsdf = BuildingSelectionDialogFragment.newInstance(bsc);
        bsdf.show(fm, "Building Selection Dialog");




    }

}
