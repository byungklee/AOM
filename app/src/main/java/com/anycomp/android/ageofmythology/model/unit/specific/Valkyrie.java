package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Cavalry;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Valkyrie extends Unit implements Myth, Cavalry {
    public Valkyrie() {
        setName("Valkyrie");
        setCulture("Norse");
        setImagePath(R.drawable.battle_valkyrie);
        setGoldCost(1);
        setFavorCost(3);
        setVsArchers(4);
        setSpecialEffect("2FavorPerKill");
        setDice(5);
    }
}
