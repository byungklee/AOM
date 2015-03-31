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
public class AreaImageAdapter extends BaseAdapter implements Observer {

     private ArrayList<Tile> al;
    private Context c;
    public AreaImageAdapter(Context c, ArrayList<Tile> al) {
        this.al = al;
        this.c =c ;
    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return al.get(position);
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
            imageView = new ImageView(c);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 150));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(al.get(position).getImagePath());
        return imageView;
    }

    @Override
    public void update(Object object) {
        this.notifyDataSetChanged();
    }
}
