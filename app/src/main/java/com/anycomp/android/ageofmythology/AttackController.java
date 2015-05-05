package com.anycomp.android.ageofmythology;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.model.area.AreaType;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.card.God;
import com.anycomp.android.ageofmythology.model.card.GodEgyptAttackCard;
import com.anycomp.android.ageofmythology.model.card.GodGreekAttackCard;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.unit.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by byung on 4/6/15.
 */
public class AttackController {

    private FragmentManager fm;
    private PlayerController pc;
    private int attackPlayerIndex;
    private int opponentPlayerIndex;
    private ArrayList<Unit> attackers;
    private ArrayList<Unit> defenders;
    private int numberOfUnitsAllowed;
    private int counter = 0;
    private boolean isHumanAttacking;
    private int buildingEffect = 0;
    private AreaType attackArea;
    private Random random = new Random();
    private Card mAttackCard;
    private int bragiEffect;
    //requires turn manager.

    private int attackerSelection;
    private int defenderSelection;
    private boolean isGod;

    public boolean isHumanAttacking() {
        return isHumanAttacking;
    }

    public AttackController(Card card, FragmentManager fm, PlayerController pc, int numberOfUnitsAllowed, boolean isGod) {
        mAttackCard = card;
        this.fm = fm;
        this.pc = pc;
        attackers = new ArrayList<>();
        defenders = new ArrayList<>();
        this.numberOfUnitsAllowed = numberOfUnitsAllowed;
        setBragiEffect(0);
        this.isGod=isGod;
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

               //fight start
               //for ai get sum of all attack card; whoever has biggest wins, if tie, then attacker wins.
                int attackSum=0;
                int defendSum=0;
                Iterator it = attackers.iterator();
                while(it.hasNext()) {
                    Unit u = (Unit) it.next();
                    attackSum += u.getDice();
                }
                it = defenders.iterator();
                while(it.hasNext()) {
                    Unit u = (Unit) it.next();
                    defendSum += u.getDice();
                }
                defendSum = defendSum + buildingEffect*defenders.size();
                if(attackSum >= defendSum) {
                    isAttackerWin=true;
                    defenders.clear();

                    //lose half of the unit
                    int temp = attackers.size();
                    while(temp-- > attackers.size()/2) {
                        attackers.remove(0);
                    }
                } else {
                    isAttackerWin=false;
                    attackers.clear();
                    //lose half of the unit
                    int temp = defenders.size();
                    while(temp-- > defenders.size()/2) {
                        defenders.remove(0);
                    }
                }
                moveAllTheUnitBack();
                winnerTakeVictoryCube();
                if(isAttackerWin) {
                    if(pc.getCurrentPlayer().hasBuilding(BuildingType.SIEGE_ENGINE_WORKSHOP)) {
                        BuildingDestructionController bdc = new BuildingDestructionController(pc, true, 1);
                        bdc.setTargetPlayer(opponentPlayerIndex);
                        bdc.destroyBuilding(0);
                    }
                    takeResources();
                }
                else {
                    pc.nextRound();
                }

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
                //openPickBattleUnitDialog(true);
                openAttackAreaDialog();
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
        setup();
        BattleSceneDialogFragment bsdf = new BattleSceneDialogFragment();
        bsdf.setPlayerController(pc);
        bsdf.setAttackController(this);
        bsdf.show(fm,"Battle Scene Dialog");
    }

    public void setup() {
        //building effect
        Player attacker = (Player) pc.getPlayers().get(attackPlayerIndex);
        Player defender = (Player) pc.getPlayers().get(opponentPlayerIndex);
        if(attacker.hasBuilding(BuildingType.SIEGE_ENGINE_WORKSHOP)) {
            setBuildingEffect(0);
            return;
        }

        if(attackArea == AreaType.CITY) {
            if(defender.hasBuilding(BuildingType.WALL)) {
                setBuildingEffect(2);
            } else
                setBuildingEffect(0);
        } else if(attackArea == AreaType.PRODUCTION) {
            if(defender.hasBuilding(BuildingType.TOWER)) {
                setBuildingEffect(2);
            } else
                setBuildingEffect(0);
        }
    }

