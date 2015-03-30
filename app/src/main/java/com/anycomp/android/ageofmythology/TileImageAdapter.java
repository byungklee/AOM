package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.anycomp.android.ageofmythology.model.tile.Tile;

import java.util.ArrayList;

/**
 * Created by byung on 3/29/15.
 */
public class TileImageAdapter extends BaseAdapter implements Observer {
    private Context mContext;
    ArrayList<Tile> tiles;
    public TileImageAdapter(Context c) {
        mContext = c;


        TileManager tm = TileManager.getInstance();
        tm.refreshTileSelectionDeck();
        tiles = tm.getTileSelectionDeck();


    }
    @Override
    public int getCount() {
        return tiles.size();
    }

    @Override
    public Object getItem(int position) {
        return tiles.get(position);
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
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(tiles.get(position).getImagePath());
        return imageView;
    }

    @Override
    public void update(Object object) {

    }
}
