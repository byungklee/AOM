package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.BuildingSelectionController;
import com.anycomp.android.ageofmythology.BuildingSelectionDialogFragment;
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
        setName("GodGreekBuild");
        this.card=card;
        setImagePath(R.drawable.card_rand_greek_build_hera);
        setValue(3);
        favorCost = 1;
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            AskGodPowerUseDialogFragment agpud = new AskGodPowerUseDialogFragment();
            agpud.setGod(this);
            agpud.show(fm, "UseGod");
        }

    }

    private void addHouse(PlayerController pc) {
        CityArea cityArea = (CityArea) pc.getCurrentPlayer().getPlayerBoard().getCityArea();
        if(cityArea.getNumberOfHouse() < 9)
            ((CityArea) pc.getCurrentPlayer().getPlayerBoard().getCityArea()).addBuilding(BuildingFactory.newInstance(BuildingType.HOUSE));
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            if(payFavor()) {
                addHouse(pc);

            }
            BuildingSelectionController bsc = BuildingSelectionController.getInstance(pc);
            //bsc.
            for (int j = 0; j < getValue(); j++) {
                for (int i = 0; i < bsc.getBuildingList().size(); i++) {
                    if (bsc.verifyAvailability(i) && bsc.verifyResource(i)) {
                        bsc.addBuilding(i);
                    }
                }
            }
        }
        pc.nextRound();
    }

    @Override
    public void playGod() {
        addHouse(pc);
        BuildingSelectionController bsc = BuildingSelectionController.getInstance(pc);
        bsc.playBuildCard(this);
        BuildingSelectionDialogFragment bsdf = BuildingSelectionDialogFragment.newInstance(bsc);
        bsdf.setMaxAllowedPick(getValue());
        bsdf.show(fm, "Building Selection Dialog");
    }

    @Override
    public void playNormal() {
        BuildingSelectionController bsc = BuildingSelectionController.getInstance(pc);
        bsc.playBuildCard(this);
        BuildingSelectionDialogFragment bsdf = BuildingSelectionDialogFragment.newInstance(bsc);
        bsdf.setMaxAllowedPick(getValue());
        bsdf.show(fm, "Building Selection Dialog");
    }
    @Override
    public boolean payFavor() {
        return pay();
    }

    @Override
    public boolean checkAge() {
        return true;
    }
}
