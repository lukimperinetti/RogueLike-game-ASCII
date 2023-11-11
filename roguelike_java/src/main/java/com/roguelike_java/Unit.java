package com.roguelike_java;

//Class pour les unites (ennemi comme PJ)

public abstract class Unit extends Entity {

    protected int hp;
    protected int atk;

    Unit(String name, int X, int Y, String imageName, int hp, String tag){
        super(name, X, Y, imageName, tag);

        this.hp = hp;
        this.atk = 1; //atk par defaut.
    }
    
    @Override
    public void loseHp(int damage){

        hp = hp - damage;
        if (hp < 0){ hp = 0; }

        System.out.println(name + " perd " + damage + " PV, et tombe a " + hp + " PV.");
    }

    public void attack(Unit unit){
        unit.loseHp(atk);
    }


}
