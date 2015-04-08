package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Hero;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class MythicGreekHero extends Unit implements Hero {
    public MythicGreekHero() {
        setName("MythicGreekHero");
        setCulture("Greek");
        setImagePath(R.drawable.battle_mythical_greek_hero);
        setGoldCost(4);
        setFavorCost(4);
        setDice(5);
        setAge(4);
        setSpecialEffect("AddOpponentDiceNumber");
    }
}
