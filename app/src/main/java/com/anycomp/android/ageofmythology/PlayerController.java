package com.anycomp.android.ageofmythology;

import android.app.FragmentManager;

import com.anycomp.android.ageofmythology.model.area.CityArea;
import com.anycomp.android.ageofmythology.model.bank.Bank;
import com.anycomp.android.ageofmythology.model.board.PlayerBoardFactory;
import com.anycomp.android.ageofmythology.model.building.BuildingType;
import com.anycomp.android.ageofmythology.model.card.VictoryCard;
import com.anycomp.android.ageofmythology.model.card.VictoryCardDeck;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.tile.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class PlayerController {

	//private PlayerBoard playerBoard;
	private ArrayList<Player> players;
    private TurnManager turnManager;
    private FragmentManager fm;
    private VictoryCardDeck victoryCardDeck;
    private WinnerInterface winnerInterface;
    private int fourth; // Signals a player who gets a fourth action


    public PlayerController(int numberOfPlayers, String name, String userChosen, HashMap cultureMap, FragmentManager fm, WinnerInterface wi) {
		// TODO Auto-generated constructor stub
		//players = new Player[numberOfPlayers];
        this.winnerInterface = wi;
		//Making a Human Player
		if(cultureMap == null) {
			System.out.println("cultureMap is null");
		}
		players = new ArrayList<Player>();
		players.add(new Player(name,
				(Culture) cultureMap.get(userChosen),
				PlayerBoardFactory.newInstance((Culture) cultureMap.get(userChosen))));


		//Making AI Players and they pick up the rest cultures.
		Iterator it = cultureMap.entrySet().iterator();
		int i=1;
		while(it.hasNext()) {
			Entry e = (Entry)it.next();
			Culture tempCulture = (Culture) e.getValue();
			if(tempCulture != players.get(0).getCulture()) {
				players.add(new Player("AI"+i, tempCulture, PlayerBoardFactory.newInstance(tempCulture)));
				i++;
			}	
		}
        turnManager = new TurnManager(this);


        cardOrder.add(0);
        cardOrder.add(1);
        cardOrder.add(2);
        cardOrder.add(3);
        cardOrder.add(4);
        cardOrder.add(5);
        cardOrder.add(6);
        cardOrder.add(7);

        this.fm = fm;
        victoryCardDeck = new VictoryCardDeck();
        fourth = -1;
	}

    public VictoryCardDeck getVictoryCardDeck() {
        return victoryCardDeck;
    }

    public void setVictoryCardDeck(VictoryCardDeck victoryCardDeck) {
        this.victoryCardDeck = victoryCardDeck;
    }

	public ArrayList getPlayers() {
		return players;
	}

	public Player getHumanPlayer() {
		return players.get(0);
	}

    public void setStartingPlayer() {

        setCurrentPlayer(turnManager.getCurrentPlayer());
        resetCardDeck();

//        if(players.get(currentPlayer).getName().contains("AI")) {
//            //AI work
//            aiWork();
//        }
    }

    public void resetCardDeck(){
        Iterator it = players.iterator();
        while(it.hasNext()) {
            Player p = (Player) it.next();
            p.resetHand();

        }
    }

    ArrayList<Integer> cardOrder = new ArrayList<Integer>();


    public void aiWork() {
            if(turnManager.getRound() == 1) {
                //if first round,

                //pick card
                //PickCardController pcc = new PickCardController();

                Collections.shuffle(cardOrder);
                for(int i=0;i<getCurrentPlayer().getAge().getMaxCardAvailable();i++) {
                    getCurrentPlayer().pickCard(cardOrder.get(i));
                }
            }
            System.out.println(getCurrentPlayer().getName() + " plays " + getCurrentPlayer().getHand().getCardAt(turnManager.getRound() -1).getName() );
            getCurrentPlayer().getHand().getCardAt(turnManager.getRound() -1).aiPlay(fm, this);
        //play card
        //and next round;

    }

    public void nextRound() {
        //currentPlayer = (currentPlayer+1)%players.size();


        setCurrentPlayer(turnManager.nextRoundPlayer());
        if(turnManager.getTurn() == 11) {
            gameEnd();
        } else {
            System.out.println("next " + turnManager.getRound() + " " + turnManager.getCounter());
            if (turnManager.getRound() == 1 && turnManager.getCounter() == 0) {

            } else {
                if (players.get(currentPlayer).getName().contains("AI")) {
                    //AI work
                    aiWork();
                }
            }
        }
        //return players.get(currentPlayer);
    }

	//public Player 

	/**
	 * Current player passes his or her turn.
	 * @param currentPlayer
	 */
	public void pass(Player currentPlayer) {

	}

	/**
	 * Current player picks tile and save it on production area.
	 * @param currentPlayer
	 * @param tile
	 */
	public void pickTile(Player currentPlayer, Tile tile) {

	}

	/**
	 * Current player picks TerrainTile.
	 * @param currentPlayer
	 * @param tile
	 */
//	public void pickTerrainTile(Player currentPlayer, Geometry geo) {
//		currentPlayer.getPlayerBoard().getProductionArea().addTile(TileManager.getInstance().takeTile(geo.getName()));;
//	}
	

	/**
	 * Current player places an object on tile
	 * @param currentPlayer
	 * @param o
	 */
	public void placeOnTile(Player currentPlayer, Object o, Tile tile) {

	}
	
	public boolean isAllResourceTilePlaced() {
		Iterator it = players.iterator();
		while(it.hasNext()) {
			Player p = (Player) it.next();
			if(p.getPlayerBoard().getProductionArea().getTileSize() < 16) {
				return false;
			}
		}
		return true;
	}
	
	private int currentPlayer = 0;

    public void setCurrentPlayer(int i) {
            currentPlayer = i;
        }
        
    public int getCurrentPlayerID() { return currentPlayer; }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
	
	//private int cycleCounter = 0;
	private boolean isForward = true;
        public void setIsForward(boolean s) {
            isForward = s;
        }

	public Player getNextPlayerForTileSelection() {
		if(isForward) {
			currentPlayer += 1;
			if(currentPlayer == players.size()) {
				isForward = false;
                currentPlayer--;

				return players.get(currentPlayer);
			}
		} else {
			currentPlayer -= 1;
			if(currentPlayer == -1) {
				isForward = true;
                currentPlayer++;
				//currentPlayer -= 1;
				return players.get(currentPlayer);
			}
		}
	
		return players.get(currentPlayer);
	}

    public Player getNextPlayerForTileSelectionLinear() {
        currentPlayer = (currentPlayer+1)%3;
        return players.get(currentPlayer);
    }

    public Player getPlayerByCulture(String culture) {
        for(int i=0;i<players.size();i++) {
            Player p = players.get(i);
            if(p.getCulture().getName().equals(culture)) {
                return p;
            }
        }
        return null;
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }

    public void spoilage() {
        System.out.println("Spoilage");
        Iterator it = players.iterator();
        while(it.hasNext()) {
            Player p = (Player) it.next();
            int maxAllowed = 5;
            if(p.hasBuilding(BuildingType.STOREHOUSE)) {
                maxAllowed = 8;
            }
            System.out.println(p.getName() + " has storehouse.");
            int i;
            if(p.getFoodCube().getValue() > maxAllowed) {
                i = p.getFoodCube().getValue() - maxAllowed;
                System.out.println("spoiling food " + i);
                p.spendFood(i);
            }
            if(p.getWoodCube().getValue() > maxAllowed) {
                i = p.getWoodCube().getValue() - maxAllowed;
                System.out.println("spoiling wood " + i);
                p.spendWood(i);
            }
            if(p.getGoldCube().getValue() > maxAllowed) {
                i = p.getGoldCube().getValue() - maxAllowed;
                System.out.println("spoiling gold " + i);
                p.spendGold(i);
            }
            if(p.getFavorCube().getValue() > maxAllowed) {
                i = p.getFavorCube().getValue() - maxAllowed;
                System.out.println("spoiling favor " + i);
                p.spendFavor(i);
            }
        }
    }

    public boolean isEndCondtionMet() {
        if(Bank.getInstance().getVictoryCubeCount() == 0) {
            return true;
        }
        return false;
    }

    public void gameEnd() {
        System.out.println("Player Controller: GameEnd has called");
        VictoryCardDeck vcd = getVictoryCardDeck();
///army buidling battle wonder
        Iterator it = vcd.getVictoryCards().iterator();

        Player p1 = players.get(0);
        Player p2 = players.get(1);
        Player p3 = players.get(2);
        VictoryCard army = vcd.getVictoryCards().get(0);
        VictoryCard building = vcd.getVictoryCards().get(1);
        //VictoryCard battle = vcd.getVictoryCards().get(2);
        //VictoryCard wonder = vcd.getVictoryCards().get(3);
        List<Player> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        //Sort based on army size
        Collections.sort(list, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                if(lhs.getArmy().size() >= rhs.getArmy().size()) {
                    return -1;
                }
                return 1;
            }
        });

        //if only one has the highest army size, take all cubes from biggest army size.
        if(list.get(0).getArmy().size() != list.get(1).getArmy().size()) {
            list.get(0).takeVictoryFromCard(army.getCubeSize());
            army.getVictoryCubes().clear();
        }

        Collections.sort(list, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                if(((CityArea)lhs.getPlayerBoard().getCityArea()).getNumberOfBuilding() >=
                        ((CityArea)rhs.getPlayerBoard().getCityArea()).getNumberOfBuilding())
                return -1;
                return 1;
            }
        });

        if(((CityArea)list.get(0).getPlayerBoard().getCityArea()).getNumberOfBuilding() !=
                ((CityArea)list.get(1).getPlayerBoard().getCityArea()).getNumberOfBuilding()) {
            list.get(0).takeVictoryFromCard(building.getCubeSize());
            building.getVictoryCubes().clear();
        }

        Collections.sort(list, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                if(lhs.getVictoryCube().getValue() < rhs.getVictoryCube().getValue()) {
                    return 1;
                }
                return -1;
            }
        });

        if(list.get(0).getVictoryCube().getValue() > list.get(1).getVictoryCube().getValue()) {
            winnerInterface.winner(list.get(0));
        } else {
            winnerInterface.winner(null);
        }




//        VictoryCard vc = (VictoryCard) it.next();
  ///      int numberOfVictoryCard = vc.getVictoryCubes().size();



    }

    public void setFourth(int fourth) { this.fourth = fourth; }

    public int getFourth() { return fourth; }
}
