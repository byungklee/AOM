package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.card.God;

/**
 * Created by byung on 4/24/15.
 */
public class AskGodPowerUseDialogFragment extends DialogFragment {

    God card;

    public void setGod(God card) {
        this.card = card;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Would you use God Power?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(card.payFavor()) {
                    card.playGod();
                } else {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                    builder2.setMessage("You don't have enough favor, so playing normal.");
                    builder2.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            card.playNormal();
                        }
                    });
                    builder2.show();
                }

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               card.playNormal();
            }
        });

        return builder.create();
    }
}
