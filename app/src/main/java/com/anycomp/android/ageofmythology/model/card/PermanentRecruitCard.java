package com.anycomp.android.ageofmythology.model.card;


import android.app.FragmentManager;
import android.content.Context;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentRecruitCard extends PermanentActionCard {
    public PermanentRecruitCard(Culture culture) {
        setName("Recruit");
        setCulture(culture);
        setImagePath(culture.getPermanentRecruitCardImage());
    }


    @Override
    public void play(FragmentManager fm, PlayerController pc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
