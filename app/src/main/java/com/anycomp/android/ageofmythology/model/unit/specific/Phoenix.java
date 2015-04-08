package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Flyer;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Phoenix extends Unit implements Flyer, Myth {
    public Phoenix() {
        setName("Phoenix");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_phoenix);
        setDice(6);
        setWoodCost(2);
        setFavorCost(3);
        setVsGiantKiller(4);
        setSpecialEffect("ResurrectedIfPriest");
    }
}
