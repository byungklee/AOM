package com.anycomp.android.ageofmythology.model.card;

import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentExploreCard extends PermanentActionCard {

    public PermanentExploreCard(Culture culture) {
        setName("Explore");
        setCulture(culture);
        setImagePath(culture.getPermanentExploreCardImage());
        setValue(1);
    }

    int i =0;
    @Override
    public void play() {
    }

}
