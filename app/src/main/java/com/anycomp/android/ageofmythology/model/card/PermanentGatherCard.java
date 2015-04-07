package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;
import android.content.Context;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentGatherCard extends PermanentActionCard  {
    public PermanentGatherCard(Culture culture) {
        setName("Gather");
        setCulture(culture);
        setImagePath(culture.getPermanentGatherCardImage());
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController player) {

        player.nextRound();
    }


}
