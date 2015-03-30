package com.anycomp.android.ageofmythology.model.building;

/**
 * Abstract class for building. 
 * It includes basic functionality of buildings and fields.
 * 
 */
public abstract class Building {
	private String mNameOfBuilding;
	private int image;
        public Building() {
            
        }
        
        public int getImagePath() {
            return image;
        }
        public void setImagePath(int path) {
            this.image = path;
        }
        public String getBuildingName() {
            return mNameOfBuilding;
        }
        public void setBuildingName(String name) {
            this.mNameOfBuilding =name;
        }
                
}	
