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
public class GodNorseExploreCard extends RandomExploreCard implements God {
    public GodNorseExploreCard(Card card) {
        setName("GodNorseExplore");

        setImagePath(R.drawable.card_rand_norse_explore_baldr);
        setValue(4);
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
            tsd.setTileSelectionController(new TileSelectionController(pc, 1, true, false));
            tsd.show(fm, "Tile Selection Dialog");

    }

    @Override
    public void playNormal() {
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

    @Override
    public boolean checkAge() {
        return true;
    }
}
