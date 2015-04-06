package com.anycomp.android.ageofmythology.model.card;


import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.RecruitSelectionController;
import com.anycomp.android.ageofmythology.RecruitSelectionDialogFragment;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentRecruitCard extends PermanentActionCard {

    public static final String TAG = "PermanentRecruitCard";

    public PermanentRecruitCard(Culture culture) {
        setName("Recruit");
        setCulture(culture);
        setImagePath(culture.getPermanentRecruitCardImage());
    }


    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        Log.i(TAG, "called play()");
        openRecruitPopup(fm, pc);
    }

    private void openRecruitPopup(FragmentManager fm, PlayerController pc) {
        RecruitSelectionController rsc = RecruitSelectionController.getInstance(pc);
        rsc.playRecruitCard(this);
        RecruitSelectionDialogFragment rsdf = RecruitSelectionDialogFragment.newInstance(rsc);
        rsdf.show(fm, TAG);
    }

}
