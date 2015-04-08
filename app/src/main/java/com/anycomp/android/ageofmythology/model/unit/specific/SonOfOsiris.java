package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Hero;
import com.anycomp.android.ageofmythology.model.unit.Unit;

/**
 * Created by byung on 4/6/15.
 */
public class SonOfOsiris extends Unit implements Hero {
    public SonOfOsiris() {
        setName("SonOfOsiris");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_son_of_osiris);
        setGoldCost(4);
        setFavorCost(4);
        setVsMyth(4);
        setDice(8);
        setAge(4);

        setSpecialEffect("Resurrect4Favor");
    }
}
