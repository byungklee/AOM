package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Archer;
import com.anycomp.android.ageofmythology.model.unit.Cavalry;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Centaur extends Unit implements Archer, Cavalry, Myth {
    public Centaur() {
        setCulture("Greek");
        setName("Centaur");
        setDice(5);
        setWoodCost(3);
        setFavorCost(1);
        setVsArchers(3);
        setVsFlyers(3);
        setImagePath(R.drawable.battle_centaur);
    }
}
