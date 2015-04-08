package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Giant;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Elephant extends Unit implements Giant, Mortal {
    public Elephant() {
        setName("Elephant");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_elephant);
        setDice(3);
        setFoodCost(2);
        setGoldCost(1);
        setVsMortal(2);
        setDoesNegateWallAndTower(true);
    }
}
