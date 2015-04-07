package com.anycomp.android.ageofmythology.model.area;



public class HoldingArea extends Area {
    private int numberOfVillagers;
	public HoldingArea(int maxSize) {
		super(maxSize);
        numberOfVillagers = 0;
		// TODO Auto-generated constructor stub
	}

    @Override
    boolean contains(Object o) {
        return false;
    }

    public void incrementNumberOfVillagers() {
        numberOfVillagers++;
    }

    public void decrementNumberOfVillagers() {
        numberOfVillagers--;
    }

    public int getNumberOfVillagers() {
        return numberOfVillagers;
    }
}
