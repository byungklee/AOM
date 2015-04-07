package com.anycomp.android.ageofmythology.model.unit.specific;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.unit.Myth;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.Warrior;

/**
 * Created by byung on 4/6/15.
 */
public class Wadjet extends Unit implements Myth, Warrior {
    public Wadjet() {
        setName("Wadjet");
        setCulture("Egypt");
        setImagePath(R.drawable.battle_wadjet);
        setDice(5);
        setFoodCost(2);
        setFavorCost(2);
        setVsCavarlys(4);
        setSpecialEffect("TakePharaoh'sPlace");
    }
}
