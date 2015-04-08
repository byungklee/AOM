package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.anycomp.android.ageofmythology.model.player.Player;

import java.util.ArrayList;

/**
 * Created by bert on 4/4/15.
 */
public class GatherDialogFragment extends DialogFragment {
    private GatherController gc;
    private boolean confirmed;

    /* Empty constructor */
    public GatherDialogFragment() { confirmed = false; }

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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.gather_selection).setItems(
                R.array.gather_options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        gc.setPick(which);

                        new AlertDialog.Builder(getActivity())
                                .setTitle(R.string.gather_title)
                                .setMessage(R.string.gather_message)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes,
                                        new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        int current = gc.getPlayerController()
                                                .getCurrentPlayerID();

                                        gc.gather();
                                        gc.getPlayerController()
                                                .setCurrentPlayer(
                                                        (++current) % 3);
                                        gc.gather();
                                        gc.getPlayerController()
                                                .setCurrentPlayer(
                                                        (++current) % 3);
                                        gc.gather();
                                        gc.getPlayerController()
                                                .setCurrentPlayer(
                                                        (++current) % 3);
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();
                    }
                });
        return builder.create();
    }
}