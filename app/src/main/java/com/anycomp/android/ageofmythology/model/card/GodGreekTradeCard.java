package com.anycomp.android.ageofmythology.model.card;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.util.Log;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.ProfitResourceDialogFragment;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.RecruitSelectionController;
import com.anycomp.android.ageofmythology.RecruitSelectionDialogFragment;
import com.anycomp.android.ageofmythology.TakeResourceDialogFragment;
import com.anycomp.android.ageofmythology.TradeSelectionController;
import com.anycomp.android.ageofmythology.TradeSelectionDialogFragment;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.specific.Toxotes;

import java.util.ArrayList;

/**
 * Created by mike on 5/1/15.
 */
public class GodGreekTradeCard extends RandomTradeCard implements God {

    private static final String TAG = "GodGreekTrade";

    public GodGreekTradeCard(RandomTradeCard card) {
        setName(TAG);
        this.card=card;
        setImagePath(R.drawable.card_rand_greek_trade_hermes);
        setValue(0);
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

            }
            // If ai cannot afford to play god, then play normal
            else {
//                new PermanentTradeCard(this).aiPlay(fm, pc);
            }
        }
    }

    @Override
    public void playGod() {
        ProfitResourceDialogFragment prdf = new ProfitResourceDialogFragment();
        prdf.setCard(this);
        prdf.setController(pc);
        prdf.setMaxResourceCanTake(4);
        prdf.show(fm, TAG);
    }

    @Override
    public void playNormal() {
        Log.i(TAG, "called playNormal()");
        TradeSelectionController tsc = new TradeSelectionController(pc);
        tsc.playTradeCard(this);
        TradeSelectionDialogFragment tsdf = TradeSelectionDialogFragment.newInstance(tsc);
        tsdf.show(fm, TAG);
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
