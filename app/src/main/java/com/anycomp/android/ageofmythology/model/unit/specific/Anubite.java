package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Cavalry;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class Anubite extends Unit implements Myth, Cavalry {
    public Anubite() {
        setName("Anubite");
        setCulture("Egypt");
        setGoldCost(3);
        setFavorCost(1);
        setVsArchers(4);
        setDice(5);
        setImagePath(R.drawable.battle_anubite);
    }
}
