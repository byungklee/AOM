package com.anycomp.android.ageofmythology.model.card;

import android.app.AlertDialog;
import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.BuildingDestructionController;
import com.anycomp.android.ageofmythology.BuildingDestructionDialogFragment;
import com.anycomp.android.ageofmythology.BuildingSelectionController;
import com.anycomp.android.ageofmythology.BuildingSelectionDialogFragment;
import com.anycomp.android.ageofmythology.Callback;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;

/**
 * Created by byung on 4/19/15.
 */
public class GodEgyptBuildCard extends RandomBuildCard implements God {

    public GodEgyptBuildCard(Card card) {
        setName("GodEgyptBuild");
        this.card=card;
        setImagePath(R.drawable.card_rand_egypt_build_horus);
        favorCost = 1;
        setValue(3);
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        //card.play(fm, pc);
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            AskGodPowerUseDialogFragment agpud = new AskGodPowerUseDialogFragment();
            agpud.setGod(this);
            agpud.show(fm, "UseGod");

        }
//        if(!isPlayed()) {
//            setPlayed(true);
//            BuildingDestructionController bsc = new BuildingDestructionController(pc);
//            BuildingDestructionDialogFragment bddf = new BuildingDestructionDialogFragment();
//            bddf.setBuildingDestructionController(bsc);
////        BuildingSelectionDialogFragment bsdf = BuildingSelectionDialogFragment.newInstance(bsc);
////        bsdf.setMaxAllowedPick(getValue());
//            bddf.show(fm, "Building destruction Dialog");
//        }
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        this.pc = pc;
        this.fm = fm;
        //card.aiPlay(fm, player);
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
            pc.nextRound();
        }
    }

    @Override
    public void playGod() {
            BuildingDestructionController bsc = new BuildingDestructionController(pc,true);
            BuildingDestructionDialogFragment bddf = new BuildingDestructionDialogFragment();
            bddf.setBuildingDestructionController(bsc);
            bddf.setCallback(new Callback() {
                @Override
                public void callback() {
                    playNormal();
                }
            });
            bddf.show(fm, "Building destruction Dialog");
    }

    @Override
    public void playNormal() {
        BuildingSelectionController bsc = BuildingSelectionController.getInstance(pc);
        bsc.playBuildCard(this);
        BuildingSelectionDialogFragment bsdf = BuildingSelectionDialogFragment.newInstance(bsc);
        bsdf.setMaxAllowedPick(getValue());
        bsdf.show(fm, "Building Selection Dialog");
    }

    @Override
    public boolean payFavor() {
        return pay();
    }
}
