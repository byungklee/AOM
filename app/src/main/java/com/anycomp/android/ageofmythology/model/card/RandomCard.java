package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.PlayerController;

public abstract class RandomCard extends Card {
    protected Card card;
    int favorCost;
    PlayerController pc;
    FragmentManager fm;
    boolean pay() {
        if(favorCost <= pc.getCurrentPlayer().getFavorCube().getValue()) {
            pc.getCurrentPlayer().spendFavor(favorCost);
            return true;
        }
        return false;
    }
}
