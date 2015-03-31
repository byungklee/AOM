package com.anycomp.android.ageofmythology;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.anycomp.android.ageofmythology.model.player.Player;

public class PlayCardDialogFragment extends DialogFragment {

    private Player human;
    private PlayerController pc;

    public PlayCardDialogFragment() {
        // Required empty public constructor
    }


    public void setPlayer(Player human) {
        this.human = human;
    }
    public void setPlayerController(PlayerController pc) {
        this.pc = pc;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_play_card, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v);
        final GridView gridview = (GridView)v.findViewById(R.id.gridview);

        gridview.setAdapter(new PlayCardAdapter(getActivity().getApplicationContext(),
                human.getHand()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
                human.getHand().getCardAt(position).play(getFragmentManager(), pc);



                human.getHand().removeCardAt(position);
                ((BaseAdapter) gridview.getAdapter()).notifyDataSetChanged();


            }
        });
        return builder.create();
    }


}
