package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;

import com.anycomp.android.ageofmythology.model.player.Player;

import java.util.List;

/**
 * Created by byung on 4/25/15.
 */
public class WinnerDialogFragment extends DialogFragment {
    PlayerController pc;
    Callback c;
    Player p;
    void setCallback(Callback c) {
        this.c = c;
    }
    void setPlayer(Player p) {
        this.p = p;
    }

    void setPlayerController(PlayerController pc) {
        this.pc= pc;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        StringBuilder sb = new StringBuilder();
        List<Player> l = pc.getPlayers();
        sb.append(l.get(0).getName() + "(" +  l.get(0).getCulture().getName()+")"+"'s victory cubes: " +
                  l.get(0).getVictoryCube().getValue() + "\n");
        sb.append(l.get(1).getName() + "(" +  l.get(1).getCulture().getName()+")" + "'s victory cubes: " +
                  l.get(1).getVictoryCube().getValue()+ "\n");
        sb.append(l.get(2).getName() + "(" +  l.get(2).getCulture().getName()+")" + "'s victory cubes: " +
                  l.get(2).getVictoryCube().getValue()+ "\n");
        if(p == null) {
            sb.append("Draw!");
        } else
            sb.append("Winner is " + p.getName() + "!");
//        builder.setMessage("Winner is " + p.getName());
        builder.setMessage(sb.toString());
        builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                c.callback();
            }
        });

        return builder.create();
    }
}
