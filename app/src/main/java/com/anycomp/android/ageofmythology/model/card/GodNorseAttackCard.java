package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AttackController;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;

/**
 * Created by byung on 4/19/15.
 */
public class GodNorseAttackCard extends RandomAttackCard implements God {

    public GodNorseAttackCard(Card card) {
        this.card = card;
        setImagePath(R.drawable.card_rand_norse_attack_bragi);
        setValue(6);
    }
    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            setPlayed(true);
            AttackController ac = new AttackController(this, fm, pc, getValue());
            ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
            ac.setBragiEffect(1);
            ac.startBattle();
        }

    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            setPlayed(true);
            AttackController ac = new AttackController(this, fm, pc, getValue());
            ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
            ac.setBragiEffect(1);
            ac.startBattle();
        }
    }
}
