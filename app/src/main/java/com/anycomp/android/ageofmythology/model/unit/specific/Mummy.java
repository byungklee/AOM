package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Mummy extends Unit implements Myth {
    public Mummy() {

        setName("Mummy");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_mummy);
        setDice(5);
        setGoldCost(3);
        setFavorCost(2);
        setSpecialEffect("NewMummy");
    }
}
