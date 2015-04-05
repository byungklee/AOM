package com.anycomp.android.ageofmythology.model.player;


import com.anycomp.android.ageofmythology.Observable;
import com.anycomp.android.ageofmythology.Observer;

import com.anycomp.android.ageofmythology.model.age.*;
import com.anycomp.android.ageofmythology.model.board.PlayerBoard;
import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.card.CardDeck;
import com.anycomp.android.ageofmythology.model.card.CardFactory;
import com.anycomp.android.ageofmythology.model.card.CardType;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.culture.Egyptian;
import com.anycomp.android.ageofmythology.model.culture.Greek;
import com.anycomp.android.ageofmythology.model.culture.Norse;
import com.anycomp.android.ageofmythology.model.resource.*;
import com.anycomp.android.ageofmythology.model.unit.MortalUnit;
import com.anycomp.android.ageofmythology.model.unit.MortalUnitType;
import com.anycomp.android.ageofmythology.model.unit.Unit;

import java.util.ArrayList;

public class Player implements Observable {
	private Culture culture;
	private PlayerBoard playerBoard;
	private Cube goldCube;
	private Cube favorCube;
	private Cube woodCube;
	private Cube foodCube;
	private Cube victoryCube;
	private String name;
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
		goldCube = new GoldCube(20);
		favorCube = new FavorCube(20);
		woodCube = new WoodCube(20);
		foodCube = new FoodCube(20);
		victoryCube = new VictoryCube();
                hand = new CardDeck();
                randomCardPool = new CardDeck();
               // permanentCardPool = new Card[7];
                age = new ArchaicAge();
		observers = new ArrayList<Observer>();
                initPermanentCardPool();

        army = new ArrayList<>();
        initArmy();
	}

    /**
     * Initializes the players army according to their culture
     */
    private void initArmy() {
        if (culture instanceof Egyptian) {
            army.add(new MortalUnit(MortalUnitType.SPEARMAN));
            army.add(new MortalUnit(MortalUnitType.SPEARMAN));
            army.add(new MortalUnit(MortalUnitType.ELEPHANT));
            army.add(new MortalUnit(MortalUnitType.ELEPHANT));
            army.add(new MortalUnit(MortalUnitType.CHARIOT_ARCHER));
            army.add(new MortalUnit(MortalUnitType.CHARIOT_ARCHER));
        }
        else if (culture instanceof Greek) {
            army.add(new MortalUnit(MortalUnitType.TOXOTE));
            army.add(new MortalUnit(MortalUnitType.TOXOTE));
            army.add(new MortalUnit(MortalUnitType.HOPLITE));
            army.add(new MortalUnit(MortalUnitType.HOPLITE));
            army.add(new MortalUnit(MortalUnitType.HIPPOKON));
            army.add(new MortalUnit(MortalUnitType.HIPPOKON));
        }
        else if (culture instanceof Norse) {
            army.add(new MortalUnit(MortalUnitType.JARL));
            army.add(new MortalUnit(MortalUnitType.JARL));
            army.add(new MortalUnit(MortalUnitType.HUSKARL));
            army.add(new MortalUnit(MortalUnitType.HUSKARL));
            army.add(new MortalUnit(MortalUnitType.THROWING_AXEMAN));
            army.add(new MortalUnit(MortalUnitType.THROWING_AXEMAN));
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
        
        public void resetPermanentCardPool() {
            
        }
        
        public void pickCard(int index) {
            System.out.println("Player Class - clicked pickcard");
            //Move pool to deck if vaild.
            //if(getPermanentCardPool()[index] instanceof )
            if(!getHand().contains(getPermanentCardPool()[index]) && getHand().size() < age.getMaxCardAvailable())
                getHand().addCard(getPermanentCardPool()[index]);
        }
        
        public void playCard(int index, Object c) {
//            System.out.println("Player Class - clicked playcard " + index);
//            c.setCurrentCard(hand.getCardAt(index));
//            hand.getCardAt(index).play(c);
//
            
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

	public void setPlayerBoard(PlayerBoard playerBoard) {
		this.playerBoard = playerBoard;
	}

	public Cube getGoldCube() {
		return goldCube;
	}

	public void setGoldCube(Cube goldCube) {
		this.goldCube = goldCube;
	}

	public Cube getFavorCube() {
		return favorCube;
	}

	public void setFavorCube(Cube favorCube) {
		this.favorCube = favorCube;
	}

	public Cube getWoodCube() {
		return woodCube;
	}

	public void setWoodCube(Cube woodCube) {
		this.woodCube = woodCube;
	}

	public Cube getFoodCube() {
		return foodCube;
	}

	public void setFoodCube(Cube foodCube) {
		this.foodCube = foodCube;
	}

	public Cube getVictoryCube() {
		return victoryCube;
	}

	public void setVictoryCube(Cube victoryCube) {
		this.victoryCube = victoryCube;
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
	
}
