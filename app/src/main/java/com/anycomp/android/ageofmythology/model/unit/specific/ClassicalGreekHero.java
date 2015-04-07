package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Hero;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class ClassicalGreekHero extends Unit implements Hero {
    public ClassicalGreekHero() {
        setName("ClassicalGreekHero");
        setCulture("Greek");
        setImagePath(R.drawable.battle_classical_greek_hero);
        setFoodCost(3);
        setGoldCost(3);
        setVsMyth(4);
        setDice(5);
        setAge(2);
    }

}
