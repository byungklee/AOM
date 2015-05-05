package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.anycomp.android.ageofmythology.model.culture.Greek;
import com.anycomp.android.ageofmythology.model.tile.Tile;

import java.util.ArrayList;

/**
 * Created by bert on 5/2/15.
 */
public class GreekTileEliminationAdapter extends BaseAdapter {
    private Context context;
    ArrayList<Tile> tiles;

    public GreekTileEliminationAdapter(Context context, TileEliminationController tec) {
        this.context = context;
        tiles = tec.getGreekTiles();
    }

    @Override
    public int getCount() { return tiles.size(); }

    @Override
    public Object getItem(int position) { return tiles.get(position); }

    @Override
    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(130, 130));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(tiles.get(position).getImagePath());

        return imageView;
    }
}
