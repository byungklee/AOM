package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by byung on 4/19/15.
 */
public class BuildingDestructionDialogFragment extends DialogFragment{
    BuildingDestructionController bdc;
    int counter;
    int maxC =1;
    Callback callback;
    public void setBuildingDestructionController(BuildingDestructionController bdc) {
        this.bdc = bdc;
        counter = 0;

    }

    public void setMaxC(int i) {
        maxC = i;
    }
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_building_selection_dialog,null);
        ((TextView) v.findViewById(R.id.title)).setText("Choose a Building for destruction");
        final GridView gridview = (GridView) v.findViewById(R.id.gridview);
        gridview.setAdapter(new CurrentBuildingAdapter(getActivity().getApplicationContext(), bdc.getBuildingList()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("counter " + counter + " " + " max C" + maxC);
                if(counter < maxC) {
                    bdc.destroyBuilding(position);
                    gridview.getChildAt(position).setAlpha(0.25f);
                    counter++;
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildingDestructionDialogFragment.this.getActivity());
                    builder.setMessage("No more destruction available.");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                }
            }
        });
        builder.setView(v);
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(bdc.isGodBuild()) {
            callback.callback();
        } else {
            bdc.nextRound();
        }
        //bdc.nextRound();
    }
}
