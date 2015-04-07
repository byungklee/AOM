package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Hero;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Pharaoh extends Unit implements Hero {
    public Pharaoh() {
        setName("Pharaoh");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_pharaoh);
        setDice(6);
        setFoodCost(3);
        setGoldCost(3);
        setVsMyth(4);
        setAge(3);
        setSpecialEffect("ReplaceWadjet");

    }
}
