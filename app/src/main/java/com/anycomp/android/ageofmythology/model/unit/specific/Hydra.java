package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Giant;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Hydra extends Unit implements Giant, Myth {
    public Hydra() {
        setCulture("Greek");
        setDice(6);
        setGoldCost(2);
        setFavorCost(2);
        setVsWarriors(4);
        setSpecialEffect("1BattleDie");
        setName("Hydra");
        setImagePath(R.drawable.battle_hydra);
    }

}
