package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.anycomp.android.ageofmythology.model.player.Player;

/**
 * Created by byung on 4/25/15.
 */
public class TakeResourceDialogFragment extends DialogFragment {

    private int maxResourceCanTake = 5;
    private int hasTakenSoFar = 0;

    private Player defender; // Loser
    private Player attacker; // Taker
    private PlayerController pc;

    NumberPicker favor;
    NumberPicker food;
    NumberPicker gold;
    NumberPicker wood;

    int maxFavor;
    int maxFood;
    int maxGold;
    int maxWood;

    public void setDefender(Player p) {
        defender = p;
    }
    public void setOffender(Player p) {
        attacker = p;
    }
    public void setPC(PlayerController pc) {
        this.pc = pc;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_take_resource_dialog, null);
        favor = (NumberPicker) v.findViewById(R.id.favor_picker);
        food = (NumberPicker) v.findViewById(R.id.food_picker);
        gold = (NumberPicker) v.findViewById(R.id.gold_picker);
        wood = (NumberPicker) v.findViewById(R.id.wood_picker);

        init();
        favor.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //hasTakenSoFar = favor.getValue() + food.getValue() + gold.getValue() + wood.getValue();
                refresh();
            }
        });
        wood.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //hasTakenSoFar = favor.getValue() + food.getValue() + gold.getValue() + wood.getValue();
                refresh();
            }
        });
        gold.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //hasTakenSoFar = favor.getValue() + food.getValue() + gold.getValue() + wood.getValue();
                refresh();
            }
        });
        wood.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //hasTakenSoFar = favor.getValue() + food.getValue() + gold.getValue() + wood.getValue();
                refresh();
            }
        });

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int favorQ = Math.min(favor.getValue(),5);
                int foodQ = Math.min(food.getValue(),5);
                int goldQ = Math.min(gold.getValue(),5);
                int woodQ = Math.min(wood.getValue(),5);
                defender.spendGold(goldQ);
                defender.spendFavor(favorQ);
                defender.spendWood(woodQ);
                defender.spendFood(foodQ);

                attacker.takeFood(foodQ);
                attacker.takeFavor(favorQ);
                attacker.takeWood(woodQ);
                attacker.takeGold(goldQ);

            }
        });

        builder.setView(v);
        return builder.create();
    }

    public void init() {
        maxFavor = Math.min(maxResourceCanTake, defender.getFavorCube().getValue());
        maxFood = Math.min(maxResourceCanTake, defender.getFoodCube().getValue());
        maxGold = Math.min(maxResourceCanTake, defender.getGoldCube().getValue());
        maxWood = Math.min(maxResourceCanTake, defender.getWoodCube().getValue());

        favor.setMaxValue(maxFavor);
        favor.setMinValue(0);
        food.setMaxValue(maxFood);
        food.setMinValue(0);
        gold.setMaxValue(maxGold);
        gold.setMinValue(0);
        wood.setMaxValue(maxWood);
        wood.setMinValue(0);
    }

    public void refresh() {
        hasTakenSoFar = favor.getValue() + food.getValue() + gold.getValue() + wood.getValue();
        favor.setMaxValue(Math.min(maxResourceCanTake - hasTakenSoFar + favor.getValue(), maxFavor));
        food.setMaxValue(Math.min(maxResourceCanTake - hasTakenSoFar + food.getValue(), maxFood));
        gold.setMaxValue(Math.min(maxResourceCanTake - hasTakenSoFar + gold.getValue(), maxGold));
        wood.setMaxValue(Math.min(maxResourceCanTake - hasTakenSoFar + wood.getValue(), maxWood));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        pc.nextRound();
    }

}
