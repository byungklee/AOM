package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.anycomp.android.ageofmythology.model.card.CardDeck;

/**
 * Created by byung on 3/30/15.
 */
public class PlayCardAdapter extends BaseAdapter {
    private CardDeck cd;
    private Context context;
    public PlayCardAdapter(Context context, CardDeck cd) {
        this.cd = cd;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cd.size();
    }

    @Override
    public Object getItem(int position) {
        return cd.getCardAt(position);
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

        imageView.setImageResource(cd.getCardAt(position).getImagePath());
        if(cd.getCardAt(position).isPlayed()) {
            imageView.setAlpha(0.25f);
        }

        return imageView;
    }
}
