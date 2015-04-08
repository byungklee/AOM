package com.anycomp.android.ageofmythology.model.player;


import com.anycomp.android.ageofmythology.Observable;
import com.anycomp.android.ageofmythology.Observer;

import com.anycomp.android.ageofmythology.VillagerController;
import com.anycomp.android.ageofmythology.model.age.*;
import com.anycomp.android.ageofmythology.model.area.HoldingArea;
import com.anycomp.android.ageofmythology.model.bank.Bank;
import com.anycomp.android.ageofmythology.model.board.PlayerBoard;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.card.CardDeck;
import com.anycomp.android.ageofmythology.model.card.CardFactory;
import com.anycomp.android.ageofmythology.model.card.CardType;
import com.anycomp.android.ageofmythology.model.card.RandomCard;

import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.culture.Egyptian;
import com.anycomp.android.ageofmythology.model.culture.Greek;

import com.anycomp.android.ageofmythology.model.resource.*;

import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.tile.BuildingTile;

import com.anycomp.android.ageofmythology.model.unit.specific.ChariotArcher;
import com.anycomp.android.ageofmythology.model.unit.specific.Elephant;
import com.anycomp.android.ageofmythology.model.unit.specific.Hippokon;
import com.anycomp.android.ageofmythology.model.unit.specific.Hoplite;
import com.anycomp.android.ageofmythology.model.unit.specific.Huskarl;
import com.anycomp.android.ageofmythology.model.unit.specific.Jarl;
import com.anycomp.android.ageofmythology.model.unit.specific.Spearman;
import com.anycomp.android.ageofmythology.model.unit.specific.ThrowingAxeman;
import com.anycomp.android.ageofmythology.model.unit.specific.Toxotes;



import java.util.ArrayList;
import java.util.Iterator;

public class Player implements Observable {
	private Culture culture;
	private PlayerBoard playerBoard;
	private Cube goldCube;
	private Cube favorCube;
	private Cube woodCube;
	private Cube foodCube;
	private Cube victoryCube;
	private String name;
    private VillagerController villagerController;
    private ArrayList<Observer> resourceObservers;


        //private PermanentCardPools
        private Card[] permanentCardPool;
        private CardDeck hand;
        private CardDeck randomCardPool;
	    private ArrayList<Observer> observers;
        private Age age;
    private ArrayList<Unit> army;

	public Player(String name, Culture culture, PlayerBoard board) {
		this.setCulture(culture);
		this.name = name;
		playerBoard = board;

        //TO DO: After bank implementation...

		goldCube = new GoldCube(0);
		favorCube = new FavorCube(0);
		woodCube = new WoodCube(0);
		foodCube = new FoodCube(0);
        victoryCube = new VictoryCube(0);
        villagerController = new VillagerController();
        resourceObservers = new ArrayList<>();
        hand = new CardDeck();
        randomCardPool = new CardDeck();
        // permanentCardPool = new Card[7];
        age = new ArchaicAge();
        observers = new ArrayList<>();
        takeGold(4);
        takeFavor(4);
        takeFood(4);
        takeWood(4);
        //takeVictory(1);
        initPermanentCardPool();

        army = new ArrayList<>();
        initArmy();
	}

