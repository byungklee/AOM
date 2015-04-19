package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by byung on 4/18/15.
 */
public class CurrentUnitListDialogFragment extends DialogFragment {

    PlayerController pc;
    public void setPlayerController(PlayerController pc) {
        this.pc = pc;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_pick_battle_unit,null);
        GridView gridview = (GridView)v.findViewById(R.id.gridview);

        gridview.setAdapter(new BattleUnitAdapter(getActivity().getApplicationContext(), pc.getHumanPlayer().getArmy()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        ((Button) v.findViewById(R.id.okay_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentUnitListDialogFragment.this.dismiss();
            }
        });
        builder.setView(v);

        return builder.create();
    }
}
