package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Hero;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class HeroicGreekHero extends Unit implements Hero {
    public HeroicGreekHero() {
        setName("HeroicGreekHero");
        setCulture("Greek");
        setImagePath(R.drawable.battle_heoric_greek_hero);
        setFoodCost(3);
        setGoldCost(4);
        setVsMyth(4);
        setDice(6);
        setAge(3);
        setSpecialEffect("DefeatHeroOpponentRetreat");
    }
}
