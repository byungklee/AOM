package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.anycomp.android.ageofmythology.model.bank.Bank;
import com.anycomp.android.ageofmythology.model.card.God;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;

/**
 * Created by byung on 4/25/15.
 */
public class ProfitResourceDialogFragment extends DialogFragment {

    private int maxResourceCanTake;

    private Player p; // Loser
    private Bank b; // Taker
    private PlayerController pc;
    private God card;

    NumberPicker favor;
    NumberPicker food;
    NumberPicker gold;
    NumberPicker wood;

    int bankFavor;
    int bankFood;
    int bankGold;
    int bankWood;

    public void setBank(Bank b) { this.b = b; }
    public void setPlayer(Player p) { this.p = p; }
    public void setController(PlayerController pc) { this.pc = pc; }
    public void setMaxResourceCanTake(int maxResourceCanTake) { this.maxResourceCanTake = maxResourceCanTake; }
    public void setCard(God card) { this.card = card; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.b = Bank.getInstance();
        this.p = pc.getCurrentPlayer();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_take_resource_dialog, null);

        ((TextView) (v.findViewById(R.id.text_message))).setText("Choose your profit:");
        favor = (NumberPicker) v.findViewById(R.id.favor_picker);
        food = (NumberPicker) v.findViewById(R.id.food_picker);
        gold = (NumberPicker) v.findViewById(R.id.gold_picker);
        wood = (NumberPicker) v.findViewById(R.id.wood_picker);

        init();

        favor.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                refresh(0);
            }
        });
        food.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                refresh(1);
            }
        });
        gold.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                refresh(2);
            }
        });
        wood.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                refresh(3);
            }
        });

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int favorQ = favor.getValue();
                int foodQ = food.getValue();
                int goldQ = gold.getValue();
                int woodQ = wood.getValue();

//                b.withdraw(ResourceType.FAVOR, favorQ);
//                b.withdraw(ResourceType.FOOD, foodQ);
//                b.withdraw(ResourceType.GOLD, goldQ);
//                b.withdraw(ResourceType.WOOD, woodQ);

                p.takeFood(foodQ);
                p.takeFavor(favorQ);
                p.takeWood(woodQ);
                p.takeGold(goldQ);

                card.playNormal();
            }
        });

        builder.setView(v);
        return builder.create();
    }

    public void init() {
        bankFavor = b.getFavor();
        bankFood = b.getFood();
        bankGold = b.getGold();
        bankWood = b.getWood();

        // to be used as the max values for the pickers
        // if bank has more than 4 of a resource, cap it at 4
        // otherwise, just show how much the bank has
        int favorQ = Math.min(bankFavor, maxResourceCanTake);
        int foodQ = Math.min(bankFood, maxResourceCanTake);
        int goldQ = Math.min(bankGold, maxResourceCanTake);
        int woodQ = Math.min(bankWood, maxResourceCanTake);

        favor.setMaxValue(favorQ);
        favor.setMinValue(0);
        food.setMaxValue(foodQ);
        food.setMinValue(0);
        gold.setMaxValue(goldQ);
        gold.setMinValue(0);
        wood.setMaxValue(woodQ);
        wood.setMinValue(0);
    }

    private void refresh(int i) {
        int hasTakenSoFar = favor.getValue() + food.getValue() + gold.getValue() + wood.getValue();
        int maxFavor = Math.min(favor.getValue()+maxResourceCanTake-hasTakenSoFar, b.getFavor());
        int maxFood = Math.min(food.getValue()+maxResourceCanTake-hasTakenSoFar, b.getFood());
        int maxGold = Math.min(gold.getValue()+maxResourceCanTake-hasTakenSoFar, b.getGold());
        int maxWood = Math.min(wood.getValue()+maxResourceCanTake-hasTakenSoFar, b.getWood());

        if (i!=0) favor.setMaxValue(maxFavor);
        if (i!=1) food.setMaxValue(maxFood);
        if (i!=2) gold.setMaxValue(maxGold);
        if (i!=3) wood.setMaxValue(maxWood);
    }

    @Override
    public void dismiss() {
        super.dismiss();
//        pc.nextRound();

    }
}
