package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Hero;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Priest extends Unit implements Hero {
    public Priest() {
        setName("Priest");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_priest);
        setFoodCost(2);
        setGoldCost(4);
        setVsMyth(5);
        setSpecialEffect("Resurrect");
        setDice(4);
        setAge(2);
    }
}
