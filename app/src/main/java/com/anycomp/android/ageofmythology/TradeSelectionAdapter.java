package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.anycomp.android.ageofmythology.model.unit.Unit;

import java.util.ArrayList;

/**
 * Created by mike on 4/3/15.
 */
public class TradeSelectionAdapter extends BaseAdapter {

    private static final String TAG = "TradeSelectionAdapter";

    public static final int FAVOR = 0, FOOD = 1, GOLD = 2, WOOD = 3;

    /** Set using the final int corresponding to each resource type. */
    private int bankResourceType;

    private ArrayList<String> bankResourceCount = new ArrayList<>();

    private Context context;

    public TradeSelectionAdapter(Context context, int bankResourceType, int bankResourceCount) {
        this.bankResourceType = bankResourceType;

        for (int i=1; i<=bankResourceCount; i++) {
            this.bankResourceCount.add("" + i);
        }
        Log.i(TAG, "bankResourceType: " + bankResourceType);
        Log.i(TAG, "bankResourceCount: " + bankResourceCount);

        this.context = context;
    }

    @Override
    public int getCount() {
        return bankResourceCount.size();
    }

    @Override
    public Object getItem(int position) {
        return bankResourceCount.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(context);
            textView.setLayoutParams(new GridView.LayoutParams(80, 80));
            textView.setPadding(8, 8, 8, 8);
            textView.setText(bankResourceCount.get(index));
        } else {
            textView = (TextView) convertView;
        }

//        Integer rid = null;
//        switch (bankResourceType) {
//            case FAVOR:
//                rid = R.drawable.resource_favor;
//                break;
//            case FOOD:
//                rid = R.drawable.resource_food;
//                break;
//            case GOLD:
//                rid = R.drawable.resource_gold;
//                break;
//            case WOOD:
//                rid = R.drawable.resource_wood;
//                break;
//            default:
//                break;
//        }
//        if (rid == null) throw new NullPointerException("The resource type was not properly set.");
//
//        textView.setImageResource(rid);

        return textView;
    }
}
