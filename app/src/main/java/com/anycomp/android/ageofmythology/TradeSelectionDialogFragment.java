package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.anycomp.android.ageofmythology.model.bank.Bank;

import java.util.ArrayList;

/**
 * Created by mike on 4/3/15.
 */
public class TradeSelectionDialogFragment extends DialogFragment {

    public static final String TAG = "TradeSelectionDialog";

    private TradeSelectionController controller;

    public void setController(TradeSelectionController controller) {
        this.controller = controller;
    }

    public static TradeSelectionDialogFragment newInstance(TradeSelectionController tsc) {
        TradeSelectionDialogFragment tsdf = new TradeSelectionDialogFragment();
        tsdf.setController(tsc);

        return tsdf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_trade_selection_dialog, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v);

        // Create the AlertDialog object and return it
        final Spinner favorSpinner = (Spinner) v.findViewById(R.id.bank_favor_spinner);
        final Spinner foodSpinner = (Spinner) v.findViewById(R.id.bank_food_spinner);
        final Spinner goldSpinner = (Spinner) v.findViewById(R.id.bank_gold_spinner);
        final Spinner woodSpinner = (Spinner) v.findViewById(R.id.bank_wood_spinner);
        final Spinner playerResourceSpinner = (Spinner) v.findViewById(R.id.player_resource_spinner);
        final Spinner playerNumberSpinner = (Spinner) v.findViewById(R.id.player_number_spinner);

        Bank bank = controller.getBank();

        // bank spinners
        favorSpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                                R.layout.support_simple_spinner_dropdown_item,
                                buildStringArray(bank.getFavor())));
        foodSpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                               R.layout.support_simple_spinner_dropdown_item,
                               buildStringArray(bank.getFood())));
        goldSpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                               R.layout.support_simple_spinner_dropdown_item,
                               buildStringArray(bank.getGold())));
        woodSpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                               R.layout.support_simple_spinner_dropdown_item,
                               buildStringArray(bank.getWood())));

        // player spinners
        playerResourceSpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                                         R.layout.support_simple_spinner_dropdown_item,
                                         new String[] {"Favor", "Food", "Gold", "Wood"}));
        playerNumberSpinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                                       R.layout.support_simple_spinner_dropdown_item,
                                       buildStringArray(5)));

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO: perform trade (via controller)
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //final Button closeButton = (Button) v.findViewById(R.id.close_button);
        return builder.create();
    }

    /** Utility function used above. */
    private String[] buildStringArray(int n) {
        String[] strings = new String[n+1];
        for (int i = 0; i <= n; i++) {
            strings[i] = "" + i;
        }
        return strings;
    }

}
