package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;
import android.util.Log;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.ProfitResourceDialogFragment;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.TradeSelectionController;
import com.anycomp.android.ageofmythology.TradeSelectionDialogFragment;
import com.anycomp.android.ageofmythology.model.bank.Bank;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;

/**
 * Created by mike on 5/1/15.
 */
public class GodEgyptTradeCard extends RandomTradeCard implements God {

    private static final String TAG = "GodEgyptTrade";

    public GodEgyptTradeCard(RandomTradeCard card) {
        setName(TAG);
        this.card=card;
        setImagePath(R.drawable.card_rand_egypt_trade6);
        setValue(0);
        favorCost = 1;  // must!! it is declared in RandomCard.java
    }

    public Culture getCulture() { return card.getCulture(); }

    //User call askgodpower dialogframent, and setGod(this) is must.
    @Override
    public void play(FragmentManager fm, PlayerController pc) {

        // must!! they are declared in RandomCard.java
        this.pc = pc;
        this.fm = fm;

        if(!isPlayed()) {
            setPlayed(true);

            //Calling askgodpower; You don't need to worry about the implementation detail in AskGodPowerDialogFragment.
            //What happens is if an user clicks no or has not enough favor cube, it calls playNormal(); otherwise, playGod().
            AskGodPowerUseDialogFragment agpud = new AskGodPowerUseDialogFragment();
            agpud.setGod(this);
            agpud.show(fm, "UseGod");
        }
    }

    //AI choose to play.
    @Override
    public void aiPlay(FragmentManager fm, PlayerController pc) {
        this.pc = pc;
        this.fm = fm;
        if(!isPlayed()) {
            setPlayed(true);
            //For ai, if you can pay, then play god.
            if(payFavor()) {
                Bank bank = Bank.getInstance();
                Player ai = pc.getCurrentPlayer();
                // take the profit
                for (int i=0; i<4; i++) {
                    int rand = (int)(Math.random()*4);
                    switch (rand) {
                        case 0:
                            if (bank.getFavor()>0) {
                                bank.withdraw(ResourceType.FAVOR, 1);
                                ai.takeFavor(1);
                                break;
                            }
                        case 1:
                            if (bank.getFood()>0) {
                                bank.withdraw(ResourceType.FOOD, 1);
                                ai.takeFood(1);
                                break;
                            }
                        case 2:
                            if (bank.getGold()>0) {
                                bank.withdraw(ResourceType.GOLD, 1);
                                ai.takeGold(1);
                                break;
                            }
                        case 3:
                            if (bank.getWood()>0) {
                                bank.withdraw(ResourceType.WOOD, 1);
                                ai.takeWood(1);
                                break;
                            }
                        default:
                            break;
                    }
                }
            }
            // then play normal regardless
            new PermanentTradeCard(this).aiPlay(fm, pc);
        }
    }

    @Override
    public void playGod() {
        ProfitResourceDialogFragment prdf = new ProfitResourceDialogFragment();
        prdf.setCard(this);
        prdf.setController(pc);
        prdf.setMaxResourceCanTake(4);
        prdf.show(fm, TAG);
    }

    @Override
    public void playNormal() {
        Log.i(TAG, "called playNormal()");
        TradeSelectionController tsc = new TradeSelectionController(pc);
        tsc.playTradeCard(this);
        TradeSelectionDialogFragment tsdf = TradeSelectionDialogFragment.newInstance(tsc);
        tsdf.show(fm, TAG);
    }

    @Override
    public boolean payFavor() {
        return pay();
    }

    @Override
    public boolean checkAge() {
        return true;
    }

    @Override
    public String toString() {
        return TAG;
    }
}
