package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.TileManager;
import com.anycomp.android.ageofmythology.TileSelectionController;
import com.anycomp.android.ageofmythology.TileSelectionDialogFragment;

/**
 * Created by byung on 4/19/15.
 */
public class GodEgyptExploreCard extends RandomExploreCard implements God {
    public GodEgyptExploreCard(Card card) {
        setImagePath(R.drawable.card_rand_egypt_explore_ptah);
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
