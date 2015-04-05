package com.anycomp.android.ageofmythology;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.model.area.CityArea;
import com.anycomp.android.ageofmythology.model.building.Building;
import com.anycomp.android.ageofmythology.model.building.BuildingFactory;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.building.HouseBuilding;
import com.anycomp.android.ageofmythology.model.card.Card;
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

    private Card card;

    public ArrayList<Building> getBuildingList() {
        return buildingList;
    }

    public void playBuildCard(Card card) {
        this.card = card;
        //turn on popup

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
        //
        Building pickedBuilding = buildingList.get(pick);
        if(pc.getCurrentPlayer().hasBuilding(BuildingType.QUARRY)) {
            if(pc.getCurrentPlayer().getWoodCube().getValue() >= Math.max(pickedBuilding.getWoodCost()-1,0) &&
                    pc.getCurrentPlayer().getFoodCube().getValue() >= Math.max(pickedBuilding.getFoodCost()-1,0) &&
                    pc.getCurrentPlayer().getGoldCube().getValue() >= Math.max(pickedBuilding.getGoldCost()-1,0) &&
                    pc.getCurrentPlayer().getFavorCube().getValue() >= Math.max(pickedBuilding.getFavorCost()-1,0)) {
                return true;
            }
        } else {
            if(pc.getCurrentPlayer().getWoodCube().getValue() >= pickedBuilding.getWoodCost() &&
                    pc.getCurrentPlayer().getFoodCube().getValue() >= pickedBuilding.getFoodCost() &&
                    pc.getCurrentPlayer().getGoldCube().getValue() >= pickedBuilding.getGoldCost() &&
                    pc.getCurrentPlayer().getFavorCube().getValue() >= pickedBuilding.getFavorCost()) {
                return true;
            }
        }

        //cost
        //pickedBuilding.getCost();

        //special effect?

        return false;
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
        ca.notifyObservers();

        //maybe refactor later
        if(pc.getCurrentPlayer().hasBuilding(BuildingType.QUARRY)) {
            pc.getCurrentPlayer().getWoodCube().setValue(pc.getCurrentPlayer().getWoodCube().getValue() - Math.max(pickedBuilding.getWoodCost()-1,0));
            pc.getCurrentPlayer().getFoodCube().setValue(pc.getCurrentPlayer().getFoodCube().getValue() - Math.max(pickedBuilding.getFoodCost()-1,0));
            pc.getCurrentPlayer().getGoldCube().setValue(pc.getCurrentPlayer().getGoldCube().getValue() - Math.max(pickedBuilding.getGoldCost()-1,0));
            pc.getCurrentPlayer().getFavorCube().setValue(pc.getCurrentPlayer().getFavorCube().getValue() - Math.max(pickedBuilding.getFavorCost()-1,0));
        } else {
            pc.getCurrentPlayer().getWoodCube().setValue(pc.getCurrentPlayer().getWoodCube().getValue() - pickedBuilding.getWoodCost());
            pc.getCurrentPlayer().getFoodCube().setValue(pc.getCurrentPlayer().getFoodCube().getValue() - pickedBuilding.getFoodCost());
            pc.getCurrentPlayer().getGoldCube().setValue(pc.getCurrentPlayer().getGoldCube().getValue() - pickedBuilding.getGoldCost());
            pc.getCurrentPlayer().getFavorCube().setValue(pc.getCurrentPlayer().getFavorCube().getValue() - pickedBuilding.getFavorCost());
        }

        //To do: addition on bank.



    }

    public void nextRound() {
        pc.nextRound();
    }


}
