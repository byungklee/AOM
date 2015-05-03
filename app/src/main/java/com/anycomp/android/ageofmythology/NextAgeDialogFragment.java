package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
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

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (nac.check()) {
//            makeToast();
//        }
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (nac.check()) {
            makeToast();
        }
        //return super.onCreateDialog(savedInstanceState);

        return new AlertDialog.Builder(getActivity()).setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        nac.nextRound();
    }



    public void makeToast() {
        Context context = getActivity().getApplicationContext();
        CharSequence text = "Advanced to " + nac.getAge().getName() + " age";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
