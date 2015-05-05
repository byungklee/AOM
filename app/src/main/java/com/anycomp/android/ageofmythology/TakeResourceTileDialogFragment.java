package com.anycomp.android.ageofmythology;
;
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
import android.widget.TextView;

import com.anycomp.android.ageofmythology.model.area.ProductionArea;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.tile.Tile;
import com.anycomp.android.ageofmythology.model.tile.TileDecorator;

import java.util.ArrayList;

/**
 * Created by byung on 4/26/15.
 */
public class TakeResourceTileDialogFragment extends DialogFragment {

    PlayerController pc;
    int counter = 0;
    int maxC = 2;
    int targetIndex;
    int attackerIndex;
    AttackController atc;
    void setPC(PlayerController pc) {
        this.pc = pc;
    }
    void setTargetPlayer(int target) {
        targetIndex = target;
    }
    void setAttacker(int att) {
        attackerIndex = att;
    }
    void setMaxC(int i) {
        maxC = i;
    }
    void setAttackController(AttackController ac) {
        this.atc = ac;
    }

    boolean canTake(Tile t) {
        TileDecorator td = (TileDecorator) t;
        Tile base = td.getBasicTile();
        Player p = (Player) pc.getPlayers().get(attackerIndex);
        ArrayList<Tile> tiles = p.getPlayerBoard().getProductionArea().getTiles();
        for(int i =0;i<tiles.size();i++) {
            Tile temp = tiles.get(i);
            if(!(temp instanceof TileDecorator)) {
                if(temp.getTileType() == t.getTileType()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_building_selection_dialog,null);
        final Player p = (Player) pc.getPlayers().get(targetIndex);
        final Player a = (Player) pc.getPlayers().get(attackerIndex);
        final ProductionArea pa =  (ProductionArea) p.getPlayerBoard().getProductionArea();
        final ArrayList<Tile> tiles= pa.getTiles();
        final TakeResourceTileAdapter trta =new TakeResourceTileAdapter(getActivity().getApplicationContext(), tiles);
        ((TextView) v.findViewById(R.id.title)).setText("Choose a Resource tile to take from " + p.getName() + " " + p.getCulture().getName());
        final GridView gridview = (GridView) v.findViewById(R.id.gridview);


        gridview.setAdapter(trta);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(counter < maxC) {
                    Tile t = tiles.get(position);
                    if(t instanceof TileDecorator) {
                        if(canTake(t)) {
                            //take
                            p.getPlayerBoard().getProductionArea().removeTileAt(position, ((TileDecorator) t).getBasicTile());
                            ArrayList<Tile> productionTiles = a.getPlayerBoard().getProductionArea().getTiles();
                            for (int i = 0; i < productionTiles.size(); i++) {
                                Tile tile = productionTiles.get(i);
                                System.out.println(tile);
                                if (!(tile instanceof TileDecorator)
                                        && tile.getTileType() == t.getTileType()) {
                                    pc.getCurrentPlayer().getPlayerBoard().getProductionArea().setTileAt(i, t);
                                    break;
                                }
                            }
                            counter++;
                            trta.notifyDataSetChanged();
                            view.setAlpha(0.25f);

                        } else {
                            //error
                            showNotVaild();
                        }
                    } else {
                        //error
                        showNotVaild();
                    }
                } else {
                    //error
                    showNotVaild();
                }
            }
        });
        builder.setView(v);
        return builder.create();
    }

    public void showNotVaild() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TakeResourceTileDialogFragment.this.getActivity());
        builder.setMessage("Not valid.");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(pc.getCurrentPlayer().hasBuilding(BuildingType.SIEGE_ENGINE_WORKSHOP)) {
           atc.destroyBuilding(1);
        } else
            pc.nextRound();
    }

}
