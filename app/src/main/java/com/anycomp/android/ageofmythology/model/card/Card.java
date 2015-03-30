package com.anycomp.android.ageofmythology.model.card;

import com.anycomp.android.ageofmythology.model.culture.Culture;

public abstract class Card {
	private String mName;
        private int mImagePath;
        private Culture culture;
        private int value;
	public Card() {}
        
        abstract public void play();
        
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
	
}
