package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.anycomp.android.ageofmythology.model.resource.VictoryCube;

import java.util.ArrayList;

/**
 * Created by byung on 4/4/15.
 */
public class VictoryCubeAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<VictoryCube> cubeArrayList;
    public VictoryCubeAdapter(Context ctx, ArrayList<VictoryCube> al) {
        this.ctx= ctx;
        cubeArrayList = al;
    }
    @Override
    public int getCount() {
        return cubeArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cubeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null) {
            imageView = new ImageView(ctx);
            imageView.setPadding(8,8,8,8);
            imageView.setLayoutParams(new GridView.LayoutParams(40,40));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(cubeArrayList.get(position).getImagePath());
        return imageView;
    }
}
