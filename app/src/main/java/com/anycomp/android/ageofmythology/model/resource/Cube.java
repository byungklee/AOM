package com.anycomp.android.ageofmythology.model.resource;

public class Cube {
	private int color;
	private int value;
    private int imagePath;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	int getColor() {
		return color;
	}
	void setColor(int color) {
		this.color = color;
	}

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }
}
