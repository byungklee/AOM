package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Giant;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class FrostGiant extends Unit implements Myth, Giant {
    public FrostGiant() {
        setName("FrostGiant");
        setCulture("Norse");
        setImagePath(R.drawable.battle_frost_giant);
        setFoodCost(4);
        setFavorCost(2);
        setVsWarriors(2);
        setVsMortal(3);
        setDice(7);

    }
}
