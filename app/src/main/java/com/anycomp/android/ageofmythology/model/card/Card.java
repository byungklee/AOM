package com.anycomp.android.ageofmythology.model.card;

import android.app.FragmentManager;
import android.content.Context;

import com.anycomp.android.ageofmythology.PlayerController;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.player.Player;

public abstract class Card {
	private String mName;
        private int mImagePath;
        private Culture culture;
        private int value;
	public Card() {}

    private boolean isPicked = false;
    private boolean isPlayed = false;

        
    abstract public void play(FragmentManager fm, PlayerController player);
    abstract public void aiPlay(FragmentManager fm, PlayerController player);
        
	public int getImagePath() {
            return mImagePath;
        }
        
    public void setImagePath(int path) {
            this.mImagePath = path;
        }

    /**
     * @return the culture
     */
    public Culture getCulture() {
        return culture;
    }

    /**
     * @param culture the culture to set
     */
    public void setCulture(Culture culture) {
        this.culture = culture;
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    public boolean isPicked() {
        return isPicked;
    }

    public void setPicked(boolean isPicked) {
        this.isPicked = isPicked;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean isPlayed) {
        this.isPlayed = isPlayed;
    }

    public void resetCardStatus() {
        setPlayed(false);
        setPicked(false);
    }
}
