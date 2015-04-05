package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.resource.VictoryCube;

import java.util.ArrayList;

/**
 * Created by byung on 4/4/15.
 */
public abstract class VictoryCard extends Card {
    ArrayList<VictoryCube> victoryCubes;
    public VictoryCard() {
        victoryCubes = new ArrayList<VictoryCube>();
    }
    @Override
    public void play(FragmentManager ctx, PlayerController player) {

    }

    @Override
    public void aiPlay(FragmentManager ctx, PlayerController player) {

    }

    public void addVictoryCube() {
        victoryCubes.add(new VictoryCube());
    }
    public void removeVictoryCube() {
        if(!victoryCubes.isEmpty())
            victoryCubes.remove(0);
    }

    public ArrayList<VictoryCube> getVictoryCubes() {
        return victoryCubes;
    }


    public int getCubeSize() {
        return victoryCubes.size();
    }
}
