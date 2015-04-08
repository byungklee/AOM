package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;
import android.content.Context;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.TileManager;
import com.anycomp.android.ageofmythology.TileSelectionController;
import com.anycomp.android.ageofmythology.TileSelectionDialogFragment;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentExploreCard extends PermanentActionCard {

    public PermanentExploreCard(Culture culture) {
        setName("Explore");
        setCulture(culture);
        setImagePath(culture.getPermanentExploreCardImage());
        setValue(4);
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
            tsd.setTileSelectionController(new TileSelectionController(pc, 1));
            tsd.show(fm, "Tile Selection Dialog");
        }
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController player) {
        play(fm,player);
//        player.nextRound();
    }


}
