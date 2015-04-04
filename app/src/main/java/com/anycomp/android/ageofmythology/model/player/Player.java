package com.anycomp.android.ageofmythology.model.player;


import com.anycomp.android.ageofmythology.Observable;
import com.anycomp.android.ageofmythology.Observer;

import com.anycomp.android.ageofmythology.VillagerController;
import com.anycomp.android.ageofmythology.model.age.*;
import com.anycomp.android.ageofmythology.model.board.PlayerBoard;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.card.CardDeck;
import com.anycomp.android.ageofmythology.model.card.CardFactory;
import com.anycomp.android.ageofmythology.model.card.CardType;
import com.anycomp.android.ageofmythology.model.card.RandomCard;
import com.anycomp.android.ageofmythology.model.card.VictoryCardDeck;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.resource.*;
import com.anycomp.android.ageofmythology.model.tile.BuildingTile;
import com.anycomp.android.ageofmythology.model.unit.*;

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
    private VillagerController villagerController;
    private VictoryCardDeck victoryCardDeck;

        //private PermanentCardPools
        private Card[] permanentCardPool;
        private CardDeck hand;
        private CardDeck randomCardPool;
	    private ArrayList<Observer> observers;
        private Age age;

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

        villagerController = new VillagerController();
        victoryCardDeck = new VictoryCardDeck();
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

    public VictoryCardDeck getVictoryCardDeck() {
        return victoryCardDeck;
    }

    public void setVictoryCardDeck(VictoryCardDeck victoryCardDeck) {
        this.victoryCardDeck = victoryCardDeck;
    }
}
