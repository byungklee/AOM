package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.RecruitSelectionController;
import com.anycomp.android.ageofmythology.RecruitSelectionDialogFragment;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.unit.Mortal;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.specific.Huskarl;
import com.anycomp.android.ageofmythology.model.unit.specific.Jarl;
import com.anycomp.android.ageofmythology.model.unit.specific.ThrowingAxeman;
import com.anycomp.android.ageofmythology.model.unit.specific.Toxotes;

import java.util.ArrayList;

/**
 * Created by mike on 5/1/15.
 */
public class GodGreekRecruitCard extends RandomRecruitCard implements God {

    private static final String TAG = "GodGreekRecruit";

    public GodGreekRecruitCard(RandomRecruitCard card) {
        setName(TAG);
        this.card=card;
        setImagePath(R.drawable.card_rand_greek_recruit_apollo);
        setValue(4);
        favorCost = 1;  // must!! it is declared in RandomCard.java
    }

    public Culture getCulture() { return card.getCulture(); }

    //User call askgodpower dialogframent, and setGod(this) is must.
    @Override
    public void play(FragmentManager fm, PlayerController pc) {

        // must!! they are declared in RandomCard.java
        this.pc = pc;
        this.fm = fm;

        if(!isPlayed()) {
            setPlayed(true);

            //Calling askgodpower; You don't need to worry about the implementation detail in AskGodPowerDialogFragment.
            //What happens is if an user clicks no or has not enough favor cube, it calls playNormal(); otherwise, playGod().
            AskGodPowerUseDialogFragment agpud = new AskGodPowerUseDialogFragment();
            agpud.setGod(this);
            agpud.show(fm, "UseGod");
        }
    }

    //AI choose to play.
    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            //For ai, if you can pay, then play god.
            if(payFavor()) {
                // gain two toxotes
                pc.getCurrentPlayer().getArmy().add(new Toxotes());
                pc.getCurrentPlayer().getArmy().add(new Toxotes());
            }

            // Just play normal whether or not ai can afford god power
            new PermanentRecruitCard(this).aiPlay(fm, pc);

        }
    }

    @Override
    public void playGod() {
        if (pc.getCurrentPlayer().getFavorCube().getValue() >= favorCost) {

            RecruitSelectionController rsc = RecruitSelectionController.getInstance(pc);
            rsc.setRecruitCard(this);
            RecruitSelectionDialogFragment rsdf = RecruitSelectionDialogFragment.newInstance(rsc);
            rsdf.setCount(2);

            // only display the mortal units you can choose from
            ArrayList<Unit> toxotes = new ArrayList<>();
            // add a toxote to the list as that is the only option with this card
            Unit toxote = new Toxotes();
            toxote.setCost(0, 0, 0, 0);
            toxotes.add(toxote);

            // display only the mortal type units

            rsdf.setPlayerController(pc);
            rsdf.setAdapterContent(toxotes);
            rsdf.show(fm, TAG);

//            if (mortal1 != null) {
//                pc.getCurrentPlayer().getArmy().add(mortal1);
//                Log.i(TAG, "Added " + mortal1.getName() + " to player's army.");
//            }
//            if (mortal2 != null) {
//                pc.getCurrentPlayer().getArmy().add(mortal2);
//                Log.i(TAG, "Added " + mortal2.getName() + " to player's army.");
//            }
//            else {
//                Log.i(TAG, "units were null for some reason...");
//            }
        }
        //playNormal();
    }

    @Override
    public void playNormal() {
        new PermanentRecruitCard(this).play(fm, pc);
    }

    @Override
    public boolean payFavor() {
        return pay();
    }

    @Override
    public boolean checkAge() {
        return true;
    }

    @Override
    public String toString() {
        return TAG;
    }
}
