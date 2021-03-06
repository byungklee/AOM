package com.anycomp.android.ageofmythology;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PickBattleUnitDialogFragment extends DialogFragment {

    PlayerController pc;
    boolean isHumanAttacking;
    private TextView title;
    private GridView gridview;
    private Button button;
    private AttackController ac;
    private Callback callback;

    public PickBattleUnitDialogFragment() {
        // Required empty public constructor
    }

    public void setPlayerController(PlayerController pc) {
        this.pc = pc;
    }
    public void setAttackController(AttackController ac) {
        this.ac = ac;
    }
    public void setIsHumanAttacking(boolean b) {
        isHumanAttacking = b;
    }
    public void setCallback(Callback callback) {this.callback = callback;}


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_pick_battle_unit, null);
        title = (TextView) v.findViewById(R.id.title);
        if(isHumanAttacking) {
            title.setText("Pick units for your attack");
        } else
            title.setText("Pick units for your defense");

        gridview = (GridView)v.findViewById(R.id.gridview);
        button = (Button) v.findViewById(R.id.okay_button);

        gridview.setAdapter(new BattleUnitAdapter(getActivity().getApplicationContext(), pc.getHumanPlayer().getArmy()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(ac.pickBattleUnitCard(isHumanAttacking, position)) {
                    ((BattleUnitAdapter) gridview.getAdapter()).notifyDataSetChanged();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isHumanAttacking) {
                    ac.aiPickBattleUnitCard(true);
                }
                PickBattleUnitDialogFragment.this.dismiss();
            }
        });

        builder.setView(v);
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        ac.openBattleSceneDialog();
    }
}
