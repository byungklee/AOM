package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.anycomp.android.ageofmythology.model.player.Player;


public class VictoryCardDialogFragment extends DialogFragment {

    private PlayerController pc;
    private boolean place;
    private int counter = 0;
    private int startingPlayerIndex;

    public void setPlayerController(PlayerController c) {
        pc = c;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }
    public void setStartingPlayerIndex(int index) {
        startingPlayerIndex = index;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_victory_card_view, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        builder.setView(v);
        // Create the AlertDialog object and return it


        final GridView gridview = (GridView) v.findViewById(R.id.gridview);

//        okayButton.setEnabled(false);

        gridview.setAdapter(new VictoryCardAdapter(getActivity().getApplicationContext(),
                ((Player)pc.getPlayers().get(startingPlayerIndex)).getVictoryCardDeck()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (!place) {
                    return;
                }
                Toast.makeText(getActivity().getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
//                ((ImageView) v).setVisibility(View.INVISIBLE);
                //TileManager.getInstance().getTileSelectionDeck().remove(position);
                //((BaseAdapter) gridview.getAdapter()).notifyDataSetChanged();

                ((BaseAdapter) gridview.getAdapter()).notifyDataSetChanged();

            }
        });

        final Button button = (Button) v.findViewById(R.id.confirm_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter == 1) {
                    button.setText("Okay");
                } else if(counter == 2) {
                    VictoryCardDialogFragment.this.dismiss();
                    return;
                }

                counter++;
                startingPlayerIndex = (startingPlayerIndex + 1)%3;
                gridview.setAdapter(new VictoryCardAdapter(getActivity().getApplicationContext(),
                        ((Player)pc.getPlayers().get(startingPlayerIndex)).getVictoryCardDeck()));

            }
        });
        return builder.create();
    }
}
