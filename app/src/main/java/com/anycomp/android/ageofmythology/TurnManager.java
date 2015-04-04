package com.anycomp.android.ageofmythology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class TurnManager {
    //round and turn
    private PlayerController pc;
    //private ArrayList<Integer> order;
    private Random rand;
    private Callback victoryCallback;

    int round;
    int counter;
    int index;
    int turn;

    public TurnManager(PlayerController pc) {
        this.pc = pc;
        turn = 1;

        round = 1;
        counter = 0;
        rand = new Random();
        index = rand.nextInt(3);
    }

    public int getStartingPlayer() {
        return index;
    }

    public int nextRoundPlayer() {
        //if(round)
        index = (index+1)%3;
        if(counter == 2) {
            if(round > 2) {
                //nextTurn();
                //nextTurn();
                nextTurn();
            } else {
                System.out.println("next round");
                index = index%3;
                round++;
                counter = 0;
            }
        } else {
            counter++;
        }

        return index;
    }

    public void nextTurn() {

        System.out.println("Next Turn");
        turn++;
        index= rand.nextInt(3);
        counter = 0;
        round=1;

        //pc.setCurrentPlayer(order.get(index));
    }

    public int getTurn() {
        return turn;
    }

    public int getRound() {
        return round;
    }

    public void setVictoryCallback(Callback victoryCallback) {
        this.victoryCallback = victoryCallback;
    }
}
