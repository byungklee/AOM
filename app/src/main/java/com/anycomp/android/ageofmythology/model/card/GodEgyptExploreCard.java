package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
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
        setName("GodEgyptExplore");
        setImagePath(R.drawable.card_rand_egypt_explore_ptah);
        setValue(7);
        this.card = card;
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

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            if(payFavor()) {
                playGod();
            } else {
                playNormal();
            }
        }
    }

    @Override
    public void playGod() {
        pc.setCurrentPlayer(pc.getTurnManager().getCurrentPlayer());
        pc.setIsForward(true);
        TileManager.getInstance().setNumberOfCardsToRefresh(getValue());
        TileSelectionDialogFragment tsd = new TileSelectionDialogFragment();
        tsd.setTileSelectionController(new TileSelectionController(pc, 1, false, true));
        tsd.show(fm, "Tile Selection Dialog");
    }

    @Override
    public void playNormal() {
        setValue(4);
        pc.setCurrentPlayer(pc.getTurnManager().getCurrentPlayer());
        pc.setIsForward(true);
        TileManager.getInstance().setNumberOfCardsToRefresh(getValue());
        TileSelectionDialogFragment tsd = new TileSelectionDialogFragment();
        tsd.setTileSelectionController(new TileSelectionController(pc, 1));
        tsd.show(fm, "Tile Selection Dialog");
    }

    @Override
    public boolean payFavor() {
        return pay();
    }
}
