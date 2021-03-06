package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.player.Player;

import java.util.ArrayList;

/**
 * Created by bert on 4/4/15.
 */
public class GatherDialogFragment extends DialogFragment {
    private GatherController gc;
    private Card card;

    public GatherDialogFragment() {}

    public static GatherDialogFragment newInstance(
            GatherController c) {
        GatherDialogFragment fragment =
                new GatherDialogFragment();

        fragment.setController(c);
        return fragment;
    }

    /* Set the controller */
    private void setController(GatherController gc) {
        this.gc = gc;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        gc.nextRound();
    }

    public void playBuildCard(Card card) { this.card = card; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence[] options = {"DESERT", "FERTILE", "HILL", "MOUNTAIN",
                "SWAMP", "FOREST", "WOOD", "GOLD", "FOOD", "FAVOR"};

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                gc.setPick(which);
            }
        };

        builder.setTitle("Choose a terrain or resource").setSingleChoiceItems(
                options, 0, listener);

        builder.setPositiveButton("Gather", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int current = gc.getPlayerController()
                        .getCurrentPlayerID();

                gc.gather(true);
                gc.getPlayerController().setCurrentPlayer((++current) % 3);
                gc.gather(false);
                gc.getPlayerController().setCurrentPlayer((++current) % 3);
                gc.gather(false);
                gc.getPlayerController().setCurrentPlayer((++current) % 3);
            }
        });

        return builder.create();
    }
}