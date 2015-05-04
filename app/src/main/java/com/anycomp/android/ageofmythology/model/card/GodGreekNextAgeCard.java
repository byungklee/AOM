package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;
import android.app.Fragment;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.GodCardDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.TileEliminationController;
import com.anycomp.android.ageofmythology.TileEliminationDialogFragment;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.player.Player;

/**
 * Created by bert on 5/1/15.
 */
public class GodGreekNextAgeCard extends RandomNextAgeCard implements God {
    public GodGreekNextAgeCard(PermanentNextAge card) {
        setName("GodGreekNextAge");
        this.card = card;
        setImagePath(R.drawable.card_rand_greek_age_hephaestos);
        favorCost = 2;
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
        TileEliminationDialogFragment tedf = new TileEliminationDialogFragment();
        TileEliminationController tec = TileEliminationController.getInstance(pc);

        tec.makeTileList();

        if (pc.getCurrentPlayer().getName().equals("user")) {
            tedf.setTec(tec);
            tedf.setCard(this);
            tedf.show(fm, "Eliminate Tile");
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
