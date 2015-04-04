package com.anycomp.android.ageofmythology;


import android.app.FragmentManager;
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
import com.anycomp.android.ageofmythology.model.player.Player;

import java.util.HashMap;

public class MainActivity extends ActionBarActivity implements TileSelectionDialogFragment.OnTileClickListener {

    public static final String TAG = "MainActivity";
    private MainPlayingFragment mpf;
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
        } else if(id == R.id.pick_card) {
            openPickCardDialog();
        } else if(id == R.id.play_card) {
            openPlayCardDialog();
        } else if(id == R.id.greekboard) {
            mpf.changeBoard(mPlayerController.getPlayerByCulture("Greek"));
        } else if(id == R.id.norseboard) {
            mpf.changeBoard(mPlayerController.getPlayerByCulture("Norse"));
        } else if(id == R.id.egyptboard) {
            mpf.changeBoard(mPlayerController.getPlayerByCulture("Egypt"));
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
        mPlayerController.getTurnManager().setVictoryCallback(victoryCardCallback);
        mpf = MainPlayingFragment.newInstance(b.getText().toString(), mPlayerController.getHumanPlayer());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, mpf);
        transaction.addToBackStack("main_playing");
        transaction.commit();

        openTileSelectionPopup(6);
       // getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    Callback startPlayerCallback = new Callback() {
        public void callback() {
            System.out.println("starting player call back");
            mPlayerController.setStartingPlayer();
            victoryCardCallback.callback();
            openVictoryCardPopup(false);
        }
    };

    Callback victoryCardCallback = new Callback() {
        public void callback() {
            //turn on victory card view
            System.out.println("victory card callback!");
            TurnManager tm =  mPlayerController.getTurnManager();
            int index = tm.getStartingPlayer();
            for(int i=0;i<3;i++) {
                System.out.println(((Player) mPlayerController.getPlayers().get(index)).getName());
                index = (index + 1) %3;
                //must do victory card work
            }

        }
    };

    public void openTileSelectionPopup(int maxPick) {
        TileSelectionDialogFragment tsd =  new TileSelectionDialogFragment();
        TileManager.getInstance().setNumberOfCardsToRefresh(18);
        tsd.setTileSelectionController(new TileSelectionController(mPlayerController,maxPick));
        tsd.setCallback(startPlayerCallback);
        tsd.show(getFragmentManager(), "Tile Selection Dialog");
    }

    public void openVictoryCardPopup(boolean place) {
        System.out.println("opening victory card popup");
        VictoryCardDialogFragment vcdf = new VictoryCardDialogFragment();
        vcdf.setPlace(place);
        vcdf.setPlayerController(mPlayerController);
        vcdf.show(getFragmentManager(), "Victory Card Dialog");
    }

    @Override
    public void onTileClick() {
        Log.d(TAG,"TILE CLICKED");
    }

    public void openPickCardDialog() {
        PickCardDialogFragment pcdf = new PickCardDialogFragment();

        pcdf.setPlayer(mPlayerController.getHumanPlayer());
        pcdf.show(getFragmentManager(), "Pick Card Dialog");
    }

    public void openPlayCardDialog() {
        PlayCardDialogFragment pcdf = new PlayCardDialogFragment();
        pcdf.setPlayer(mPlayerController.getHumanPlayer());
        pcdf.setPlayerController(mPlayerController);
        pcdf.show(getFragmentManager(), "Play Card Dialog");
    }


}
