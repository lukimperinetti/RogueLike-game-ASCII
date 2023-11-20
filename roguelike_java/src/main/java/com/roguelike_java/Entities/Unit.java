package com.roguelike_java.Entities;

import com.roguelike_java.UI.UItext;

//Class pour les unites (ennemi comme PJ)

public abstract class Unit extends Entity {

    protected int hp;
    protected int atk;

    // GETTERS :
    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    // SETTERS :
    public void setAtk(int atk) {
        this.atk = atk;
    }

    Unit(String name, int X, int Y, String imageName, int hp, String tag) {
        super(name, X, Y, imageName, tag);

        this.hp = hp;
        this.atk = 1; // atk par defaut.
        visibility = true;
    }

    @Override
    public void loseHp(int damage) {

        hp = hp - damage;
        if (hp < 0) {
            hp = 0;
        }

        UItext.printText(name + " perd " + damage + " PV.");
        UItext.printText(" ");
    }

    public void attack(Unit unit) {
        unit.loseHp(atk);
    }

}
