package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AfterSelectOpponentCallback;
import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.BuildingDestructionController;
import com.anycomp.android.ageofmythology.BuildingDestructionDialogFragment;
import com.anycomp.android.ageofmythology.BuildingSelectionController;
import com.anycomp.android.ageofmythology.BuildingSelectionDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.SelectOpponentDialogFragment;

/**
 * Created by byung on 4/19/15.
 */
public class GodNorseBuildCard extends RandomBuildCard implements God {
    public GodNorseBuildCard() {
        setName("GodNorseBuild");
        setImagePath(R.drawable.card_rand_norse_build_njord);
        favorCost = 1;
        setValue(4);
    }

    @Override
    public void play(final FragmentManager fm, final PlayerController pc) {
//        super.play(fm, pc);
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            AskGodPowerUseDialogFragment agpud = new AskGodPowerUseDialogFragment();
            agpud.setGod(this);
            agpud.show(fm, "UseGod");
        }

    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
//        super.aiPlay(fm, player);
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            if (payFavor()) {
                BuildingDestructionController bdc = new BuildingDestructionController(pc);
                int targetId = (pc.getCurrentPlayerID() + 1) % 3;
                bdc.setTargetPlayer(targetId);
                bdc.destroyBuilding(0);
            } else {
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

        }
        pc.nextRound();

    }

    @Override
    public void playGod() {
        SelectOpponentDialogFragment sodf = new SelectOpponentDialogFragment();
        sodf.setPlayerController(pc);
        sodf.setAfterSelecttOpponentCallback(new AfterSelectOpponentCallback() {
            @Override
            public void callback(int i) {
//                opponentPlayerIndex = i;
                //openPickBattleUnitDialog(true);
//                openAttackAreaDialog();
//                isHumanAttacking = true;
                BuildingDestructionController bdc = new BuildingDestructionController(pc);
                bdc.setTargetPlayer(i);
                BuildingDestructionDialogFragment bddf = new BuildingDestructionDialogFragment();
                bddf.setBuildingDestructionController(bdc);
                bddf.show(fm, "Destroy Building");
            }
        });
        sodf.show(fm, "Select Opponent Dialog");
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
