package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.Warrior;

/**
 * Created by byung on 4/6/15.
 */
public class Huskarl extends Unit implements Warrior, Mortal {
    public Huskarl() {
        setName("Huskarl");
        setCulture("Norse");
        setImagePath(R.drawable.battle_huskarl);
        setDice(3);
        setFoodCost(1);
        setGoldCost(2);
        setVsCavarlys(4);
        setSpecialEffect("Berserk");
    }
}
