package com.anycomp.android.ageofmythology.model.area;


import com.anycomp.android.ageofmythology.model.building.Building;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.building.HouseBuilding;
import com.anycomp.android.ageofmythology.model.tile.BuildingTile;

import java.util.ArrayList;

public class CityArea extends Area {
    private int numberOfHouse;
    private int numberOfBuilding;
	public CityArea(int maxSize) {
		super(maxSize);
		// TODO Auto-generated constructor stub
        numberOfHouse = 0;
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
                if(b instanceof HouseBuilding) {
                    incrementNumberOfHouse();
                }
                break;
            }
        }
        numberOfBuilding++;
        notifyObservers();
    }

    public void destroyBuilding(int index) {
        BuildingTile bt = (BuildingTile) getTiles().get(index);
        if(bt.getBuilding().getBuildingType() == BuildingType.HOUSE) {
            decrementNumberOfHouse();
        }
        bt.setBuilding(null);

        numberOfBuilding--;
        notifyObservers();
    }

    public void incrementNumberOfHouse() {
        numberOfHouse++;
    }

    public void decrementNumberOfHouse() {
        numberOfHouse--;
    }

    public int getNumberOfHouse() {
        return numberOfHouse;
    }

    public int getNumberOfBuilding() {
        return numberOfBuilding;
    }

    public void setNumberOfBuilding(int numberOfBuilding) {
        this.numberOfBuilding = numberOfBuilding;
    }
}
