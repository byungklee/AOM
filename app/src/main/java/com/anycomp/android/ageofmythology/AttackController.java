package com.anycomp.android.ageofmythology;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.model.unit.Unit;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by byung on 4/6/15.
 */
public class AttackController {

    FragmentManager fm;
    PlayerController pc;
    private int opponentPlayerIndex;
    ArrayList<Unit> attackers;
    ArrayList<Unit> defenders;
    private int numberOfUnitsAllowed;

    //requires turn manager.

    public AttackController(FragmentManager fm, PlayerController pc, int numberOfUnitsAllowed) {
        this.fm = fm;
        this.pc = pc;
        attackers = new ArrayList<>();
        defenders = new ArrayList<>();
        this.numberOfUnitsAllowed = numberOfUnitsAllowed;
    }

    public void startBattle() {
        if(pc.getCurrentPlayer() == pc.getHumanPlayer()) {
            openSelectOpponentDialog();
        } else {
            //AI work

            //Finding a target
            Random random = new Random();
            opponentPlayerIndex = random.nextInt(3);
            while(pc.getCurrentPlayer() == pc.getPlayers().get(opponentPlayerIndex)) {
                opponentPlayerIndex = random.nextInt(3);
            }

            //select units by ai



            //if opponents is humanplayer, let human player picks their defense units
            if(pc.getPlayers().get(opponentPlayerIndex) == pc.getHumanPlayer()) {
                openPickBattleUnitDialog(false);
            } else {
                //AI VS AI

            }

        }
    }

    public void openSelectOpponentDialog() {
        SelectOpponentDialogFragment sodf = new SelectOpponentDialogFragment();
        sodf.setPlayerController(pc);
        sodf.setAfterSelecttOpponentCallback(new AfterSelectOpponentCallback() {
            @Override
            public void callback(int i) {
                opponentPlayerIndex = i;
                openPickBattleUnitDialog(true);
            }
        });
        sodf.show(fm,"Select Opponent Dialog");
    }

    public void openPickBattleUnitDialog(boolean isHumanAttacking) {

        PickBattleUnitDialogFragment pbudf = new PickBattleUnitDialogFragment();
        pbudf.setPlayerController(pc);
        pbudf.setIsHumanAttacking(isHumanAttacking);
        pbudf.setAttackController(this);
        pbudf.setCallback(new Callback() {
            @Override
            public void callback() {
                openBattleSceneDialog();
            }
        });
        pbudf.show(fm,"Pick Battle Unit Dialog");

    }

    public void openBattleSceneDialog() {
        BattleSceneDialogFragment bsdf = new BattleSceneDialogFragment();

        bsdf.setPlayerController(pc);

        bsdf.show(fm,"Battle Scene Dialog");
    }


}
