package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.FourthActivityDialogFragment;
import com.anycomp.android.ageofmythology.GodCardDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.player.Player;

/**
 * Created by bert on 5/4/15.
 */
public class GodNorseNextAgeCard extends RandomNextAgeCard implements God {
    public GodNorseNextAgeCard(PermanentNextAge card) {
        setName("GodNorseNextAge");
        this.card = card;
        setImagePath(R.drawable.card_rand_norse_age_odin);
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
        Player player = pc.getCurrentPlayer();

        pc.setFourth(pc.getCurrentPlayerID());

        // Display dialog if human player
        if (player.getName().equals("user")) {
            FourthActivityDialogFragment fragment
                    = FourthActivityDialogFragment.newInstance();

            fragment.setCard(this);
            fragment.show(fm, "Extra Action");
        }

        if (!player.getName().equals("user")) {
            pc.nextRound();
        }
    }

    @Override
    public void playNormal() {
        PermanentNextAge.godPlay(fm, pc);
//        pc.nextRound();
    }

    @Override
    public boolean payFavor() { return pay(); }

    @Override
    public boolean checkAge() {
        return true;
    }

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
