package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.resource.VictoryCube;

import java.util.ArrayList;

/**
 * Created by byung on 4/4/15.
 */
public class VictoryCard extends Card {
    ArrayList<VictoryCube> victoryCubes;

    @Override
    public void play(FragmentManager ctx, PlayerController player) {

    }

    public void addVictoryCube() {
        victoryCubes.add(new VictoryCube());
    }
    public void removeVictoryCube() {
        if(!victoryCubes.isEmpty())
            victoryCubes.remove(0);
    }
    public int getCubeSize() {
        return victoryCubes.size();
    }
}
