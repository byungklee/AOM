package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Flyer;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class NidHogg extends Unit implements Myth, Flyer {
    public NidHogg() {
        setName("Nidhogg");
        setCulture("Norse");
        setImagePath(R.drawable.battle_nidhogg);
        setDice(7);
        setVsGiantKiller(4);
        setGoldCost(4);
        setFavorCost(1);
        setSpecialEffect("Destroy2Building");
    }
}
