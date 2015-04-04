package com.anycomp.android.ageofmythology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TurnManager {
    //round and turn
    PlayerController pc;
    ArrayList<Integer> order;
    int round;
    int index;
    int turn;

    public TurnManager(PlayerController pc) {
        order = new ArrayList<Integer>();
        for(int i =0;i<pc.getPlayers().size();i++) {
            order.add(i);
        }
        turn = 1;
        index = 0;
        round = 1;
        Collections.shuffle(order);
    }

    public int getStartingPlayer() {
        return order.get(index);
    }

    public void nextRound() {
        //if(round)
        index++;
        if(index == order.size()) {
            if(round == 3) {
                //nextTurn();
                //nextTurn();
            } else {
                index = 0;
            }
        } else {
            pc.setCurrentPlayer(order.get(index));
        }
    }

    public void nextTurn() {
        Collections.shuffle(order);
        turn++;
        index=0;
        round=1;
        pc.setCurrentPlayer(order.get(index));
    }

    public int getTurn() {
        return turn;
    }

    public int getRound() {
        return round;
    }
}
