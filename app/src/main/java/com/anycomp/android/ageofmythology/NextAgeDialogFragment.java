package com.anycomp.android.ageofmythology;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by bert on 4/8/15.
 */
public class NextAgeDialogFragment extends DialogFragment {
    private NextAgeController nac;

    public static NextAgeDialogFragment newInstance(
            NextAgeController c) {
        NextAgeDialogFragment fragment =
                new NextAgeDialogFragment();

        fragment.setController(c);
        return fragment;
    }

    public NextAgeDialogFragment() {}

    /* Set the controller */
    private void setController(NextAgeController nac) {
        this.nac = nac;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (nac.check()) {
            makeToast();
        }
    }

    public void makeToast() {
        Context context = getActivity().getApplicationContext();
        CharSequence text = "Advanced to " + nac.getAge().getName() + " age";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
