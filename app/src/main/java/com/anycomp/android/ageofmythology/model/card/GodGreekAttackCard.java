package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.AttackController;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.culture.Culture;

/**
 * Created by byung on 4/19/15.
 */
public class GodGreekAttackCard extends RandomAttackCard implements God {

    public GodGreekAttackCard(Card card) {
        setName("GodGreekAttack");
        this.card = card;
        this.setValue(8);
        setImagePath(R.drawable.card_rand_greek_attack_ares);
        favorCost = 3;
    }
    public Culture getCulture() {
        return card.getCulture();
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
    }

    @Override
    public void playGod() {
        AttackController ac = new AttackController(this, fm, pc, getValue(),true);
        ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());

        ac.startBattle();
    }

    @Override
    public void playNormal() {
        setValue(6);
        AttackController ac = new AttackController(this, fm, pc, getValue(),false);
        ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
        ac.startBattle();
    }

    @Override
    public boolean payFavor() {
        return pay();
    }
}
