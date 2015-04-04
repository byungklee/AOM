package com.anycomp.android.ageofmythology.model.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by byung on 4/4/15.
 */
public class VictoryCardDeck {
    ArrayList<VictoryCard> victoryCards;
    HashMap<String,VictoryCard> victoryCardMap;
    public VictoryCardDeck() {
        victoryCards = new ArrayList<>();
        victoryCardMap = new HashMap<>();
        addCard(new ArmyVictoryCard());
        addCard(new BuildingVictoryCard());
        addCard(new BattleVictoryCard());
        addCard(new WonderVictoryCard());
    }
    private void addCard(VictoryCard card) {
        victoryCards.add(card);
        victoryCardMap.put(card.getName(),card);
    }

    public ArrayList<VictoryCard> getVictoryCards() {
        return victoryCards;
    }

    public void addCube(String cardName) {
        victoryCardMap.get(cardName).addVictoryCube();
    }

    public void removeCube(String cardName) {
        victoryCardMap.get(cardName).removeVictoryCube();
    }

    public int getVictoryCardCount() {
        return victoryCards.size();
    }



    public int getTotalCubeCount() {
        Iterator it = victoryCards.iterator();
        int total = 0;
        while(it.hasNext()) {
            VictoryCard vc = (VictoryCard)it.next();
            total += vc.getCubeSize();
        }
        return total;
    }

}
