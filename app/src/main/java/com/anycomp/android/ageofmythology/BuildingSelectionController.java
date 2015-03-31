package com.anycomp.android.ageofmythology;

import com.anycomp.android.ageofmythology.model.area.CityArea;
import com.anycomp.android.ageofmythology.model.building.Building;
import com.anycomp.android.ageofmythology.model.building.BuildingFactory;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.building.HouseBuilding;
import com.anycomp.android.ageofmythology.model.tile.BuildingTile;

import java.util.ArrayList;

/**
 * Created by byung on 3/30/15.
 */
public class BuildingSelectionController {
    private PlayerController pc;
    private ArrayList<Building> buildingList;

    private static BuildingSelectionController instance;
    public static BuildingSelectionController getInstance(PlayerController pc) {
        if(instance == null) {
            instance = new BuildingSelectionController(pc);
        }
        return instance;
    }

    private BuildingSelectionController(PlayerController pc) {

        this.pc = pc;
        buildingList = new ArrayList<>();
        initBuildingList();
    }

    public void initBuildingList() {
        BuildingType[] bts= BuildingType.values();
        for(int i=0;i<bts.length;i++) {
            buildingList.add(BuildingFactory.newInstance(bts[i]));
        }
    }

    public boolean verifyAvailability(int pick) {
        //pc.getCurrentPlayer().getPlayerBoard().getCityArea().contains();
        CityArea ca = (CityArea) pc.getCurrentPlayer().getPlayerBoard().getCityArea();
        Building pickedBuilding = buildingList.get(pick);
        BuildingTile bt = ca.get(buildingList.get(pick));

        if(bt == null) {
            return true;
        } else {
            if(pickedBuilding instanceof HouseBuilding) {
                HouseBuilding hb = (HouseBuilding) bt.getBuilding();
                if(hb.getQuantity() < 9) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verifyResource(int pick) {
        //cost
        //special effect?
    }

    public void addBuilding(int pick) {
        CityArea ca = (CityArea) pc.getCurrentPlayer().getPlayerBoard().getCityArea();
        Building pickedBuilding = buildingList.get(pick);
        BuildingTile bt = ca.get(buildingList.get(pick));
        if(pickedBuilding instanceof HouseBuilding) {
            if(bt == null) {
                HouseBuilding houseBuilding = new HouseBuilding();
                houseBuilding.setQuantity(1);
                ca.addBuilding(houseBuilding);
            } else {
                HouseBuilding hb = (HouseBuilding) bt.getBuilding();
                hb.setQuantity(hb.getQuantity()+1);
            }
        } else {
            ca.addBuilding(pickedBuilding);
        }
    }


}
