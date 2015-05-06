package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.GatherController;
import com.anycomp.android.ageofmythology.GodCardDialogFragment;
import com.anycomp.android.ageofmythology.GodGatherDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.player.Player;

/**
 * Created by bert on 5/1/15.
 */
public class GodNorseGatherCard extends RandomGatherCard implements God {
    public GodNorseGatherCard(PermanentGatherCard card) {
        setName("GodNorseGather");
        this.card = card;
        setImagePath(R.drawable.card_rand_norse_gather_freya);
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
        Player player = pc.getCurrentPlayer(); // Current player
        int current = pc.getCurrentPlayerID(); // Current player's ID

        for (int i = 0; i < 3; i++) {
            player.takeGold(5);
            pc.setCurrentPlayer((++current) % 3);
            player = pc.getCurrentPlayer();
        }

        if (player.getName().equals("user")) {
            GodCardDialogFragment gcdf = new GodCardDialogFragment();

            gcdf.setGod(this);
            gcdf.setPlayerController(pc);
            gcdf.show(this.fm, "Other Activity");
        } else {
            pc.nextRound();
        }

    }

    @Override
    public void playNormal() {
        GatherController gc = GatherController.getInstance(pc);
        int current = pc.getCurrentPlayerID();

        if (pc.getCurrentPlayer().getName().equals("user")) {
            GodGatherDialogFragment gdf = GodGatherDialogFragment.newInstance(gc);
            gdf.show(fm, "Gather Dialog");
        } else {
            gc.setPick(0);
            gc.gather(true);
            gc.getPlayerController().setCurrentPlayer((++current) % 3);
            gc.gather(false);
            gc.getPlayerController().setCurrentPlayer((++current) % 3);
            gc.gather(false);
            gc.getPlayerController().setCurrentPlayer((++current) % 3);
            pc.nextRound();
        }


    }

    @Override
    public boolean payFavor() { return pay(); }

    @Override
    public boolean checkAge() {
        return true;
    }
}
