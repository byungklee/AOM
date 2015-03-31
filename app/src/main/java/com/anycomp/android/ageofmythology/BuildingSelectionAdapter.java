package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.anycomp.android.ageofmythology.model.building.Building;

import java.util.ArrayList;

/**
 * Created by byung on 3/30/15.
 */
public class BuildingSelectionAdapter extends BaseAdapter {

    private ArrayList<Building> list;
    private Context context;
    public BuildingSelectionAdapter(Context context, ArrayList al) {
        this.context = context;
        list = al;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            imageView.setLayoutParams(new GridView.LayoutParams(80,80));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(list.get(position).getImagePath());

        return imageView;
    }
}
