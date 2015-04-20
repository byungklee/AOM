package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;
import android.content.Context;

import com.anycomp.android.ageofmythology.AfterSelectOpponentCallback;
import com.anycomp.android.ageofmythology.AttackController;
import com.anycomp.android.ageofmythology.PickBattleUnitDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.SelectOpponentDialogFragment;
import com.anycomp.android.ageofmythology.model.culture.Culture;



public class PermanentAttackCard extends PermanentActionCard {
    public PermanentAttackCard(Culture culture) {
       setName("Attack");
        setCulture(culture);

        setImagePath(culture.getPermanentAttackCardImage());
        setValue(4);
    }


    @Override
    public void play(FragmentManager fm, PlayerController pc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        //Pick opponent
        //Pick units
        //open battle
        //do action until end
        //finish

//        pc.nextRound();
        if(!isPlayed()) {
            setPlayed(true);
            AttackController ac = new AttackController(this, fm, pc, getValue());
            ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
            ac.startBattle();
        }
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            setPlayed(true);
            AttackController ac = new AttackController(this, fm, pc, getValue());
            ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
            ac.startBattle();
        }
    }

}
