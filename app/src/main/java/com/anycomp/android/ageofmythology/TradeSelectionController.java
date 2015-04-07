package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.util.Log;

import com.anycomp.android.ageofmythology.model.bank.Bank;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.card.PermanentTradeCard;

/**
 * Created by mike on 4/5/15.
 */
public class TradeSelectionController {

    private PlayerController pc;
    private Bank bank;
    private Card card;

    /** Resource counts for player (current player). */
    private int availableFavor, availableFood, availableGold, availableWood;

    /** Resource counts for bank (bank). */
    private int bankFavor, bankFood, bankGold, bankWood;

    public TradeSelectionController(PlayerController pc) {
        this.pc = pc;
        this.bank = Bank.getInstance();

        availableFavor = pc.getCurrentPlayer().getFavorCube().getValue();
        availableFood = pc.getCurrentPlayer().getFoodCube().getValue();
        availableGold = pc.getCurrentPlayer().getGoldCube().getValue();
        availableWood = pc.getCurrentPlayer().getWoodCube().getValue();
    }

    public void playTradeCard(Card card) {
        this.card = card;
    }

    public void makeTrade(int requestFavor, int requestFood, int requestGold, int requestWood) {
        int transactionFee = 0;
        // check to see if the player has a market
        if (!pc.getCurrentPlayer().hasBuilding(BuildingType.MARKET)) {
            // if not, set transaction fee
            transactionFee = 2;
        }
        else {
            // check that the player has sufficient resources
            if ((availableFavor - requestFavor) +
                    (availableFood - requestFood) +
                    (availableGold - requestGold) +
                    (availableWood - requestWood) - transactionFee >= 0 ) {
//                bank.
            }
            else {
                Log.i("TradeController", "Not enough resources to complete the trade");
            }
        }
    }

    public Bank getBank() { return bank; }

}
