package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.TradeSelectionController;
import com.anycomp.android.ageofmythology.TradeSelectionDialogFragment;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentTradeCard extends PermanentActionCard {

    public static final String TAG = "PermanentTradeCard";

    public PermanentTradeCard(Culture culture) {
        setName("Trade");
        setCulture(culture);
        setImagePath(culture.getPermanentTradeCardImage());
    }

    public PermanentTradeCard(RandomTradeCard card) {
        setName(card.getName());
        setCulture(card.getCulture());
        setImagePath(card.getImagePath());
        setValue(card.getValue());
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        Log.i(TAG, "called play()");
        if(!isPlayed()) {
            setPlayed(true);
            openTradePopup(fm, pc);
        }
    }

    public void openTradePopup(FragmentManager fm, PlayerController pc) {
        TradeSelectionController tsc = new TradeSelectionController(pc);
        tsc.playTradeCard(this);
        TradeSelectionDialogFragment tsdf = TradeSelectionDialogFragment.newInstance(tsc);
        tsdf.show(fm, TAG);
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController player) {
        player.nextRound();
    }


}
