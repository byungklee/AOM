package com.anycomp.android.ageofmythology;


import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.culture.Egyptian;
import com.anycomp.android.ageofmythology.model.culture.Greek;
import com.anycomp.android.ageofmythology.model.culture.Norse;

import java.util.HashMap;

public class MainActivity extends ActionBarActivity implements TileSelectionDialogFragment.OnTileClickListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button button = new Button(this);
        //button.setBackgroundResource();

        if (savedInstanceState == null) {
            Log.d(TAG, "Starting main fragment");
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.container, new MainFragment(), "main").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public void startGame(View view) {
        Log.d(this.TAG, "clicked start button in MainActivity");
        CultureSelectionFragment newFragment = new CultureSelectionFragment();
        Bundle args = new Bundle();
        //
        newFragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack("culture_selection");

        // Commit the transaction
        transaction.commit();
    }

    private HashMap<String, Culture> cultureMap;
    private PlayerController mPlayerController;

    public void selectCulture(View view) {
        cultureMap = new HashMap<>();
        cultureMap.put("Norse", new Norse());
        cultureMap.put("Greek", new Greek());
        cultureMap.put("Egypt", new Egyptian());
        Button b = (Button) view;
        Log.d(TAG, "start " + b.getText().toString());
        mPlayerController = new PlayerController(3,"user", b.getText().toString(),cultureMap);
        MainPlayingFragment newFragment = MainPlayingFragment.newInstance(b.getText().toString(), mPlayerController.getHumanPlayer());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack("culture_selection");
        transaction.commit();
        TileSelectionDialogFragment tsd =  new TileSelectionDialogFragment();
        tsd.setTileSelectionController(new TileSelectionController(mPlayerController,6));
        tsd.show(getFragmentManager(), "Tile Selection Dialog");

       // getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onTileClick() {
        Log.d(TAG,"TILE CLICKED");
    }
}
