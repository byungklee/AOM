package com.anycomp.android.ageofmythology;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PickBattleUnitDialogFragment extends DialogFragment {

    PlayerController pc;
    boolean isHumanAttacking;
    private TextView title;
    private GridView gridview;
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


        //TO DO: load available units
        //       onClickListner(take out unit and put it on selectUnit data structure);
        //        place it in attackController

        //pc.getCurrentPlayer().getunit etc



        builder.setView(v);
        return builder.create();
    }
}
