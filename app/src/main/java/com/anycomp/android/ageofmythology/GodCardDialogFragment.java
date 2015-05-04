package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.anycomp.android.ageofmythology.model.card.God;

/**
 * Created by bert on 4/29/15.
 */
public class GodCardDialogFragment extends DialogFragment {
    God card;
    PlayerController pc;

    public void setGod(God card) {
        this.card = card;
    }

    public void setPlayerController(PlayerController pc) { this.pc = pc; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Do you want to use the card's non-God action?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                card.playNormal();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pc.nextRound();
                };
        });
        return builder.create();
    }
}
