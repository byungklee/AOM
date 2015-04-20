package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

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
        card.setValue(8);
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        //TO DO: cost of 3 cubes

        card.play(fm,pc);
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        card.aiPlay(fm,pc);
    }
}
