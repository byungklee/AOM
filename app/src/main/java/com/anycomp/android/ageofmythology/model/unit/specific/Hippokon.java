package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Cavalry;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Hippokon extends Unit implements Cavalry, Mortal {
    public Hippokon() {
        setName("Hippokon");
        setCulture("Greek");
        setImagePath(R.drawable.battle_hippokon);
        setFoodCost(1);
        setGoldCost(1);
        setVsHeroes(4);
        setVsArchers(4);
        setDice(3);
//        set
    }
}
