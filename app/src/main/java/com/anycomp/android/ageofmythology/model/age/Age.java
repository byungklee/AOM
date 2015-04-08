/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anycomp.android.ageofmythology.model.age;

/**
 *
 * @author byung
 */
abstract public class Age {
    protected String name;
    protected int maxCardAvailable;
    protected int order;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the maxCardAvailable
     */
    public int getMaxCardAvailable() {
        return maxCardAvailable;
    }

    /**
     * @param maxCardAvailable the maxCardAvailable to set
     */
    public void setMaxCardAvailable(int maxCardAvailable) {
        this.maxCardAvailable = maxCardAvailable;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    public int getOrder() {
        return this.order;
    }
    
}
