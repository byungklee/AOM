package com.anycomp.android.ageofmythology;

public interface Observable {
	void attachObserver(Observer o);
	void detachObserver(Observer o);
	void notifyObservers();
}
