package com.anycomp.android.ageofmythology.model.tile;

public abstract class Tile {
	
	private TileType tileType;
    private int imagePath;
    private String name;
     
    public Tile() {
    	name = "Tile" + TileFactory.TILE_COUNTER++;
    }

    /**
     * @return the imagePath
     */
    public int getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    protected void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}
	public void print() {
		System.out.println(name + " " + tileType);
	}
    
}
