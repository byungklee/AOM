package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.GatherController;
import com.anycomp.android.ageofmythology.GodCardDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;
import com.anycomp.android.ageofmythology.model.tile.ResourceProductionTile;
import com.anycomp.android.ageofmythology.model.tile.Tile;

import java.util.ArrayList;

/**
 * Created by bert on 4/28/15.
 */
public class GodEgyptGatherCard extends RandomGatherCard implements God {
    public GodEgyptGatherCard(PermanentGatherCard card) {
        setName("GodEgyptGather");
        this.card = card;
        setImagePath(R.drawable.card_rand_egypt_gather_ra);
        favorCost = 2;
    }

    @Override
    public Culture getCulture() { return card.getCulture(); }

    @Override
    public void play(FragmentManager fm, PlayerController pc) {
        this.fm = fm;
        this.pc = pc;

        if (!isPlayed()) {
            setPlayed(true);

            AskGodPowerUseDialogFragment agpud = new AskGodPowerUseDialogFragment();
            agpud.setGod(this);
            agpud.show(fm, "UseGod");
        }
    }

    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        this.pc = pc;
        this.fm = fm;

        if(!isPlayed()) {
            setPlayed(true);

            if(payFavor()) {
                playGod();
            } else {
                playNormal();
            }
        }
    }

    @Override
    public void playNormal() {
        GatherController gc = GatherController.getInstance(pc);
        int current = gc.getPlayerController()
                .getCurrentPlayerID();

        gc.setPick(8); // FOOD correlates to 8 in gc.list
        gc.gather(true);
        gc.getPlayerController().setCurrentPlayer((++current) % 3);
        gc.gather(false);
        gc.getPlayerController().setCurrentPlayer((++current) % 3);
        gc.gather(false);
        gc.getPlayerController().setCurrentPlayer((++current) % 3);
        pc.nextRound();
    }

    @Override
    public void playGod() {
        Player player = pc.getCurrentPlayer(); // Current player
        int current = pc.getCurrentPlayerID(); // Current player's ID
        ArrayList<Tile> tiles = player.getPlayerBoard().getProductionArea()
                .getTiles();
        int numTiles = tiles.size(); // Number of tiles on board

        // Check for FOOD tiles
        for (int i = 0; i < numTiles; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile
                    && ((ResourceProductionTile) tiles.get(i))
                    .getResourceType() == ResourceType.FOOD) {
                player.takeFood(2);
            }
        }

        pc.setCurrentPlayer((++current) % 3);
        player = pc.getCurrentPlayer();
        tiles = player.getPlayerBoard().getProductionArea().getTiles();
        numTiles = tiles.size();

        // Check for FOOD tiles
        for (int i = 0; i < numTiles; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile
                    && ((ResourceProductionTile) tiles.get(i))
                    .getResourceType() == ResourceType.FOOD) {
                player.takeFood(2);
            }
        }

        pc.setCurrentPlayer((++current) % 3);
        player = pc.getCurrentPlayer();
        tiles = player.getPlayerBoard().getProductionArea().getTiles();
        numTiles = tiles.size();

        // Check for FOOD tiles
        for (int i = 0; i < numTiles; i++) {
            if (tiles.get(i) instanceof ResourceProductionTile
                    && ((ResourceProductionTile) tiles.get(i))
                    .getResourceType() == ResourceType.FOOD) {
                player.takeFood(2);
            }
        }

        pc.setCurrentPlayer((++current) % 3);
        player = pc.getCurrentPlayer();

        if (player.getName().equals("user")) {
            GodCardDialogFragment gcdf = new GodCardDialogFragment();

            gcdf.setGod(this);
            gcdf.setPlayerController(pc);
            gcdf.show(this.fm, "Other Activity");
        } else {
            pc.nextRound();
        }
    }

    @Override
    public boolean payFavor() { return pay(); }

    @Override
    public boolean checkAge() {
        return false;
    }
}
