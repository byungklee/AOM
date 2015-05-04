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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.anycomp.android.ageofmythology.model.card.GodGreekNextAgeCard;

/**
 * Created by bert on 5/1/15.
 */
public class TileEliminationDialogFragment extends DialogFragment {
    private TileEliminationController tec;
    private GodGreekNextAgeCard card;

    public void setTec(TileEliminationController tec) { this.tec = tec; }

    public void setCard(GodGreekNextAgeCard card) { this.card = card; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity().getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_tile_elimination_dialog,
                null);

        builder.setView(v);

        final GridView greekGridView = (GridView) v.findViewById(R.id.greek_gridView);
        final GridView norseGridView = (GridView) v.findViewById(R.id.norse_gridView);
        final GridView egyptianGridView = (GridView) v.findViewById(R.id.egyptian_gridView);
        greekGridView.setAdapter(new GreekTileEliminationAdapter(context, tec));
        norseGridView.setAdapter(new NorseTileEliminationAdapter(context, tec));
        egyptianGridView.setAdapter(new EgyptianTileEliminationAdapter(context, tec));

        greekGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
                tec.greekEliminate(position);
                TileEliminationDialogFragment.this.dismiss();
            }
        });

        norseGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
                tec.norseEliminate(position);
                TileEliminationDialogFragment.this.dismiss();
            }
        });

        egyptianGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
                tec.egyptianEliminate(position);
                TileEliminationDialogFragment.this.dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        card.otherActivity();
    }
}
