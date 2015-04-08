package com.anycomp.android.ageofmythology.model.bank;

import com.anycomp.android.ageofmythology.model.resource.ResourceType;

/**
 * Created by bert on 4/4/15.
 */
public class Bank {
    private int wood;
    private int favor;
    private int food;
    private int gold;
    private int victory;
    private static Bank instance;

    /* Singleton */
    public static Bank getInstance() {
        if (instance == null)
            instance = new Bank();

        return instance;
    }

    /* Each resource has a starting number */
    private Bank() {
        wood = 25;
        favor = 25;
        food = 25;
        gold = 25;
        victory = 30;
    }

    /* Withdraw resources */
    public int withdraw(ResourceType type, int amount) {
        switch (type) {
            case WOOD:
                if (wood > amount) {
                    wood -= amount;
                } else {
                    amount = wood;
                    wood = 0;
                }
                break;
            case FAVOR:
                if (favor > amount) {
                    favor -= amount;
                } else {
                    amount = favor;
                    favor = 0;
                }
                break;
            case FOOD:
                if (food > amount) {
                    food -= amount;
                } else {
                    amount = food;
                    food = 0;
                }
                break;
            case GOLD:
                if (gold > amount) {
                    gold -= amount;
                } else {
                    amount = gold;
                    gold = 0;
                }
                break;
            default:
                return 0;
        }

        return amount;
    }

    /* Return resources to the bank */
    public void deposit(ResourceType type, int amount) {
        switch (type) {
            case WOOD:
                wood += amount;
                break;
            case FAVOR:
                favor += amount;
                break;
            case FOOD:
                food += amount;
                break;
            case GOLD:
                gold += amount;
                break;
            default:
                return;
        }
    }

    /* Withdraw victory cubes */
    public int withdrawVictory(int amount) {
        if (victory > amount) {
            victory -= amount;
        } else {
            amount = victory;
            victory = 0;
        }

        return amount;
    }

    /* Deposit victory cubes */
    public void depositVictory(int amount) { victory += amount; }



    public int getFavor() { return favor; }
    public int getFood() { return food; }
    public int getGold() { return gold; }
    public int getWood() { return wood; }

    public int getVictoryCubeCount() {
        return victory;
    }


}
