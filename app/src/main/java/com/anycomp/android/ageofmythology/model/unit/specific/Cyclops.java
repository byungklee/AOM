package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Giant;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Cyclops extends Unit implements Giant, Myth {
    public Cyclops() {
        setCulture("Greek");
        setDice(6);
        setFoodCost(3);
        setFavorCost(3);
        setVsMortal(4);
        setSpecialEffect("Throwing");
        setName("Cyclops");
        setImagePath(R.drawable.battle_cyclops);
    }
}
