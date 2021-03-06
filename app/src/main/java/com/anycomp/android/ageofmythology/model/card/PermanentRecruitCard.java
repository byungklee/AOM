package com.anycomp.android.ageofmythology.model.card;


import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.RecruitSelectionController;
import com.anycomp.android.ageofmythology.RecruitSelectionDialogFragment;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.culture.Egyptian;
import com.anycomp.android.ageofmythology.model.culture.Norse;
import com.anycomp.android.ageofmythology.model.player.Player;

public class PermanentRecruitCard extends PermanentActionCard {

    public static final String TAG = "PermanentRecruitCard";

    public PermanentRecruitCard(Culture culture) {
        setName("Recruit");
        setCulture(culture);
        setImagePath(culture.getPermanentRecruitCardImage());
        setValue(2);
    }

    /**
     * Use this constructor to create a custom version of this card (for use with God random card when the God option is unavailable)
     * @param randomRecruitCard The God card to be used as a normal card.
     */
    public PermanentRecruitCard(RandomRecruitCard randomRecruitCard) {
        setName(randomRecruitCard.getName());
        setCulture(randomRecruitCard.getCulture());
        setImagePath(randomRecruitCard.getImagePath());
        setValue(randomRecruitCard.getValue());
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        Log.i(TAG, "called play()");
        if(!isPlayed()) {
            setPlayed(true);
            openRecruitPopup(fm, pc);
        }
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController player) {
        RecruitSelectionController rsc = RecruitSelectionController.getInstance(player);
        Player p = player.getCurrentPlayer();
        //Ai always to try to get 5th and 6th in the list.
        rsc.setRecruitCard(this);
        rsc.addRecruit(rsc.getRecruitListByCulture(p.getCulture().getName()).get(5));
        rsc.addRecruit(rsc.getRecruitListByCulture(p.getCulture().getName()).get(6));
        player.nextRound();
    }

    private void openRecruitPopup(FragmentManager fm, PlayerController pc) {
        RecruitSelectionController rsc = RecruitSelectionController.getInstance(pc);
        rsc.setRecruitCard(this);
        RecruitSelectionDialogFragment rsdf = RecruitSelectionDialogFragment.newInstance(rsc);
        rsdf.setCount(getValue());
        rsdf.setPlayerController(pc);
        rsdf.show(fm, TAG);
    }

}
