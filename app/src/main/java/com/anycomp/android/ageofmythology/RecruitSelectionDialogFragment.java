package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by mike on 4/3/15.
 */
public class RecruitSelectionDialogFragment extends DialogFragment {

    public static final String TAG = "Recruit Selection Dialog";
    private PlayerController pc;
    private RecruitSelectionController controller;

    public void setController(RecruitSelectionController controller) {
        this.controller = controller;
    }

    public void setPlayerController(PlayerController pc) {
        this.pc = pc;
    }

    public static RecruitSelectionDialogFragment newInstance(RecruitSelectionController rsc) {
        RecruitSelectionDialogFragment rsdf = new RecruitSelectionDialogFragment();
        rsdf.setController(rsc);

        return rsdf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_recruit_selection_dialog, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v);

        // Create the AlertDialog object and return it
        final GridView gridview = (GridView) v.findViewById(R.id.gridview);

        String cultureName = pc.getHumanPlayer().getCulture().getName();
        gridview.setAdapter(new RecruitSelectionAdapter(getActivity(), controller.getRecruitListByCulture(cultureName)));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // adds the recruit to the player's army if they have sufficient resources
                // otherwise, it returns false and displays the alert message.
                if (!controller.addRecruit(position)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Not available for you.")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                    AlertDialog ad = builder.create();
                    ad.show();
                }
            }
        });

        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //final Button closeButton = (Button) v.findViewById(R.id.close_button);
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        pc.nextRound();
    }
}
