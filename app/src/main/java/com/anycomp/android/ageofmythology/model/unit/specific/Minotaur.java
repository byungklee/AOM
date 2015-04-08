package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.Warrior;

/**
 * Created by byung on 4/6/15.
 */
public class Minotaur extends Unit implements Myth, Warrior {
    public Minotaur() {
        setCulture("Greek");
        setDice(6);
        setFoodCost(2);
        setWoodCost(2);
        setVsCavarlys(4);
        setSpecialEffect("Destroy2Buildings");
        setName("Hydra");
        setImagePath(R.drawable.battle_hydra);
    }
}
