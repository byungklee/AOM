package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.BuildingDestructionController;
import com.anycomp.android.ageofmythology.BuildingDestructionDialogFragment;
import com.anycomp.android.ageofmythology.BuildingSelectionController;
import com.anycomp.android.ageofmythology.BuildingSelectionDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;

/**
 * Created by byung on 4/19/15.
 */
public class GodEgyptBuildCard extends RandomBuildCard implements God {

    public GodEgyptBuildCard(Card card) {
        this.card=card;
        setImagePath(R.drawable.card_rand_egypt_build_horus);
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        //card.play(fm, pc);
        if(!isPlayed()) {
            setPlayed(true);
            BuildingDestructionController bsc = new BuildingDestructionController(pc);
            BuildingDestructionDialogFragment bddf = new BuildingDestructionDialogFragment();
            bddf.setBuildingDestructionController(bsc);
//        BuildingSelectionDialogFragment bsdf = BuildingSelectionDialogFragment.newInstance(bsc);
//        bsdf.setMaxAllowedPick(getValue());
            bddf.show(fm, "Building destruction Dialog");
        }
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController player) {

        //card.aiPlay(fm, player);
        player.nextRound();

    }
}
