package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.media.Image;
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

import java.util.Random;


public class VictoryCardDialogFragment extends DialogFragment {

    private PlayerController pc;
    private boolean place;
    private int counter = 0;
    private int startingPlayerIndex;
    private Callback onVictoryCardDialogEnd;
    private Button button;
    private GridView gridview;
    private ImageClickCallback callback = new ImageClickCallback() {

        @Override
        public void callback(int index) {
            if (!place) {
                return;
            }
            if(pc.getPlayers().get(startingPlayerIndex) == pc.getHumanPlayer() && counter < 3) {
                System.out.println("Human place a victory cube");
                pc.getVictoryCardDeck().addCube(index);
                counter++;
                startingPlayerIndex = (startingPlayerIndex + 1) % 3;

                if (counter < 3) {
                    aiWork();
                } else {
                    button.setEnabled(true);
                }
                ((BaseAdapter) gridview.getAdapter()).notifyDataSetChanged();
                ((VictoryCardAdapter) gridview.getAdapter()).refresh();

            }
        }
    };


    public interface ImageClickCallback {
        void callback(int index);
    }

    public void setPlayerController(PlayerController c) {
        pc = c;
    }
    public void setOnVictoryCardDialogEnd(Callback c) {
        onVictoryCardDialogEnd = c;
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


        gridview = (GridView) v.findViewById(R.id.gridview);
        button = (Button) v.findViewById(R.id.confirm_button);
        final VictoryCardAdapter vca = new VictoryCardAdapter(getActivity().getApplicationContext(),
                (pc.getVictoryCardDeck()), callback);

        System.out.println("PLACE: " + place);

//        okayButton.setEnabled(false);


        gridview.setAdapter(vca);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                System.out.println("Place " + place);
                if (!place) {
                    return;
                }
                Toast.makeText(getActivity().getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
//                ((ImageView) v).setVisibility(View.INVISIBLE);
                //TileManager.getInstance().getTileSelectionDeck().remove(position);
                //((BaseAdapter) gridview.getAdapter()).notifyDataSetChanged();
//                if(pc.getPlayers().get(startingPlayerIndex) == pc.getHumanPlayer() && counter < 3) {
//                    System.out.println("Human place a victory cube");
//                    pc.getVictoryCardDeck().addCube(position);
//                    counter++;
//                    startingPlayerIndex = (startingPlayerIndex + 1) % 3;
//
//                    if (counter < 3) {
//                        aiWork();
//                    } else {
//                        button.setEnabled(true);
//                    }
//                    ((BaseAdapter) gridview.getAdapter()).notifyDataSetChanged();
//                    ((VictoryCardAdapter) gridview.getAdapter()).refresh();
//
//                }

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(counter == 1) {
//                    button.setText("Okay");
//                } else if(counter == 2) {
//
//
//                    return;
//                }
                VictoryCardDialogFragment.this.dismiss();
                onVictoryCardDialogEnd.callback();
//

//                gridview.setAdapter(new VictoryCardAdapter(getActivity().getApplicationContext(),
//                        ((Player)pc.getPlayers().get(startingPlayerIndex)).getVictoryCardDeck()));

            }
        });

        if(place)
            button.setEnabled(false);
        aiWork();
        return builder.create();
    }

    private void aiWork() {
        while(((Player)pc.getPlayers().get(startingPlayerIndex)).getName().contains("AI") && counter < 3) {
            System.out.println("AI is placing victory cube on card");
            int randomNum = new Random().nextInt(4);
            pc.getVictoryCardDeck().addCube(randomNum);
            ((VictoryCardAdapter) gridview.getAdapter()).refresh();
            counter++;
            startingPlayerIndex = (startingPlayerIndex + 1)%3;
            if(counter <=3) {
                button.setEnabled(true);
            }
        }
    }
}
