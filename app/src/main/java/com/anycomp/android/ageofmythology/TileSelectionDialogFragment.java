package com.anycomp.android.ageofmythology;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class TileSelectionDialogFragment extends DialogFragment {
    public static final String TAG = "TileSelectionDialog";
    private TileSelectionController c;
    private Callback callback = null;


    public void setTileSelectionController(TileSelectionController c) {
        this.c =c;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_tile_selection_dialog, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        builder.setView(v);
        // Create the AlertDialog object and return it


        final GridView gridview = (GridView) v.findViewById(R.id.gridview);
        final Button passButton = (Button) v.findViewById(R.id.pass_button);
        final Button okayButton = (Button) v.findViewById(R.id.okay_button);
        okayButton.setEnabled(false);

        gridview.setAdapter(new TileImageAdapter(getActivity().getApplicationContext()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
//                ((ImageView) v).setVisibility(View.INVISIBLE);
                //TileManager.getInstance().getTileSelectionDeck().remove(position);
                //((BaseAdapter) gridview.getAdapter()).notifyDataSetChanged();
                c.execute(position);
                ((BaseAdapter) gridview.getAdapter()).notifyDataSetChanged();
                if(c.isAllResourceTilePlaced()) {
                    passButton.setEnabled(false);
                    okayButton.setEnabled(true);
                }
            }
        });

        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.pass();
                ((BaseAdapter) gridview.getAdapter()).notifyDataSetChanged();
                if(c.isAllResourceTilePlaced()) {
                    passButton.setEnabled(false);
                    okayButton.setEnabled(true);
                }
            }
        });

    //    okayButton.setEnabled(false);
        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TileSelectionDialogFragment.this.dismiss();

            }
        });

        if(c.isAllResourceTilePlaced()) {
            passButton.setEnabled(false);
            okayButton.setEnabled(true);
        }

        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        System.out.println("Dismissing Tile Fragment");
        if(callback != null) {
            callback.callback();
        } else {
        //    c.getPlayerController().setCurrentPlayer(c.getPlayerController().getTurnManager().getCurrentPlayer());
            c.nextRound();
        }
    }

    private OnTileClickListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (OnTileClickListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement TileClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    public interface OnTileClickListener {
        public void onTileClick();
    }
}
