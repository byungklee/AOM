package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Giant;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class ScorpionMan extends Unit implements Myth, Giant {

    public ScorpionMan() {
        setName("ScorpionMan");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_scorpion_man);
        setDice(5);
        setGoldCost(2);
        setFoodCost(4);
        setVsMortal(4);
    }

}
