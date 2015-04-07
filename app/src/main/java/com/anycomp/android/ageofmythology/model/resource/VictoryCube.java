package com.anycomp.android.ageofmythology.model.resource;

import com.anycomp.android.ageofmythology.R;

public class VictoryCube extends Cube {
    public VictoryCube() {
        //setImagePath(R.drawable);
        this(1);
    }

    public VictoryCube(int i) {
        setImagePath(R.drawable.card_victory_army);
        setValue(i);
    }
}
