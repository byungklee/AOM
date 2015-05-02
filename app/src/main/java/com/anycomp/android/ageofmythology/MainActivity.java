package com.anycomp.android.ageofmythology;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.anycomp.android.ageofmythology.model.bank.Bank;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.culture.Egyptian;
import com.anycomp.android.ageofmythology.model.culture.Greek;
import com.anycomp.android.ageofmythology.model.culture.Norse;
import com.anycomp.android.ageofmythology.model.player.Player;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends ActionBarActivity implements TileSelectionDialogFragment.OnTileClickListener, WinnerInterface {

    public static final String TAG = "MainActivity";
    private MainPlayingFragment mpf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        } else if(id == R.id.victory_card)  {
            openVictoryCardPopup(false);
        } else if(id == R.id.next_round) {
            mPlayerController.nextRound();
        } else if(id == R.id.unit_list) {
            openUnitListDialog();
        } else if(id == R.id.end_game) {
            mPlayerController.gameEnd();
        } else if(id == R.id.give3resources) {
            give3resources();
        } else if(id == R.id.take_resource) {
            TakeResourceDialogFragment t = new TakeResourceDialogFragment();
            t.setOffender(mPlayerController.getHumanPlayer());
            t.setDefender((Player) mPlayerController.getPlayers().get(1));
            t.setPC(mPlayerController);
            t.show(getFragmentManager(), "takeresource");
        } else if(id == R.id.destroy_building) {
            BuildingDestructionController bc = new BuildingDestructionController(mPlayerController, false);
            bc.setTargetPlayer(1);
            BuildingDestructionDialogFragment bd = new BuildingDestructionDialogFragment();
            bd.setBuildingDestructionController(bc);
            bd.show(getFragmentManager(),"test");
        } else if(id == R.id.take_tiles) {
            TakeResourceTileDialogFragment trtf = new TakeResourceTileDialogFragment();
            trtf.setTargetPlayer(1);
            trtf.setAttacker(0);
            trtf.setPC(mPlayerController);
            trtf.show(getFragmentManager(),"test2");
        }

        return super.onOptionsItemSelected(item);
    }

    public void give3resources() {
        List<Player> list = mPlayerController.getPlayers();
        for(Player p:list) {
            p.takeFood(3);
            p.takeWood(3);
            p.takeFavor(3);
            p.takeGold(3);
        }
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
        Bank.getInstance().reset();
        mPlayerController = new PlayerController(3,"user", b.getText().toString(),cultureMap, getFragmentManager(), this);
        mPlayerController.getTurnManager().setVictoryCallback(startPlayerCallback);
        mpf = MainPlayingFragment.newInstance(b.getText().toString(), mPlayerController.getHumanPlayer());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, mpf);
        transaction.addToBackStack("main_playing");
        transaction.commit();

        openTileSelectionPopup(6);
       // getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    private Callback startPlayerCallback = new Callback() {
        public void callback() {
            System.out.println("starting player call back");
            mPlayerController.setStartingPlayer();

            victoryCardCallback.callback();

        }
    };

    private Callback victoryCardCallback = new Callback() {
        public void callback() {
            //turn on victory card view
            System.out.println("victory card callback!");
            if(mPlayerController.isEndCondtionMet()) {
                //TO DO: implement game end logic

            } else {
                TurnManager tm = mPlayerController.getTurnManager();

                int index = tm.getCurrentPlayer();
                for (int i = 0; i < 3; i++) {
                    System.out.println(((Player) mPlayerController.getPlayers().get(index)).getName());
                    index = (index + 1) % 3;
                    //must do victory card work
                }
                openVictoryCardPopup(true);
            }

        }
    };



    private Callback onVictoryCardEnd = new Callback() {
        @Override
        public void callback() {
            System.out.println("Victory Card Dialog is ended. " + mPlayerController.getTurnManager().getCurrentPlayer() + " is starting player");
//            mPlayerController.setStartingPlayer();
            if(mPlayerController.getCurrentPlayer().getName().contains("AI")) {
                System.out.println("First player is AI, so AI IS PLAYING");
                mPlayerController.aiWork();
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
        System.out.println("opening victory card popup " + place);
        VictoryCardDialogFragment vcdf = new VictoryCardDialogFragment();
        vcdf.setPlace(place);
        vcdf.setPlayerController(mPlayerController);
        vcdf.setOnVictoryCardDialogEnd(onVictoryCardEnd);
        vcdf.setStartingPlayerIndex(mPlayerController.getTurnManager().getCurrentPlayer());
        vcdf.show(getFragmentManager(), "Victory Card Dialog");
    }

    public void openUnitListDialog() {
        CurrentUnitListDialogFragment culdf = new CurrentUnitListDialogFragment();
        culdf.setPlayerController(mPlayerController);
        culdf.show(getFragmentManager(),"Unit List");
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
//        getFragmentManager().popBackStack("MainPlayingView",);
    }


    @Override
    public void winner(Player player) {
        //game end popup;
        WinnerDialogFragment wdf = new WinnerDialogFragment();
        wdf.setPlayer(player);
        wdf.setPlayerController(mPlayerController);
        wdf.setCallback(new Callback() {
            @Override
            public void callback() {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.container, new MainFragment(), "main").commit();
            }
        });

        wdf.show(getFragmentManager(), "WinnerDialog");
    }
}
