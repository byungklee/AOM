package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.anycomp.android.ageofmythology.AttackController;
import com.anycomp.android.ageofmythology.model.area.AreaType;

/**
 * Created by byung on 4/7/15.
 */
public class PickAttackAreaDialogFragment extends DialogFragment {
    AttackController ac;
    public void setAttackController(AttackController ac) {
        this.ac = ac;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_pick_attack_area,null);

        Button holdingArea = (Button) v.findViewById(R.id.holding_area);
        Button cityArea = (Button) v.findViewById(R.id.city_area);
        Button productionArea = (Button) v.findViewById(R.id.production_area);

        holdingArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.setAttackArea(AreaType.HOLDING);
                next();
            }
        });
        cityArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.setAttackArea(AreaType.CITY);
                next();
            }
        });
        productionArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.setAttackArea(AreaType.PRODUCTION);
                next();
            }
        });

        builder.setView(v);

        return builder.create();
    }

    public void next() {
        dismiss();
        ac.openPickBattleUnitDialog(true);
    }
}
