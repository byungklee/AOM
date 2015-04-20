package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AfterSelectOpponentCallback;
import com.anycomp.android.ageofmythology.BuildingDestructionController;
import com.anycomp.android.ageofmythology.BuildingDestructionDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.SelectOpponentDialogFragment;

/**
 * Created by byung on 4/19/15.
 */
public class GodNorseBuildCard extends RandomBuildCard implements God {
    public GodNorseBuildCard() {
        setImagePath(R.drawable.card_rand_norse_build_njord);
    }

    @Override
    public void play(final FragmentManager fm, final PlayerController pc) {
//        super.play(fm, pc);
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
        sodf.show(fm,"Select Opponent Dialog");

    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController player) {
//        super.aiPlay(fm, player);
    }
}
