package com.anycomp.android.ageofmythology;

import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.VillagerUnit;

import java.util.ArrayList;

/**
 * Created by byung on 4/3/15.
 */
public class VillagerController {

    private ArrayList<Unit> villagers;
    private int idleVillagers;
    private int workingVillagers;

    public VillagerController() {
        villagers = new ArrayList<Unit>();
        idleVillagers = 0;
        workingVillagers = 0;
    }

    public void incrementVillager() {
        VillagerUnit vu = new VillagerUnit();
        villagers.add(vu);
        idleVillagers++;
    }

    public boolean isAnyVillagerIdle() {
        if(idleVillagers > 0) {
            return true;
        }
        return false;
    }

    public Unit takeVillager() {
        if(idleVillagers > 0) {
            idleVillagers--;
            return villagers.get(workingVillagers++);
        } else {
            return null;
        }
    }
    public void returnVillager() {
        workingVillagers--;
        idleVillagers++;
    }

    public int getIdleVillagers() {
        return idleVillagers;
    }

    public int getTotalVillagers() {
        return villagers.size();
    }
}
