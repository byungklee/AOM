/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anycomp.android.ageofmythology.model.card;

import com.anycomp.android.ageofmythology.Observable;
import com.anycomp.android.ageofmythology.Observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author byung
 */
public class CardDeck implements Observable {
      private ArrayList<Card> cards;
      private ArrayList<Observer> observers;
      public CardDeck() {
          cards = new ArrayList<Card>();
          observers = new ArrayList<Observer>();
      }
      
      public void addCard(Card card) {
          cards.add(card);
      notifyObservers();
                 
      }
      
      public Card getCardAt(int index) {
          return cards.get(index);
      }
      
      public void removeCardAt(int index) {
          cards.remove(index);
          notifyObservers();
      }
      
      public boolean contains(Card card) {
          return cards.contains(card);
      }
      
      public void clean() {
          cards.clear();
           notifyObservers();
      }

    @Override
    public void attachObserver(Observer o) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        observers.add(o);
    }

    @Override
    public void detachObserver(Observer o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Iterator it = observers.iterator();
        while(it.hasNext()) {
            ((Observer) it.next()).update(this);
        }
                
    }
    
    public Iterator iterator() {
        return cards.iterator();
    }
    
    public int size() {
        return cards.size();
    }
      
}