    /**
     * Initializes the players army according to their culture
     */
//    private void initArmy() {
//        if (culture instanceof Egyptian) {
//            army.add(new MortalUnit(MortalUnitType.SPEARMAN));
//            army.add(new MortalUnit(MortalUnitType.SPEARMAN));
//            army.add(new MortalUnit(MortalUnitType.ELEPHANT));
//            army.add(new MortalUnit(MortalUnitType.ELEPHANT));
//            army.add(new MortalUnit(MortalUnitType.CHARIOT_ARCHER));
//            army.add(new MortalUnit(MortalUnitType.CHARIOT_ARCHER));
//        }
//        else if (culture instanceof Greek) {
//            army.add(new MortalUnit(MortalUnitType.TOXOTE));
//            army.add(new MortalUnit(MortalUnitType.TOXOTE));
//            army.add(new MortalUnit(MortalUnitType.HOPLITE));
//            army.add(new MortalUnit(MortalUnitType.HOPLITE));
//            army.add(new MortalUnit(MortalUnitType.HIPPOKON));
//            army.add(new MortalUnit(MortalUnitType.HIPPOKON));
//        }
//        else if (culture instanceof Norse) {
//            army.add(new MortalUnit(MortalUnitType.JARL));
//            army.add(new MortalUnit(MortalUnitType.JARL));
//            army.add(new MortalUnit(MortalUnitType.HUSKARL));
//            army.add(new MortalUnit(MortalUnitType.HUSKARL));
//            army.add(new MortalUnit(MortalUnitType.THROWING_AXEMAN));
//            army.add(new MortalUnit(MortalUnitType.THROWING_AXEMAN));
//        }
//    }
    private void initArmy() {
        if(culture instanceof Egyptian) {
            army.add(new Spearman());
            army.add(new Spearman());
            army.add(new Elephant());
            army.add(new Elephant());
            army.add(new ChariotArcher());
        }
        else if(culture instanceof Greek) {
            army.add(new Toxotes());
            army.add(new Toxotes());
            army.add(new Hoplite());
            army.add(new Hoplite());
            army.add(new Hippokon());
            army.add(new Hippokon());
        }
        else {
            army.add(new Jarl());
            army.add(new Jarl());
            army.add(new Huskarl());
            army.add(new Huskarl());
            army.add(new ThrowingAxeman());
            army.add(new ThrowingAxeman());
        }
    }
        
        private void initPermanentCardPool() {
            setPermanentCardPool(new Card[7]);
            CardType[] ct = CardType.values();
            for(int i=0;i<7;i++) {
                getPermanentCardPool()[i] = CardFactory.newPermanentCard(ct[i], culture);
            }
        }

        //TO DO
        private void initRandomCardDeck() {

        }
        
        public void resetHand() {
            for(int i=0;i<hand.size();i++) {
                Card card = hand.getCardAt(i);
                card.resetCardStatus();
                if(card instanceof RandomCard) {
                    randomCardPool.addCard(hand.getCardAt(i));
                }
            }
            randomCardPool.shuffle();
            hand.clean();

        }

        /**
        * //Move pool to deck if vaild.
        * @param index
        * @return
        */
        public boolean pickCard(int index) {
            System.out.println("Player Class - clicked pickcard");

            //this if statement is for random card
            if(index == 7) {
                return false;
            }

            Card pickedCard = getPermanentCardPool()[index];
             if(!pickedCard.isPicked() && getHand().size() < age.getMaxCardAvailable()) {

                 getHand().addCard(getPermanentCardPool()[index]);
                 pickedCard.setPicked(true);
                 //getPermanentCardPool()[index]
                 return true;
             }
            return false;

        }
	
	public Culture getCulture() {
		return culture;
	}

	public void setCulture(Culture culture) {
		this.culture = culture;
	}

	public PlayerBoard getPlayerBoard() {
		return playerBoard;
	}

	//public void setPlayerBoard(PlayerBoard playerBoard) {
//		this.playerBoard = playerBoard;
//	}

	public Cube getGoldCube() {
		return goldCube;
	}

    public void spendGold(int amount) {
        Bank.getInstance().deposit(ResourceType.GOLD, amount);
        goldCube.setValue(goldCube.getValue() - amount);
        resourceUpdate();
    }

    public void takeGold(int amount) {
        Bank.getInstance().withdraw(ResourceType.GOLD, amount);
        goldCube.setValue(goldCube.getValue()+amount);
        resourceUpdate();
    }

	public Cube getFavorCube() {
		return favorCube;
	}

    public void spendFavor(int amount) {
        Bank.getInstance().deposit(ResourceType.FAVOR, amount);
        favorCube.setValue(favorCube.getValue()-amount);
        resourceUpdate();
    }

    public void takeFavor(int amount) {
        Bank.getInstance().withdraw(ResourceType.FAVOR, amount);
        favorCube.setValue(favorCube.getValue()+amount);
        resourceUpdate();
    }
	public Cube getWoodCube() {
		return woodCube;
	}

