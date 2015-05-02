package com.anycomp.android.ageofmythology.model.card;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.MainActivity;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.RecruitSelectionController;
import com.anycomp.android.ageofmythology.RecruitSelectionDialogFragment;
import com.anycomp.android.ageofmythology.model.age.Age;
import com.anycomp.android.ageofmythology.model.age.ArchaicAge;
import com.anycomp.android.ageofmythology.model.age.ClassicalAge;
import com.anycomp.android.ageofmythology.model.age.HeroicAge;
import com.anycomp.android.ageofmythology.model.age.MythicAge;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.specific.Pharaoh;
import com.anycomp.android.ageofmythology.model.unit.specific.Priest;
import com.anycomp.android.ageofmythology.model.unit.specific.SonOfOsiris;

/**
 * Created by mike on 5/1/15.
 */
public class GodEgyptRecruitCard extends RandomRecruitCard implements God {

    private static final String TAG = "GodEgyptRecruit";

    public GodEgyptRecruitCard(PermanentTradeCard card) {
        setName("GodEgyptRecruit");
        this.card=card;
        setImagePath(R.drawable.card_rand_egypt_recruit_osiris);
        setValue(4);
        favorCost = 2;  // must!! it is declared in RandomCard.java
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
            //For ai, if you can pay, they play god otherwise normal.
            if(payFavor()) {
                playGod();
            } else {
                playNormal();
            }
        }
    }

    @Override
    public void playGod() {
        Age currentAge = pc.getCurrentPlayer().getAge();
        if (currentAge instanceof ArchaicAge) {
            // play normal
            playNormal();
        }
        else if (pc.getCurrentPlayer().getFavorCube().getValue() >= 2) {
            Unit hero = null;
            if (currentAge instanceof ClassicalAge) {
                hero = new Priest();
            } else if (currentAge instanceof HeroicAge) {
                hero = new Pharaoh();
            } else if (currentAge instanceof MythicAge) {
                hero = new SonOfOsiris();
            }

            if (hero != null) {
                pc.getCurrentPlayer().getArmy().add(hero);
                Log.i(TAG, "Added " + hero.getName() + " to player's army.");
            }
            else {
                Log.i(TAG, "Unable to add hero.");
            }
        }
    }

    @Override
    public void playNormal() {
        new PermanentRecruitCard(this).play(fm, pc);
    }

    @Override
    public boolean payFavor() {
        return false;
    }
}
