package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.GiantKiller;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Medusa extends Unit implements Myth,GiantKiller {
    public Medusa() {
        setName("Medusa");
        setCulture("Greek");
        setImagePath(R.drawable.battle_medusa);
        setFoodCost(1);
        setFavorCost(3);
        setDice(5);
        setVsGiant(6);
        setSpecialEffect("WinAllTies");
    }
}
