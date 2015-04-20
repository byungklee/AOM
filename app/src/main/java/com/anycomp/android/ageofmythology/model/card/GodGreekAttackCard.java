package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AttackController;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.culture.Culture;

/**
 * Created by byung on 4/19/15.
 */
public class GodGreekAttackCard extends RandomAttackCard implements God {

    public GodGreekAttackCard(Card card) {
        this.card = card;
        this.setValue(8);
        setImagePath(R.drawable.card_rand_greek_attack_ares);
    }
    public Culture getCulture() {
        return card.getCulture();
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
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
