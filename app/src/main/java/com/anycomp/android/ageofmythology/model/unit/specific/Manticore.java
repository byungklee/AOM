package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Flyer;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Manticore extends Unit implements Flyer, Myth {
    public Manticore() {
        setCulture("Greek");
        setDice(5);
        setFoodCost(2);
        setFavorCost(2);
        setVsGiantKiller(4);
        setName("Manticore");
        setImagePath(R.drawable.battle_manticore);
    }
}
