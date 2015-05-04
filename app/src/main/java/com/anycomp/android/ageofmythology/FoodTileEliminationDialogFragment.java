package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.anycomp.android.ageofmythology.model.card.GodEgyptNextAgeCard;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.culture.Greek;
import com.anycomp.android.ageofmythology.model.culture.Norse;

/**
 * Created by bert on 5/4/15.
 */
public class FoodTileEliminationDialogFragment extends DialogFragment {
    private TileEliminationController tec;
    private GodEgyptNextAgeCard card;
    private PlayerController pc;

    public void setTec(TileEliminationController tec) { this.tec = tec; }

    public void setCard(GodEgyptNextAgeCard card) { this.card = card; }

    public void setPc(PlayerController pc) { this.pc = pc; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity().getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(
                R.layout.fragment_food_tile_elimination_dialog, null);
        //Culture culture = pc.getCurrentPlayer().getCulture();

        builder.setView(v);

        final GridView opponentGridView1 = (GridView) v.findViewById(
                R.id.opponent_gridView1);
        final GridView opponentGridView2 = (GridView) v.findViewById(
                R.id.opponent_gridView2);

        opponentGridView1.setAdapter(new GreekTileEliminationAdapter(context,
                tec));
        opponentGridView2.setAdapter(new NorseTileEliminationAdapter(context,
                tec));

        opponentGridView1.setOnItemClickListener(new AdapterView
                .OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), ""
                        + position, Toast.LENGTH_SHORT).show();
                tec.greekEliminate(position);
                FoodTileEliminationDialogFragment.this.dismiss();             }
        });

        opponentGridView2.setOnItemClickListener(new AdapterView
                .OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), ""
                        + position, Toast.LENGTH_SHORT).show();
                tec.norseEliminate(position);
                FoodTileEliminationDialogFragment.this.dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        card.otherActivity();
    }
}
