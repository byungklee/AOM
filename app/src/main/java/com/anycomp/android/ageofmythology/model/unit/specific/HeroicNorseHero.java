package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Hero;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class HeroicNorseHero extends Unit implements Hero {
    public HeroicNorseHero() {
        setName("HeroicNorseHero");
        setCulture("Norse");
        setImagePath(R.drawable.battle_heroic_norse_hero);
        setDice(6);
        setAge(3);
        setFoodCost(3);
        setGoldCost(3);
        setVsMyth(4);
        setSpecialEffect("Berserk");
    }
}
