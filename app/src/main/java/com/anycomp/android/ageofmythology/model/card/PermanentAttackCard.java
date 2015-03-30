package com.anycomp.android.ageofmythology.model.card;

import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentAttackCard extends PermanentActionCard {
    public PermanentAttackCard(Culture culture) {
       setName("Attack");
        setCulture(culture);

        setImagePath(culture.getPermanentAttackCardImage());

    }


    @Override
    public void play() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
