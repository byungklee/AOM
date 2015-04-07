package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Archer;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class ThrowingAxeman extends Unit implements Mortal, Archer {
    public ThrowingAxeman() {
        setName("ThrowingAxeman");
        setCulture("Norse");
        setImagePath(R.drawable.battle_throwing_axeman);
        setFoodCost(1);
        setWoodCost(1);
        setVsWarriors(3);
        setVsFlyers(4);
        setDice(3);
    }
}
