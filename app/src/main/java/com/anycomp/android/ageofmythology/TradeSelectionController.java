package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.anycomp.android.ageofmythology.model.bank.Bank;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.card.God;
import com.anycomp.android.ageofmythology.model.card.PermanentTradeCard;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;

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

    public void makeTrade(int requestFavor, int requestFood, int requestGold, int requestWood, ResourceType tradeType, int amount, ResourceType costType1, ResourceType costType2) {
        Player player = pc.getCurrentPlayer();

        // check that the amounts are equivalent
        if (requestFavor + requestFood + requestGold + requestWood != amount) {
            Log.i("TradeController", "Trade aborted. The amount requested doesn't match the amount the player is trading.");
            return;
        }

        int transactionFee = 0;
        // check to see if the player has a market
        if (!player.hasBuilding(BuildingType.MARKET) && !(card instanceof God)) {
            // if not, set transaction fee
            transactionFee = 2;
        }

        // check that the player has sufficient resources
        if ((availableFavor - requestFavor) +
                (availableFood - requestFood) +
                (availableGold - requestGold) +
                (availableWood - requestWood) - transactionFee >= 0 ) {
            // take player's resource and deposit it into bank
            for (int i=0; i<transactionFee; i++) {
                ResourceType costType = costType1;
                if (i==1) costType = costType2;
                switch (costType) {
                    case FAVOR:
                        if (availableFavor >= 1) {
                            player.getFavorCube().setValue(--availableFavor);
                        } else {
                            Log.i("TradeController", "Not enough resources to pay.");
                            return;
                        }
                        break;
                    case FOOD:
                        if (availableFood >= 1) {
                            player.getFoodCube().setValue(--availableFood);
                        } else {
                            Log.i("TradeController", "Not enough resources to pay.");
                            return;
                        }
                        break;
                    case GOLD:
                        if (availableGold >= 1) {
                            player.getGoldCube().setValue(--availableGold);
                        } else {
                            Log.i("TradeController", "Not enough resources to pay.");
                            return;
                        }
                        break;
                    case WOOD:
                        if (availableWood >= 1) {
                            player.getWoodCube().setValue(--availableWood);
                        } else {
                            Log.i("TradeController", "Not enough resources to pay.");
                            return;
                        }
                        break;
                    default:
                        break;
                }
                bank.deposit(costType, 1);
            }

            // check to make sure the player still has enough to trade the amount requested
            switch (tradeType) {
                case FAVOR:
                    if (player.getFavorCube().getValue() >= amount) {
                        player.getFavorCube().setValue(player.getFavorCube().getValue() - amount);
                    }
                    else {
                        Log.i("TradeController", "Not enough resources to continue with trade.");
                        return;
                    }
                    break;
                case FOOD:
                    if (player.getFoodCube().getValue() >= amount) {
                        player.getFoodCube().setValue(player.getFoodCube().getValue() - amount);
                    }
                    else {
                        Log.i("TradeController", "Not enough resources to continue with trade.");
                        return;
                    }
                    break;
                case GOLD:
                    if (player.getGoldCube().getValue() >= amount) {
                        player.getGoldCube().setValue(player.getGoldCube().getValue() - amount);
                    }
                    else {
                        Log.i("TradeController", "Not enough resources to continue with trade.");
                        return;
                    }
                    break;
                case WOOD:
                    if (player.getWoodCube().getValue() >= amount) {
                        player.getWoodCube().setValue(player.getWoodCube().getValue() - amount);
                    }
                    else {
                        Log.i("TradeController", "Not enough resources to continue with trade.");
                        return;
                    }
                    break;
                default:
                    break;
            }
            bank.deposit(tradeType, amount);
            player.takeFavor(bank.withdraw(ResourceType.FAVOR, requestFavor));
            player.takeFood(bank.withdraw(ResourceType.FOOD, requestFood));
            player.takeGold(bank.withdraw(ResourceType.GOLD, requestGold));
            player.takeWood(bank.withdraw(ResourceType.WOOD, requestWood));

            player.resourceUpdate();
        }
        else {
            Log.i("TradeController", "Not enough resources to complete the trade");
        }

    }

    public Player getPlayer() { return pc.getCurrentPlayer(); }
    public Bank getBank() { return bank; }
    public void nextRound() { pc.nextRound(); }

}
