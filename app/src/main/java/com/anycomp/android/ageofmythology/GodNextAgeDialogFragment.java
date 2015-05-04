package com.anycomp.android.ageofmythology;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by bert on 5/3/15.
 */
public class GodNextAgeDialogFragment extends DialogFragment {
    private NextAgeController nac;

    public static GodNextAgeDialogFragment newInstance(
            NextAgeController c) {
        GodNextAgeDialogFragment fragment =
                new GodNextAgeDialogFragment();

        fragment.setController(c);
        return fragment;
    }

    public GodNextAgeDialogFragment() {}

    /* Set the controller */
    private void setController(NextAgeController nac) {
        this.nac = nac;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (nac.godCheck()) {
            makeToast();
        } else {
            denyToast();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        //nac.nextRound();
    }

    public void makeToast() {
        Context context = getActivity().getApplicationContext();
        CharSequence text = "Advanced to " + nac.getAge().getName() + " age";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void denyToast() {
        Context context = getActivity().getApplicationContext();
        CharSequence text = "Insufficient Resources";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
