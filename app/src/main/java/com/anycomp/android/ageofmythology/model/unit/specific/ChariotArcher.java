package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Archer;
import com.anycomp.android.ageofmythology.model.unit.Cavalry;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class ChariotArcher extends Unit implements Archer,Mortal,Cavalry {
    public ChariotArcher() {
        setName("ChariotArcher");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_chariot_archer);
        setDice(3);
        setGoldCost(1);
        setWoodCost(1);
        setVsFlyers(3);
        setVsWarriors(3);
    }
}
