package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.GiantKiller;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Sphinx extends Unit implements Myth, GiantKiller{
    public Sphinx() {
        setName("Sphnix");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_sphinx);
        setDice(5);
        setGoldCost(2);
        setFavorCost(2);
        setVsGiant(6);
    }

}
