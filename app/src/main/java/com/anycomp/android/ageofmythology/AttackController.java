package com.anycomp.android.ageofmythology;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.unit.Unit;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by byung on 4/6/15.
 */
public class AttackController {

    FragmentManager fm;
    PlayerController pc;
    private int opponentPlayerIndex;
    ArrayList<Unit> attackers;
    ArrayList<Unit> defenders;
    private int numberOfUnitsAllowed;
    int counter = 0;
    boolean isHumanAttacking;
    //requires turn manager.

    private int attackerSelection;
    private int defenderSelection;

    public boolean isHumanAttacking() {
        return isHumanAttacking;
    }
    public AttackController(FragmentManager fm, PlayerController pc, int numberOfUnitsAllowed) {
        this.fm = fm;
        this.pc = pc;
        attackers = new ArrayList<>();
        defenders = new ArrayList<>();
        this.numberOfUnitsAllowed = numberOfUnitsAllowed;
    }

    public void startBattle() {
        if(pc.getCurrentPlayer() == pc.getHumanPlayer()) {
            openSelectOpponentDialog();
        } else {
            //AI work

            //Finding a target
            Random random = new Random();
            opponentPlayerIndex = random.nextInt(3);
            while(pc.getCurrentPlayer() == pc.getPlayers().get(opponentPlayerIndex)) {
                opponentPlayerIndex = random.nextInt(3);
            }

            //select units by ai
            aiPickBattleUnitCard(false);

            //if opponents is humanplayer, let human player picks their defense units
            if(pc.getPlayers().get(opponentPlayerIndex) == pc.getHumanPlayer()) {
                openPickBattleUnitDialog(false);
            } else {
                //AI VS AI
                aiDefenderPickBattleUnitCard();
            }

        }
    }

    public void openSelectOpponentDialog() {
        SelectOpponentDialogFragment sodf = new SelectOpponentDialogFragment();
        sodf.setPlayerController(pc);
        sodf.setAfterSelecttOpponentCallback(new AfterSelectOpponentCallback() {
            @Override
            public void callback(int i) {
                opponentPlayerIndex = i;
                openPickBattleUnitDialog(true);
                isHumanAttacking = true;
            }
        });
        sodf.show(fm,"Select Opponent Dialog");
    }

    public void openPickBattleUnitDialog(boolean isHumanAttacking) {

        PickBattleUnitDialogFragment pbudf = new PickBattleUnitDialogFragment();
        pbudf.setPlayerController(pc);
        pbudf.setIsHumanAttacking(isHumanAttacking);
        pbudf.setAttackController(this);
        pbudf.setCallback(new Callback() {
            @Override
            public void callback() {
                openBattleSceneDialog();
            }
        });
        pbudf.show(fm,"Pick Battle Unit Dialog");

    }

    public void openBattleSceneDialog() {
        BattleSceneDialogFragment bsdf = new BattleSceneDialogFragment();
        bsdf.setPlayerController(pc);
        bsdf.setAttackController(this);
        bsdf.show(fm,"Battle Scene Dialog");
    }


    public boolean pickBattleUnitCard(boolean isHumanAttacking, int index) {
        Player p = (Player) pc.getPlayers().get(0);
        int maxAllowed = p.hasBuilding(BuildingType.ARMORY) ? numberOfUnitsAllowed + 1: numberOfUnitsAllowed;
        if(counter >= maxAllowed) {
            return false;
        }

        counter++;
        if(isHumanAttacking) {
            attackers.add(p.getArmy().remove(index));
        } else {
            defenders.add(p.getArmy().remove(index));
        }
        return true;

    }

    public void aiPickBattleUnitCard(boolean isHumanAttacking) {
        if(isHumanAttacking) {
            //Defense picking
            Player p = (Player) pc.getPlayers().get(opponentPlayerIndex);
            ArrayList<Unit> unit = p.getArmy();
            int maxAllowedByAI = p.hasBuilding(BuildingType.ARMORY) == true ? numberOfUnitsAllowed + 1 : numberOfUnitsAllowed;
            Random random = new Random();

            for(int i=0;i<maxAllowedByAI;i++) {
                defenders.add(unit.remove(random.nextInt(unit.size())));
            }

        } else {
            //attack picking
            Player p = (Player) pc.getCurrentPlayer();
            ArrayList<Unit> unit = p.getArmy();
            int maxAllowedByAI = p.hasBuilding(BuildingType.ARMORY) == true ? numberOfUnitsAllowed + 1 : numberOfUnitsAllowed;
            Random random = new Random();

            for(int i=0;i<maxAllowedByAI;i++) {
                attackers.add(unit.remove(random.nextInt(unit.size())));
            }
        }
    }

