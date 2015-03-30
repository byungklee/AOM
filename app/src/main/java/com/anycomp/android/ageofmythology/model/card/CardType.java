/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anycomp.android.ageofmythology.model.card;

/**
 *
 * @author byung
 */
public enum CardType {
    GATHER_CARD(0),
    EXPLORE_CARD(1),
    ATTACK_CARD(2),
    BUILD_CARD(3),
    TRADE_CARD(4),
    RECRUIT_CARD(5),
    NEXT_AGE_CARD(6);
    
    public int value;
    private CardType(int i) {
        value = i;
    }
    
}
