/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anycomp.android.ageofmythology.model.building;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author byung
 */
public class BuildingContainer {
    private static BuildingContainer instance;
    public static BuildingContainer getInstance() {
        if(instance == null) {
            instance =new BuildingContainer();
        }
        return instance;
    }
    
    ArrayList<Building> buildingList;
    
    private BuildingContainer() {
        buildingList =new ArrayList<Building>();
        initializeBuilding();
    }
    public void initializeBuilding() {
        BuildingType[] bts = BuildingType.values();
        for(BuildingType bt : bts) {
            buildingList.add(BuildingFactory.newInstance(bt));
        }
    }
    
    public Iterator iterator() {
        return buildingList.iterator();
    }
    
    public Building get(int index) {
        return buildingList.get(index);
    }
    
    public ArrayList<Building> getList() {
        return buildingList;
    }
}
