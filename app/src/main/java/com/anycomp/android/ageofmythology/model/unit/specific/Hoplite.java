package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.Warrior;

/**
 * Created by byung on 4/6/15.
 */
public class Hoplite extends Unit implements Warrior, Mortal {
    public Hoplite() {
        setName("Hoplite");
        setCulture("Greek");
        setImagePath(R.drawable.battle_hoplite);
        setFoodCost(1);
        setWoodCost(1);
        setVsCavarlys(3);
        setVsMortal(1);
        setDice(3);
    }
}
