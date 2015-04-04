package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.anycomp.android.ageofmythology.model.card.VictoryCardDeck;

import java.util.ArrayList;

/**
 * Created by byung on 4/4/15.
 */
public class VictoryCardAdapter extends BaseAdapter {

    Context ctx;
    VictoryCardDeck victoryCardDeck;

    public VictoryCardAdapter(Context c, VictoryCardDeck victoryCardDeck) {
        this.ctx = c;
        this.victoryCardDeck = victoryCardDeck;
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
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(ctx);
            imageView.setLayoutParams(new GridView.LayoutParams(230, 350));

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(victoryCardDeck.getVictoryCards().get(position).getImagePath());
        return imageView;
    }
}
