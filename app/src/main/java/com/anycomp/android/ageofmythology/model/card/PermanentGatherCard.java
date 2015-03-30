package com.anycomp.android.ageofmythology.model.card;

import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentGatherCard extends PermanentActionCard  {
    public PermanentGatherCard(Culture culture) {
        setName("Gather");
        setCulture(culture);
        setImagePath(culture.getPermanentGatherCardImage());
    }

    @Override
    public void play() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
