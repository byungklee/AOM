package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.FoodTileEliminationDialogFragment;
import com.anycomp.android.ageofmythology.GodCardDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.TileEliminationController;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.culture.Greek;
import com.anycomp.android.ageofmythology.model.player.Player;

/**
 * Created by bert on 5/4/15.
 */
public class GodEgyptNextAgeCard extends RandomNextAgeCard implements God {
    public GodEgyptNextAgeCard(PermanentNextAge card) {
        setName("GodEgyptNextAge");
        this.card = card;
        setImagePath(R.drawable.card_rand_egypt_age_hathor);
        favorCost = 1;
    }

    @Override
    public Culture getCulture() { return card.getCulture(); }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        this.fm = fm;
        this.pc = pc;

        if (!isPlayed()) {
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
        FoodTileEliminationDialogFragment ftedf = new FoodTileEliminationDialogFragment();
        TileEliminationController tec = TileEliminationController.getInstance(pc);

        tec.makeFoodTileList();

        if (pc.getCurrentPlayer().getName().equals("user")) {
            ftedf.setTec(tec);
            ftedf.setCard(this);
            ftedf.show(fm, "Eliminate Food Tile");
        } else { // AI
            tec.greekEliminate(0);
            pc.nextRound();
        }
    }

    @Override
    public void playNormal() {
        PermanentNextAge.godPlay(fm, pc);
        pc.nextRound();
    }

    @Override
    public boolean payFavor() { return pay(); }

    public void otherActivity() {
        Player player = pc.getCurrentPlayer();

        if (player.getName().equals("user")) {
            GodCardDialogFragment gcdf = new GodCardDialogFragment();

            gcdf.setGod(this);
            gcdf.setPlayerController(pc);
            gcdf.show(this.fm, "Other Activity");
        } else {
            pc.nextRound();
        }
    }
}
