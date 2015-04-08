package com.anycomp.android.ageofmythology;

import android.content.Context;
import android.widget.Toast;

import com.anycomp.android.ageofmythology.model.age.Age;
import com.anycomp.android.ageofmythology.model.age.ArchaicAge;
import com.anycomp.android.ageofmythology.model.age.ClassicalAge;
import com.anycomp.android.ageofmythology.model.age.HeroicAge;
import com.anycomp.android.ageofmythology.model.age.MythicAge;
import com.anycomp.android.ageofmythology.model.bank.Bank;
import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.resource.Cube;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;
import com.anycomp.android.ageofmythology.model.resource.WoodCube;

/**
 * Created by bert on 4/8/15.
 */
public class NextAgeController {
    private PlayerController pc;
    private static NextAgeController instance;
    private Card card;
    private Age age;

    public static NextAgeController getInstance(PlayerController pc) {
        if(instance == null) {
            instance = new NextAgeController(pc);
        }
        return instance;
    }

    private NextAgeController(PlayerController pc) {
        this.pc = pc;
        age = pc.getCurrentPlayer().getAge();
    }

    public void playNextAgeCard(Card card) { this.card = card; }

    public Age getAge() { return age; }

    public void nextRound() {
        pc.nextRound();
    }

    public boolean check() {
        Player player = pc.getCurrentPlayer();
        Age age = player.getAge();
        Bank bank = Bank.getInstance();
        Cube woodCube = player.getWoodCube();
        Cube foodCube = player.getFoodCube();
        Cube favorCube = player.getFavorCube();
        Cube goldCube = player.getGoldCube();
        int wood = woodCube.getValue();
        int food = foodCube.getValue();
        int favor = favorCube.getValue();
        int gold = goldCube.getValue();

        if (age instanceof ArchaicAge && wood >= 4 && food >= 4 && favor >= 4
                && gold >= 4) {
            this.age = new ClassicalAge();
            player.setAge(this.age);
            bank.deposit(ResourceType.WOOD, 4);
            bank.deposit(ResourceType.FOOD, 4);
            bank.deposit(ResourceType.FAVOR, 4);
            bank.deposit(ResourceType.GOLD, 4);
            player.spendWood(4);
            player.spendFood(4);
            player.spendFavor(4);
            player.spendGold(4);
        } else if (age instanceof ClassicalAge && wood >= 5 && food >= 5 && favor >= 5
                && gold >= 5) {
            this.age = new HeroicAge();
            player.setAge(this.age);
            bank.deposit(ResourceType.WOOD, 5);
            bank.deposit(ResourceType.FOOD, 5);
            bank.deposit(ResourceType.FAVOR, 5);
            bank.deposit(ResourceType.GOLD, 5);
            player.spendWood(5);
            player.spendFood(5);
            player.spendFavor(5);
            player.spendGold(5);
        } else if (age instanceof HeroicAge && wood >= 6 && food >= 6 && favor >= 6
                && gold >= 6) {
            this.age = new MythicAge();
            player.setAge(this.age);
            bank.deposit(ResourceType.WOOD, 6);
            bank.deposit(ResourceType.FAVOR, 6);
            bank.deposit(ResourceType.FOOD, 6);
            bank.deposit(ResourceType.GOLD, 6);
            player.spendWood(6);
            player.spendFood(6);
            player.spendFavor(6);
            player.spendGold(6);
        } else {
            return false;
        }

        return true;
    }
}
