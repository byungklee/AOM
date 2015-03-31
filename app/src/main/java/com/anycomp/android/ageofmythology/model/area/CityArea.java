package com.anycomp.android.ageofmythology.model.area;


import com.anycomp.android.ageofmythology.model.building.Building;
import com.anycomp.android.ageofmythology.model.tile.BuildingTile;

import java.util.ArrayList;

public class CityArea extends Area {

	public CityArea(int maxSize) {
		super(maxSize);
		// TODO Auto-generated constructor stub
        //Tile
	}

    @Override
    boolean contains(Object o) {
        Building b = (Building) o;
        ArrayList al = getTiles();
        for(int i=0;i<al.size();i++) {
            BuildingTile bt = (BuildingTile) al.get(i);
            if(bt.getBuilding() == b) {
                return true;
            }
        }
        return false;

    }

    public BuildingTile get(Building b) {
        ArrayList al = getTiles();
        for(int i=0;i<al.size();i++) {
            BuildingTile bt = (BuildingTile) al.get(i);
            if(bt.getBuilding() == b) {
                return bt;
            }
        }
        return null;
    }

    public void addBuilding(Building b) {
        ArrayList al = getTiles();
        for(int i=0;i<al.size();i++) {
            BuildingTile bt = (BuildingTile) al.get(i);
            if(bt.getBuilding() == null) {
                bt.setBuilding(b);
                break;
            }
        }
        notifyObservers();
    }

}
