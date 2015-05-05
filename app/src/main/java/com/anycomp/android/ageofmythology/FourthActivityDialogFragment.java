package com.anycomp.android.ageofmythology;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.anycomp.android.ageofmythology.model.card.GodEgyptNextAgeCard;
import com.anycomp.android.ageofmythology.model.card.GodNorseNextAgeCard;

/**
 * Created by bert on 5/4/15.
 */
public class FourthActivityDialogFragment extends DialogFragment {
    GodNorseNextAgeCard card;

    public static FourthActivityDialogFragment newInstance() {
        FourthActivityDialogFragment fragment = new FourthActivityDialogFragment();
        return fragment;
    }

    public FourthActivityDialogFragment() {}

    public void setCard(GodNorseNextAgeCard card) { this.card = card; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeToast();
    }

    public void makeToast() {
        Context context = getActivity().getApplicationContext();
        CharSequence text = "You get a fourth activity this round.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        card.otherActivity();
    }
}
