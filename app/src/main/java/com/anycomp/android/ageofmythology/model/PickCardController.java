package com.anycomp.android.ageofmythology.model;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.card.Card;

/**
 * Created by byung on 3/30/15.
 */
public class PickCardController {
    private PlayerController pc;
    public PickCardController(PlayerController pc) {
        this.pc = pc;
    }
    public void pickCard(int index) {
        pc.getCurrentPlayer().pickCard(index);
    }
}
