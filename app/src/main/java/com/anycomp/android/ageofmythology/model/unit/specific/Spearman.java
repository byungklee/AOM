package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.Warrior;

/**
 * Created by byung on 4/6/15.
 */
public class Spearman extends Unit implements Mortal, Warrior {
    public Spearman() {
        setName("Spearman");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_spearman);

        setDice(3);
        setFoodCost(1);
        setWoodCost(1);
        setVsCavarlys(3);
        setVsHeroes(4);
    }
}
