package com.anycomp.android.ageofmythology.model.card;


import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentNextAge extends PermanentActionCard {

    public PermanentNextAge(Culture culture) {
         setName("Age");
        setCulture(culture);
        setImagePath(culture.getPermanentNextCardImage());
    }

    @Override
    public void play() {

    }

}
