package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.GiantKiller;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Dwarf extends Unit implements Myth, GiantKiller {
    public Dwarf() {
        setName("Dwarf");
        setCulture("Norse");
        setImagePath(R.drawable.battle_dwarf);
        setDice(4);
        setFoodCost(2);
        setGoldCost(2);
        setVsGiant(7);
        setDoesNegateWallAndTower(true);
        setSpecialEffect("2GoldForProduction");
    }
}
