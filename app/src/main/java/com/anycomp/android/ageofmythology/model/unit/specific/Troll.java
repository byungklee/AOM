package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.Warrior;

/**
 * Created by byung on 4/6/15.
 */
public class Troll extends Unit implements Myth, Warrior {
    public Troll() {
        setName("Troll");
        setCulture("Norse");
        setImagePath(R.drawable.battle_troll);
        setDice(6);
        setFoodCost(3);
        setWoodCost(2);
        setVsCavarlys(4);
    }
}
