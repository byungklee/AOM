package com.anycomp.android.ageofmythology;

import com.anycomp.android.ageofmythology.model.board.PlayerBoardFactory;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.tile.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class PlayerController {

	//private PlayerBoard playerBoard;
	private ArrayList<Player> players;



    public PlayerController(int numberOfPlayers, String name, String userChosen, HashMap cultureMap) {
		// TODO Auto-generated constructor stub
		//players = new Player[numberOfPlayers];
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
	}

	public ArrayList getPlayers() {
		return players;
	}

	public Player getHumanPlayer() {
		return players.get(0);
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
        
	public Player getNextPlayer() {
		currentPlayer = (currentPlayer+1)%players.size();
		return players.get(currentPlayer);
	}

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

}
