package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.anycomp.android.ageofmythology.model.bank.Bank;

/**
 * Created by byung on 5/3/15.
 */
public class BankInfoDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bank bank = Bank.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_bank_dialog, null);
        ((TextView) v.findViewById(R.id.food)).setText("Food: " + bank.getFood());
        ((TextView) v.findViewById(R.id.wood)).setText("Wood: " + bank.getWood());
        ((TextView) v.findViewById(R.id.gold)).setText("Gold: " + bank.getGold());
        ((TextView) v.findViewById(R.id.favor)).setText("Favor: " + bank.getFavor());
        ((TextView) v.findViewById(R.id.victory)).setText("Victory: " + bank.getVictoryCubeCount());



        builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setView(v);
        return builder.create();
    }


}
