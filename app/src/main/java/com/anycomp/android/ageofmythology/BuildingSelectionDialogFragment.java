package com.anycomp.android.ageofmythology;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuildingSelectionDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildingSelectionDialogFragment extends DialogFragment {

    public static final String TAG = "TileSelectionDialog";
    private BuildingSelectionController c;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int maxAllowedPick;
    private String mParam2;

    private void setController(BuildingSelectionController c) {
        this.c = c;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param c Parameter 1.
     * @return A new instance of fragment BuildingSelectionDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildingSelectionDialogFragment newInstance(BuildingSelectionController c) {
        BuildingSelectionDialogFragment fragment = new BuildingSelectionDialogFragment();
        fragment.setController(c);

        //fragment.
        return fragment;
    }

    public BuildingSelectionDialogFragment() {
        // Required empty public constructor
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
        View v = inflater.inflate(R.layout.fragment_building_selection_dialog, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v);
        // Create the AlertDialog object and return it



        final GridView gridview = (GridView) v.findViewById(R.id.gridview);
        gridview.setAdapter(new BuildingSelectionAdapter(getActivity().getApplicationContext(), c.getBuildingList()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(c.verifyAvailability(position) && c.verifyResource(position)) {
                    c.addBuilding(position);
                } else {

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
}
