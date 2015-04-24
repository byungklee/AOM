package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.AttackController;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;

/**
 * Created by byung on 4/19/15.
 */
public class GodNorseAttackCard extends RandomAttackCard implements God {

    public GodNorseAttackCard(Card card) {
        setName("GodNorseAttack");
        this.card = card;
        setImagePath(R.drawable.card_rand_norse_attack_bragi);
        setValue(6);
        favorCost = 2;
    }
    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            AskGodPowerUseDialogFragment agpud = new AskGodPowerUseDialogFragment();
            agpud.setGod(this);
            agpud.show(fm, "UseGod");
//            AttackController ac = new AttackController(this, fm, pc, getValue(),true);
//            ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
//            ac.setBragiEffect(1);
//            ac.startBattle();
        }

    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            if(payFavor()) {
                playGod();
            } else {
                playNormal();
            }
        }

//
//        if(!isPlayed()) {
//            setPlayed(true);
//            AttackController ac = new AttackController(this, fm, pc, getValue(),true);
//            ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
//            ac.setBragiEffect(1);
//            ac.startBattle();
//        }
    }

    @Override
    public void playGod() {

            AttackController ac = new AttackController(this, fm, pc, getValue(),true);
            ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
            ac.setBragiEffect(1);
            ac.startBattle();

    }

    @Override
    public void playNormal() {
        AttackController ac = new AttackController(this, fm, pc, getValue(),false);
        ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
        ac.startBattle();
    }
    @Override
    public boolean payFavor() {
        return pay();
    }
}
