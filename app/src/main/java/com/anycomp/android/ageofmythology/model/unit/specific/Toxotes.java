package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Archer;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Toxotes extends Unit implements Mortal, Archer {
    public Toxotes() {
        setCulture("Greek");
        setName("Toxotes");
        setImagePath(R.drawable.battle_toxotes);
        setFoodCost(1);
        setWoodCost(1);
        setVsFlyers(4);
        setVsWarriors(3);
        setDice(3);
    }
}
