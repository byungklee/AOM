package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by byung on 4/6/15.
 */
public class BattleSceneDialogFragment extends DialogFragment {

    PlayerController pc;
    AttackController ac;

    Button mRetreatButton;

    public BattleSceneDialogFragment() {}
    public void setPlayerController(PlayerController pc) {
        this.pc = pc;
    }
    public void setAttackController(AttackController ac) {
        this.ac = ac;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //View v = inflater.inflate(R.layout.fragment_battle_scene, null);

        //TO DO:

        return super.onCreateDialog(savedInstanceState);
    }
}
