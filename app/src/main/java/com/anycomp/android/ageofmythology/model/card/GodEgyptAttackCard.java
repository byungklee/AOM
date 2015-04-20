package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AttackController;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.culture.Culture;

/**
 * Created by byung on 4/19/15.
 * Implementation of sekhmet
 * 8 unit 6 oppo
 * 3 cubes
 */
public class GodEgyptAttackCard extends RandomAttackCard implements God {

    public GodEgyptAttackCard(PermanentAttackCard card) {
        this.card=card;
        setImagePath(R.drawable.card_rand_egypt_attack_sekhmet);
        setValue(8);
    }

    public Culture getCulture() {
        return card.getCulture();
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        //TO DO: cost of 3 cubes
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
