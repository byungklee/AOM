package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.anycomp.android.ageofmythology.model.card.VictoryCardDeck;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by byung on 4/4/15.
 */
public class VictoryCardAdapter extends BaseAdapter {

    private Context ctx;
    private VictoryCardDeck victoryCardDeck;
    private ArrayList<VictoryCubeAdapter> vcalist;
    private VictoryCardDialogFragment.ImageClickCallback callback;

    public VictoryCardAdapter(Context c, VictoryCardDeck victoryCardDeck, VictoryCardDialogFragment.ImageClickCallback callback) {
        this.ctx = c;
        this.victoryCardDeck = victoryCardDeck;
        vcalist = new ArrayList<>();
        this.callback = callback;
        for(int i=0; i<victoryCardDeck.getVictoryCardCount();i++) {
            vcalist.add(new VictoryCubeAdapter(c, victoryCardDeck.getVictoryCards().get(i).getVictoryCubes()));
        }
        debug();
    }

    private void debug() {
        System.out.println("DEBUG VICTORY CARD");
        System.out.println(victoryCardDeck.getVictoryCards().get(0).getImagePath());
        System.out.println(victoryCardDeck.getVictoryCards().get(1).getImagePath());
        System.out.println(victoryCardDeck.getVictoryCards().get(2).getImagePath());
    }
    @Override
    public int getCount() {
        return victoryCardDeck.getVictoryCardCount();
    }

    @Override
    public Object getItem(int position) {
        return victoryCardDeck.getVictoryCards().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout;
        if (convertView == null) {
            layout = new LinearLayout(ctx);
            layout.setOrientation(LinearLayout.VERTICAL);
        final int index = position;


//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("layout");
//            }
//        });
        ImageView imageView;

            // if it's not recycled, initialize some attributes
            imageView = new ImageView(ctx);
            imageView.setLayoutParams(new GridView.LayoutParams(260, 350));
//            imageView.at
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(victoryCardDeck.getVictoryCards().get(position).getImagePath());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("image");
                    callback.callback(index);
                }
            });

            GridView gridView;
            gridView = new GridView(ctx);
            gridView.setLayoutParams(new GridView.LayoutParams(230,350));
//            gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            gridView.setPadding(8,8,8,8);
            gridView.setNumColumns(5);
            gridView.setColumnWidth(40);

            //gridView.setAdapter(new VictoryCubeAdapter(ctx, victoryCardDeck.getVictoryCards().get(position).getVictoryCubes()));
            gridView.setAdapter(vcalist.get(position));
            gridView.setClickable(false);
//            gridView.setEnabled(false);
            layout.addView(imageView);
            layout.addView(gridView);

        } else {
            layout = (LinearLayout) convertView;
        }

        return layout;
    }

    public void refresh() {
        System.out.println("refreshing");
        Iterator it = vcalist.iterator();
        while(it.hasNext()) {
            VictoryCubeAdapter t = (VictoryCubeAdapter)it.next();
            t.notifyDataSetChanged();
        }
    }



}