    public void aiDefenderPickBattleUnitCard() {

            //Defense picking
            Player p = (Player) pc.getPlayers().get(opponentPlayerIndex);
            ArrayList<Unit> unit = p.getArmy();
            int maxAllowedByAI = p.hasBuilding(BuildingType.ARMORY) == true ? numberOfUnitsAllowed + 1 : numberOfUnitsAllowed;
            Random random = new Random();

            for(int i=0;i<maxAllowedByAI;i++) {
                defenders.add(unit.remove(random.nextInt(unit.size())));
            }
    }

    public ArrayList<Unit> getAttackers() {
        return attackers;
    }

    public ArrayList<Unit> getDefenders() {
        return defenders;
    }


    private int attackerDice;
    private int attackerPossibleDice;
    private int defenderDice;
    private int defenderPossibleDice;
    private int attackerScore = 0;
    private int defenderScore = 0;
    public void battle() {
        //load unit
        Unit au = attackers.get(attackerSelection);
        Unit du = defenders.get(defenderSelection);

        //calculate
//        int additionalAttackerDice = au.getAdditionalDice(du);
//        int additionalDefenderDice = du.getAdditionalDice(au);
        setAttackerPossibleDice(au.getAdditionalDice(du) + au.getDice());
        setDefenderPossibleDice(du.getAdditionalDice(au) + du.getDice());
    }

    public boolean roll() {
        attackerDice = random.nextInt(getAttackerPossibleDice())+1;
        defenderDice = random.nextInt(getDefenderPossibleDice())+1;
        if(attackerDice > defenderDice) {
            attackerScore++;
            return true;
        } else if(defenderDice > attackerDice) {
            defenderScore++;
            return true;
        }
        return false;
    }

    public int getAttackerDice() {
        return attackerDice;
    }

    public int getDefenderDice() {
        return defenderDice;
    }

    public int getAttackerScore() {
        return attackerScore;
    }

    public int getDefenderScore() {
        return defenderScore;
    }


    public void resetInfo() {

        attackerDice=0;
        defenderDice=0;

    }
    boolean isAttackerWin = false;
    public boolean nextBattle() {
        if(attackerDice > defenderDice) {
            defenders.remove(defenderSelection);
        } else if(defenderDice > attackerDice) {
            attackers.remove(attackerSelection);
        }
        if(attackers.size() == 0) {
            isAttackerWin = true;
            return false;
        }else if(defenders.size() == 0) {
            isAttackerWin = false;
            return false;
        }
        //remove
        resetInfo();
        return true;
    }



    public void retreat(int playerIndex) {
        //playerIndex has retreated.
        if(playerIndex == opponentPlayerIndex) {
            isAttackerWin = true;
        } else {
            isAttackerWin = false;
        }
    }

    public void battleEnd() {

    }


    public int getDefenderSelection() {
        return defenderSelection;
    }

    public void setDefenderSelection(int defenderSelection) {
        this.defenderSelection = defenderSelection;
    }

    public int getAttackerSelection() {
        return attackerSelection;
    }

    public void setAttackerSelection(int attackerSelection) {
        this.attackerSelection = attackerSelection;
    }
    Random random = new Random();
    public void aiDefenderChooseRandomUnit() {

        int index = random.nextInt(getDefenders().size());
        // defendCard.setImageResource(ac.getDefenders().get(position).getImagePath());
        setDefenderSelection(index);
    }

    public void aiAttackerChooseRandomUnit() {
        int index = random.nextInt(getAttackers().size());
        setAttackerSelection(index);
    }

    public int getAttackerPossibleDice() {
        return attackerPossibleDice;
    }

    public void setAttackerPossibleDice(int attackerPossibleDice) {
        this.attackerPossibleDice = attackerPossibleDice;
    }

    public int getDefenderPossibleDice() {
        return defenderPossibleDice;
    }

    public void setDefenderPossibleDice(int defenderPossibleDice) {
        this.defenderPossibleDice = defenderPossibleDice;
    }

    public void winnerTakeVictoryCube() {
        int value = pc.getVictoryCardDeck().getVictoryCards().get(2).getVictoryCubes().size();
        Player p;
        if(isAttackerWin) {
            p = pc.getCurrentPlayer();
        } else {
            p = (Player) pc.getPlayers().get(opponentPlayerIndex);
        }
        p.takeVictoryFromCard(value);
        pc.getVictoryCardDeck().getVictoryCards().get(2).getVictoryCubes().clear();
    }
}
