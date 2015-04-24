package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.AskGodPowerUseDialogFragment;
import com.anycomp.android.ageofmythology.AttackController;
import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.culture.Culture;

/**
 * Created by byung on 4/19/15.
 * Implementation of sekhmet
 * 8 unit 6 oppo
 * 3 cubes
 */
public class GodEgyptAttackCard extends RandomAttackCard implements God {


    public GodEgyptAttackCard(PermanentAttackCard card) {
        setName("GodEgyptAttack");
        this.card=card;
        setImagePath(R.drawable.card_rand_egypt_attack_sekhmet);
        setValue(8);
        favorCost = 3;  // must!! it is declared in RandomCard.java
    }

    public Culture getCulture() {
        return card.getCulture();
    }

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
            //For ai, if you can pay, they play god otherwise normal.
            if(payFavor()) {
                playGod();
            } else {
                playNormal();
            }
        }
    }

    //God mode
    @Override
    public void playGod() {
            AttackController ac = new AttackController(this, fm, pc, getValue(),true);
            ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
            ac.startBattle();
    }

    //Normal mode
    @Override
    public void playNormal() {
         setValue(6);
         AttackController ac = new AttackController(this, fm, pc, getValue(),false);
         ac.setAttackPlayerIndex(pc.getTurnManager().getCurrentPlayer());
         ac.startBattle();
    }

    //You can copy paste in all other god cards. This is for God interface.
    @Override
    public boolean payFavor() {
        return pay();
    }
}