    public void openAttackAreaDialog() {
        PickAttackAreaDialogFragment paadf = new PickAttackAreaDialogFragment();
        paadf.setAttackController(this);
        paadf.show(fm, "Attack Area");
    }

    public boolean pickBattleUnitCard(boolean isHumanAttacking, int index) {
        Player p = (Player) pc.getPlayers().get(0);

        int maxAllowed = p.hasBuilding(BuildingType.ARMORY) ? numberOfUnitsAllowed + 1: numberOfUnitsAllowed;
        if(!isHumanAttacking && (mAttackCard instanceof GodEgyptAttackCard || mAttackCard instanceof GodGreekAttackCard)) {
            if(isGod)
                maxAllowed -= 2;
        }
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
            if((mAttackCard instanceof GodEgyptAttackCard || mAttackCard instanceof GodGreekAttackCard)) {
                if(isGod)
                    maxAllowedByAI -= 2;
            }
            Random random = new Random();

            for(int i=0;i<maxAllowedByAI;i++) {
                if(unit.size() == 0) {
                    break;
                }
                defenders.add(unit.remove(random.nextInt(unit.size())));
            }

        } else {
            //attack picking
            Player p = pc.getCurrentPlayer();
            ArrayList<Unit> unit = p.getArmy();
            //int maxAllowedByAI = p.hasBuilding(BuildingType.ARMORY) == true ? numberOfUnitsAllowed + 1 : numberOfUnitsAllowed;
            int maxAllowedByAI = numberOfUnitsAllowed;
            for(int i=0;i<maxAllowedByAI;i++) {
                if(unit.size() == 0) {
                    break;
                }
                attackers.add(unit.remove(random.nextInt(unit.size())));
            }
        }
    }

    public void aiDefenderPickBattleUnitCard() {

            //Defense picking
            Player p = (Player) pc.getPlayers().get(opponentPlayerIndex);
            ArrayList<Unit> unit = p.getArmy();
            int maxAllowedByAI = p.hasBuilding(BuildingType.ARMORY) == true ? numberOfUnitsAllowed + 1 : numberOfUnitsAllowed;
            if((mAttackCard instanceof GodEgyptAttackCard || mAttackCard instanceof GodGreekAttackCard)) {
                maxAllowedByAI -= 2;
            }
            Random random = new Random();
            if(unit.size() == 0) {
                return;
            }
            for(int i=0;i<maxAllowedByAI;i++) {
                if(unit.size() == 0) {
                    return;
                }
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
        int negateBuildingEffect = au.isDoesNegateWallAndTower() ? 2:0;
        //calculate
        setAttackerPossibleDice(au.getAdditionalDice(du) + au.getDice()-negateBuildingEffect + getBragiEffect());
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
            isAttackerWin = false;
            return false;
        }else if(defenders.size() == 0) {
            isAttackerWin = true;
            return false;
        }
        //remove
        resetInfo();
        return true;
    }

    public boolean decideWinner() {
        if(attackers.size() == 0) {
            isAttackerWin = false;
            return true;
        }else if(defenders.size() == 0) {
            isAttackerWin = true;
            return true;
        }
        return false;
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

    public void moveAllTheUnitBack() {
        if(!attackers.isEmpty()) {
            Iterator it = attackers.iterator();
            while(it.hasNext()) {
                Unit u =  (Unit) it.next();
                pc.getCurrentPlayer().getArmy().add(u);
            }
            attackers.clear();
        }
        if(!defenders.isEmpty()) {
            Iterator it = defenders.iterator();
            while(it.hasNext()) {
                Unit u =  (Unit) it.next();
                ((Player)pc.getPlayers().get(opponentPlayerIndex)).getArmy().add(u);
            }
            defenders.clear();
        }
    }

    public void takeTrophy() {
        //if(attackArea)
        if(isAttackerWin) {
            if (attackArea == AreaType.HOLDING) {
                takeResources();
            } else if (attackArea == AreaType.PRODUCTION) {
                takeResourceTile();
            } else {
                if(pc.getCurrentPlayer().hasBuilding(BuildingType.SIEGE_ENGINE_WORKSHOP)) {
                    destroyBuilding(2);
                } else
                    destroyBuilding(1);
            }
        }
    }

    public void takeResources() {
        System.out.println("To do: take  5 resource");
        if(isHumanAttacking) {
            TakeResourceDialogFragment t = new TakeResourceDialogFragment();
            t.setDefender((Player)pc.getPlayers().get(opponentPlayerIndex));
            t.setOffender((Player) pc.getPlayers().get(attackPlayerIndex));
            t.setPC(pc);
            t.setAttackController(this);
            t.show(fm, "TakeResource");
        } else {
            Player def = (Player)pc.getPlayers().get(opponentPlayerIndex);
            Player att = (Player)pc.getPlayers().get(attackPlayerIndex);
            int taken = 0;
            int maxCanTake=5;
            if(def.getFavorCube().getValue() > 0) {
                int temp = def.getFavorCube().getValue();
                int takeValue = Math.min(temp,maxCanTake-taken);
                def.spendFavor(takeValue);
                att.takeFavor(takeValue);
                taken += takeValue;
            }
            if(taken < 5) {
                if (def.getFoodCube().getValue() > 0) {
                    int temp = def.getFoodCube().getValue();
                    int takeValue = Math.min(temp, maxCanTake - taken);
                    def.spendFood(takeValue);
                    att.takeFood(takeValue);
                    taken += takeValue;
                }
            }
            if(taken < 5) {
                if (def.getWoodCube().getValue() > 0) {
                    int temp = def.getWoodCube().getValue();
                    int takeValue = Math.min(temp, maxCanTake - taken);
                    def.spendWood(takeValue);
                    att.takeWood(takeValue);
                    taken += takeValue;
                }
            }
            if(taken < 5) {
                if (def.getGoldCube().getValue() > 0) {
                    int temp = def.getGoldCube().getValue();
                    int takeValue = Math.min(temp, maxCanTake - taken);
                    def.spendGold(takeValue);
                    att.takeGold(takeValue);
                    //taken += takeValue;
                }
            }
            pc.nextRound();
        }


    }

    public void takeResourceTile() {
        //TO DO:
        System.out.println("To do: take resource tile");
//        pc.nextRound();
        TakeResourceTileDialogFragment trtdf = new TakeResourceTileDialogFragment();
        trtdf.setPC(pc);
        trtdf.setAttacker(attackPlayerIndex);
        trtdf.setTargetPlayer(opponentPlayerIndex);
        trtdf.setMaxC(1);
        trtdf.show(fm, "Take Resource Tiles");
        trtdf.setAttackController(this);
    }

    public void destroyBuilding(int numberOfBuilding) {
        //TO DO:
        System.out.println("To do: destroy building");
//        int defaultValue = 1;
        BuildingDestructionController bc = new BuildingDestructionController(pc,false,numberOfBuilding);
        bc.setTargetPlayer(opponentPlayerIndex);
        BuildingDestructionDialogFragment bd = new BuildingDestructionDialogFragment();
        bd.setBuildingDestructionController(bc);
        bd.show(fm,"DestroyBuilding");
    }

    public AreaType getAttackArea() {
        return attackArea;
    }

    public void setAttackArea(AreaType attackArea) {
        this.attackArea = attackArea;
    }

    public int getBuildingEffect() {
        return buildingEffect;
    }

    public void setBuildingEffect(int buildingEffect) {
        this.buildingEffect = buildingEffect;
    }

    public int getAttackPlayerIndex() {
        return attackPlayerIndex;
    }

    public void setAttackPlayerIndex(int attackPlayerIndex) {
        this.attackPlayerIndex = attackPlayerIndex;
    }

    public int getBragiEffect() {
        return bragiEffect;
    }

    public void setBragiEffect(int bragiEffect) {
        this.bragiEffect = bragiEffect;
    }
}
