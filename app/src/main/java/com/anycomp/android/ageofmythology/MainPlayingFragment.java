package com.anycomp.android.ageofmythology;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anycomp.android.ageofmythology.model.area.CityArea;
import com.anycomp.android.ageofmythology.model.area.HoldingArea;
import com.anycomp.android.ageofmythology.model.player.Player;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPlayingFragment extends Fragment implements Observer {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mCulture;
    private Player mPlayer;
    private LinearLayout background;
    private GridView resourceView;
    private GridView buildingView;
    private AreaImageAdapter resourceTileImageAdapter;
    private BuildingImageAdapter buildingTileAdapter;
    private TextView age;
    private TextView food;
    private TextView wood;
    private TextView gold;
    private TextView favor;
    private TextView victory;
    private TextView villagers;

    private String currentPlayerBoard;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param culture Parameter 1.
     * @return A new instance of fragment MainPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainPlayingFragment newInstance(String culture, Player player) {
        MainPlayingFragment fragment = new MainPlayingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, culture);
        fragment.setPlayer(player);

        fragment.setArguments(args);
        return fragment;
    }

    private void setPlayer(Player player) {
        mPlayer = player;
    }

    public void changeBoard(Player player) {
        background.setBackgroundResource(player.getCulture().getImagePath());
        currentPlayerBoard = player.getCulture().getName();
        if(player.getCulture().getName().equals(mCulture)) {
            resourceView.setAdapter(resourceTileImageAdapter);
            buildingView.setAdapter(buildingTileAdapter);
            showResourceInfo(player);

        } else {
            AreaImageAdapter resourceTileImageAdapter = new AreaImageAdapter(getActivity().getApplicationContext(),
                    player.getPlayerBoard().getProductionArea().getTiles());
            resourceView.setAdapter(resourceTileImageAdapter);
            BuildingImageAdapter buildingTileAdapter = new BuildingImageAdapter(getActivity().getApplicationContext(),
                    player.getPlayerBoard().getCityArea().getTiles());
            buildingView.setAdapter(buildingTileAdapter);
            showResourceInfo(player);
        }
    }

    public MainPlayingFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCulture = getArguments().getString(ARG_PARAM1);
        }
    }

    public void showResourceInfo() {
        showResourceInfo(mPlayer);
    }
    public void showResourceInfo(Player mPlayer) {
        age.setText("Age " + mPlayer.getAge().getName());
        food.setText("Food: " + mPlayer.getFoodCube().getValue());
        wood.setText("Wood: " + mPlayer.getWoodCube().getValue());
        gold.setText("Gold: " + mPlayer.getGoldCube().getValue());
        favor.setText("Favor: " + mPlayer.getFavorCube().getValue());
        victory.setText("Victory: " + mPlayer.getVictoryCube().getValue());
        villagers.setText("Villager: " + ((CityArea) mPlayer.getPlayerBoard().getCityArea()).getNumberOfHouse());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_playing, container, false);
        age = (TextView)v.findViewById(R.id.current_age);
        food = (TextView) v.findViewById(R.id.food);
        wood = (TextView) v.findViewById(R.id.wood);
        gold = (TextView) v.findViewById(R.id.gold);
        favor = (TextView) v.findViewById(R.id.favor);
        victory = (TextView) v.findViewById(R.id.victory);
        villagers = (TextView) v.findViewById(R.id.villager);
        showResourceInfo();
        background = (LinearLayout) v.findViewById(R.id.layout);
        background.setBackgroundResource(mPlayer.getCulture().getImagePath());
//        background = (ImageView) v.findViewById(R.id.bac)

        resourceView =(GridView) v.findViewById(R.id.resource_tile_grid);
        resourceTileImageAdapter = new AreaImageAdapter(getActivity().getApplicationContext(),
                mPlayer.getPlayerBoard().getProductionArea().getTiles());
        resourceView.setAdapter(resourceTileImageAdapter);
        resourceView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();

            }
        });

        buildingView = (GridView) v.findViewById(R.id.building_tile_grid);
        buildingTileAdapter = new BuildingImageAdapter(getActivity().getApplicationContext(),
                mPlayer.getPlayerBoard().getCityArea().getTiles());
        buildingView.setAdapter(buildingTileAdapter);

        currentPlayerBoard = mPlayer.getCulture().getName();
        return v;
    }

    private void attachObs() {
        mPlayer.getPlayerBoard().getProductionArea().attachObserver(resourceTileImageAdapter);
        mPlayer.getPlayerBoard().getCityArea().attachObserver(buildingTileAdapter);
        mPlayer.attachResourceObserver(this);
    }

    private void detachObs() {
        mPlayer.getPlayerBoard().getProductionArea().detachObserver(resourceTileImageAdapter);
        mPlayer.getPlayerBoard().getCityArea().detachObserver(buildingTileAdapter);
        mPlayer.detachResourceObserver(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        attachObs();
    }

    @Override
    public void onStop() {
        super.onStop();
        detachObs();

    }

    @Override
    public void update(Object object) {
        if(mPlayer.getCulture().getName().equals(currentPlayerBoard)) {
            showResourceInfo();
        }
    }
}
