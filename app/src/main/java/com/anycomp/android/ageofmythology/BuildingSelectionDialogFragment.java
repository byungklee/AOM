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

    public void setTileSelectionController(BuildingSelectionController c) {
        this.c =c;
    }

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
    public static BuildingSelectionDialogFragment newInstance(BuildingSelectionController c, int maxAllowedPick) {
        BuildingSelectionDialogFragment fragment = new BuildingSelectionDialogFragment();
        Bundle args = new Bundle();
        args.putInt("MaxPick", maxAllowedPick);

        fragment.setController(c);

        fragment.
        return fragment;
    }

    public BuildingSelectionDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_building_selection_dialog, container, false);
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
        final Button closeButton = (Button) v.findViewById(R.id.close_button);




        return builder.create();
    }


}
