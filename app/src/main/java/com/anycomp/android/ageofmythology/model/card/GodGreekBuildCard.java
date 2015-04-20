package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.area.CityArea;
import com.anycomp.android.ageofmythology.model.building.BuildingFactory;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.building.HouseBuilding;

/**
 * Created by byung on 4/19/15.
 */
public class GodGreekBuildCard extends RandomBuildCard implements God {
    public GodGreekBuildCard(Card card) {
        this.card=card;
        setImagePath(R.drawable.card_rand_greek_build_hera);
        card.setValue(3);
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            addHouse(pc);
            card.play(fm, pc);
        }

    }

    private void addHouse(PlayerController pc) {
        CityArea cityArea = (CityArea) pc.getCurrentPlayer().getPlayerBoard().getCityArea();
        if(cityArea.getNumberOfHouse() < 9)
            ((CityArea) pc.getCurrentPlayer().getPlayerBoard().getCityArea()).addBuilding(BuildingFactory.newInstance(BuildingType.HOUSE));
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            addHouse(pc);
            card.aiPlay(fm, pc);
        }
    }
}
