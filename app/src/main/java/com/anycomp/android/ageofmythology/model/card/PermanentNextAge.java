package com.anycomp.android.ageofmythology.model.card;


import android.app.FragmentManager;
import android.content.Context;

import com.anycomp.android.ageofmythology.NextAgeController;
import com.anycomp.android.ageofmythology.NextAgeDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentNextAge extends PermanentActionCard {

    public PermanentNextAge(Culture culture) {
         setName("Age");
        setCulture(culture);
        setImagePath(culture.getPermanentNextCardImage());
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            setPlayed(true);
            NextAgeController nac = NextAgeController.getInstance(pc);
            nac.playNextAgeCard(this);
            NextAgeDialogFragment nadf = NextAgeDialogFragment.newInstance(nac);
            nadf.show(fm, "Next Age");
        }
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController player) {
        player.nextRound();
    }

}
