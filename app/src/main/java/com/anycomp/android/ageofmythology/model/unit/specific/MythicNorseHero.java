package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Hero;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class MythicNorseHero extends Unit implements Hero {
    public MythicNorseHero() {
        setName("MythicNorseHero");
        setCulture("Norse");
        setImagePath(R.drawable.battle_mythic_norse_hero);
        setDice(8);
        setAge(4);
        setFoodCost(4);
        setFavorCost(4);
        setVsMyth(4);
        setSpecialEffect("AddUnitOnKill");
    }
}