    public void spendWood(int amount) {
        Bank.getInstance().deposit(ResourceType.WOOD, amount);
        woodCube.setValue(woodCube.getValue()-amount);
        resourceUpdate();
    }

    public void takeWood(int amount) {
        Bank.getInstance().withdraw(ResourceType.WOOD, amount);
        woodCube.setValue(woodCube.getValue()+amount);
        resourceUpdate();
    }

//	public void setWoodCube(Cube woodCube) {
//		this.woodCube = woodCube;
//	}

	public Cube getFoodCube() {
		return foodCube;
	}
    public void spendFood(int amount) {
        Bank.getInstance().deposit(ResourceType.FOOD, amount);
        foodCube.setValue(foodCube.getValue()-amount);
        resourceUpdate();
    }

    public void takeFood(int amount) {
        Bank.getInstance().withdraw(ResourceType.FOOD, amount);
        foodCube.setValue(foodCube.getValue()+amount);
        resourceUpdate();
    }

//	public void setFoodCube(Cube foodCube) {
//		this.foodCube = foodCube;
//	}

	public Cube getVictoryCube() {
		return victoryCube;
	}
    public void spendVictory(int amount) {
        Bank.getInstance().depositVictory(amount);
        victoryCube.setValue(victoryCube.getValue()-amount);
        resourceUpdate();
    }

    public void takeVictory(int amount) {
        Bank.getInstance().withdrawVictory(amount);
        victoryCube.setValue(victoryCube.getValue()+amount);
        resourceUpdate();
    }

    public void takeVictoryFromCard(int amount) {
        victoryCube.setValue(victoryCube.getValue()+amount);
        resourceUpdate();
    }

    public void incrementNumberOfVillager() {
        ((HoldingArea) getPlayerBoard().getHoldingArea()).incrementNumberOfVillagers();
        resourceUpdate();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public ArrayList<Unit> getArmy() { return army; }

	@Override
	public void attachObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
	}


	@Override
	public void detachObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}


	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer ob : observers) {
			ob.update(this);
		}
	}

    /**
     * @return the randomCardPool
     */
    public CardDeck getRandomCardPool() {
        return randomCardPool;
    }

    /**
     * @param randomCardPool the randomCardPool to set
     */
    public void setRandomCardPool(CardDeck randomCardPool) {
        this.randomCardPool = randomCardPool;
    }

    /**
     * @return the hand
     */
    public CardDeck getHand() {
        return hand;
    }

    /**
     * @param hand the hand to set
     */
    public void setHand(CardDeck hand) {
        this.hand = hand;
    }

    /**
     * @return the permanentCardPool
     */
    public Card[] getPermanentCardPool() {
        return permanentCardPool;
    }

    /**
     * @param permanentCardPool the permanentCardPool to set
     */
    public void setPermanentCardPool(Card[] permanentCardPool) {
        this.permanentCardPool = permanentCardPool;
    }

    /**
     * The method is to use the building effect.
     * @param bType buidling type to check the player has the type of building
     * @return
     */
    public boolean hasBuilding(BuildingType bType) {
        ArrayList al = getPlayerBoard().getCityArea().getTiles();
        for(int i=0;i<al.size();i++) {
            BuildingTile bt = (BuildingTile) al.get(i);
            if(bt.getBuilding() != null) {
                if (bt.getBuilding().getBuildingType() == bType) {
                    return true;
                }
            }
        }
        return false;
    }
    public Age getAge() {
        return age;
    }

    public void attachResourceObserver(Observer ob) {
        resourceObservers.add(ob);
    }

    public void detachResourceObserver(Observer ob) {
        resourceObservers.remove(ob);
    }
    public void resourceUpdate() {
        Iterator it = resourceObservers.iterator();
        while(it.hasNext()) {
            ((Observer)it.next()).update(this);
        }
    }

    //TO DO: change return type and return unit list.
    public void getUnitList() {

    }

    public void setAge(Age age) { this.age = age; resourceUpdate(); }
}
