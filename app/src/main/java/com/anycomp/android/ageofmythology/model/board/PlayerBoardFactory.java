package com.anycomp.android.ageofmythology.model.board;


import com.anycomp.android.ageofmythology.model.culture.Culture;

public class PlayerBoardFactory {
	public static PlayerBoard newInstance(Culture culture) {
		if(culture.getName().equals("Norse"))
			return new NorseBoard(culture);
		else if(culture.getName().equals("Greek"))
			return new GreekBoard(culture);
		else
			return new EgyptBoard(culture);
			
				
	}
}
