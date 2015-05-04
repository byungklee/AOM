package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;
import android.content.Context;

import com.anycomp.android.ageofmythology.GatherController;
import com.anycomp.android.ageofmythology.GatherDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PermanentGatherCard extends PermanentActionCard  {
    public PermanentGatherCard(Culture culture) {
        setName("Gather");
        setCulture(culture);
        setImagePath(culture.getPermanentGatherCardImage());
    }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        if(!isPlayed()) {
            setPlayed(true);
            GatherController gc = GatherController.getInstance(pc);
            gc.playGatherCard(this);
            GatherDialogFragment gdf = GatherDialogFragment.newInstance(gc);
            gdf.show(fm, "Gather Dialog");
        }
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        GatherController gc = GatherController.getInstance(pc);

        gc.gather();
        gc.nextRound();
    }
}
