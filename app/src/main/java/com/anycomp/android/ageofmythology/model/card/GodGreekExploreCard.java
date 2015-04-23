package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.TileManager;
import com.anycomp.android.ageofmythology.TileSelectionController;
import com.anycomp.android.ageofmythology.TileSelectionDialogFragment;
import com.anycomp.android.ageofmythology.model.area.CityArea;
import com.anycomp.android.ageofmythology.model.building.BuildingFactory;
import com.anycomp.android.ageofmythology.model.building.BuildingType;

/**
 * Created by byung on 4/19/15.
 */
public class GodGreekExploreCard extends RandomExploreCard implements God {
    public GodGreekExploreCard(Card card) {
        setImagePath(R.drawable.card_rand_greek_explore_artemis);
        setValue(7);
        this.card = card;
    }
    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            setPlayed(true);
//            pc.setCurrentPlayer(0);
            pc.setCurrentPlayer(pc.getTurnManager().getCurrentPlayer());
            pc.setIsForward(true);
            TileManager.getInstance().setNumberOfCardsToRefresh(getValue());
            TileSelectionDialogFragment tsd = new TileSelectionDialogFragment();
            tsd.setTileSelectionController(new TileSelectionController(pc, 1, false, true));
            tsd.show(fm, "Tile Selection Dialog");
        }

    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            play(fm,pc);
        }
    }
}
