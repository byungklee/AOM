package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Cavalry;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Jarl extends Unit implements Mortal, Cavalry {
    public Jarl() {
        setName("Jarl");
        setCulture("Norse");
        setImagePath(R.drawable.battle_jarl);
        setFoodCost(1);
        setGoldCost(1);
        setVsHeroes(4);
        setVsArchers(4);
        setDice(3);
    }
}
