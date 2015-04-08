package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.anycomp.android.ageofmythology.model.unit.Unit;

import java.util.ArrayList;

/**
 * Created by byung on 4/7/15.
 */
public class BattleUnitAdapter extends BaseAdapter {
    ArrayList<Unit> units;
    Context ctx;
    public BattleUnitAdapter(Context ctx, ArrayList<Unit> unit) {
        this.units = unit;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return units.size();
    }

    @Override
    public Object getItem(int position) {
        return units.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image;

        if(convertView == null) {
            image = new ImageView(ctx);
            image.setLayoutParams(new GridView.LayoutParams(150,150));
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setPadding(8, 8, 8, 8);
        } else {
            image = (ImageView) convertView;
        }

        image.setImageResource(units.get(position).getImagePath());
        return image;
    }
}
