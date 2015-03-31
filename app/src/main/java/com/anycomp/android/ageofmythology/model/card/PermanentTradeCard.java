package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;
import android.content.Context;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentTradeCard extends PermanentActionCard {
    public PermanentTradeCard(Culture culture) {
         setName("Trade");
        setCulture(culture);
        setImagePath(culture.getPermanentTradeCardImage());
    }
    @Override
    public void play(FragmentManager fm, PlayerController pc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
