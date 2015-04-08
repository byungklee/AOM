package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.anycomp.android.ageofmythology.model.player.Player;

import java.util.Iterator;

/**
 * Created by byung on 4/5/15.
 */
public class SelectOpponentDialogFragment extends DialogFragment {

    private Button[] opponents;

    private PlayerController pc;
    private AfterSelectOpponentCallback attackCallback;

    public void setPlayerController(PlayerController pc) {
        this.pc = pc;
    }
    public void setAfterSelecttOpponentCallback(AfterSelectOpponentCallback asoc) { this.attackCallback = asoc; }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        /*View v = inflater.inflate(R.layout.fragment_select_opponent,null);
        opponents = new Button[2];
        opponents[0] = (Button) v.findViewById(R.id.button_opponent_1);
        opponents[1] = (Button) v.findViewById(R.id.button_opponent_2);*/
        Iterator it = pc.getPlayers().iterator();
        int counter = 0;
        while(it.hasNext()) {
            Player p = (Player) it.next();
            if(p != pc.getCurrentPlayer()) {
                opponents[counter].setText(p.getCulture().getName());

                counter++;
            }
        }

        for(int index=0;index<opponents.length;index++) {
            opponents[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cultureName = ((Button) v).getText().toString();
                    int i;
                    for(i=0;i<pc.getPlayers().size();i++) {
                        Player p = (Player) pc.getPlayers().get(i);
                        if(p.getCulture().getName().equals(cultureName)) {
                            break;
                        }
                    }
                    SelectOpponentDialogFragment.this.dismiss();
                    attackCallback.callback(i);
                }
            });
        }

        //builder.setView(v);
        return builder.create();
    }
}
