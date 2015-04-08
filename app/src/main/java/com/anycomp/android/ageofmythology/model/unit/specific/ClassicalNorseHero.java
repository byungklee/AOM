package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Hero;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class ClassicalNorseHero extends Unit implements Hero {
    public ClassicalNorseHero() {
        setName("ClassicalNorseHero");
        setCulture("Norse");
        setImagePath(R.drawable.battle_classical_norse_hero);
        setFoodCost(3);
        setGoldCost(3);
        setDice(5);
        setVsMyth(4);
        setAge(2);
    }
}
