package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.card.CardDeck;

/**
 * Created by byung on 3/30/15.
 */
public class PickCardAdapter extends BaseAdapter {
    Card[] permanentCardPool;
    CardDeck randomCardPool;
    Context context;

    //to do make randomCardpool as back of the card.
    public PickCardAdapter(Context context, Card[] permanentCardPool, CardDeck randomCardPool) {
        this.context = context;
        this.permanentCardPool =permanentCardPool;
        this.randomCardPool = randomCardPool;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        if(position == 7) {
            return randomCardPool.getCardAt(0);
        }
        return permanentCardPool[position];
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
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 230));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        if(position == 7) {
            //Fix it to Random Card later!
            imageView.setImageResource(permanentCardPool[position-1].getImagePath());
        } else {
            imageView.setImageResource(permanentCardPool[position].getImagePath());
        }

        return imageView;
    }
}
