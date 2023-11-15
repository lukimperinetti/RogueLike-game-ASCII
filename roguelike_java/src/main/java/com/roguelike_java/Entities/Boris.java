package com.roguelike_java.Entities;

import java.util.ArrayList;

import com.roguelike_java.App;
import com.roguelike_java.Grid;
import com.roguelike_java.ListEntity;
import com.roguelike_java.UItext;
import com.roguelike_java.UnitManager;

public class Boris extends Unit {
    
    int rangeVisibility;


    public Boris(int X, int Y){
        super("Boris", X, Y, "Arobase.png", 20, "PJ");
        ListEntity.setBoris(this);

        App.displaySprite(this); //S'affiche.
        sprite.setTranslateZ(1);

        this.setAtk(10);

        rangeVisibility = 5;
    }

    @Override
    public Boolean canMove(int X, int Y){ //Condition de déplacement du personnage joueur.

        String tag;

        for (int i = 0; i < Grid.getGrid().get(X).get(Y).size(); i ++){
                tag = Grid.getGrid().get(X).get(Y).get(i).getTag();
                if(tag == "bloc"){ return false; }
                if(tag == "enemy"){ return false; }
        }
        return true;
    }

    public Boolean canAttack(int X, int Y){ //Check si on peut attaquer la cible.

        String tag;

        for (int i = 0; i < Grid.getGrid().get(X).get(Y).size(); i ++){
                tag = Grid.getGrid().get(X).get(Y).get(i).getTag();
                if(tag == "enemy"){ return true; }
        }
        return false;
    }

    //A lancer spécifiquement au déplacement du joueur. Relative move est plus simple
    public void playerMove(int dX, int dY){

        int oldX = coordX;
        int oldY = coordY;

        if(canMove(coordX+dX, coordY+dY)){
            Grid.deleteEntity(this);

            //Met a jour les coordonnées du sprite :
            this.coordX += dX;
            this.coordY += dY;

            Grid.setEntity(this);

            //Deplace le sprite
            sprite.setTranslateX(coordX * Grid.sizeSprite);
            sprite.setTranslateY(coordY * Grid.sizeSprite);

            //Affichage
            Grid.displaySquareEntities(coordX, coordY);
            Grid.displaySquareEntities(oldX, oldY);

        } else if(canAttack(coordX + dX, coordY + dY)){
            
            UItext.printText("Boris attaque un " + Grid.getEnnemy(coordX + dX, coordY + dY).getName() + ".");
            Grid.getEnnemy(coordX + dX, coordY + dY).loseHp(atk);
            
        }

        this.playerVisibility();
        UnitManager.enemyTurn();
        
    }

    //Methode qui gère le champ de vision du joueur.
    public void playerVisibility(){

        //Pour compter les itérations du foreach (c'est nul mais j'ai pas mieux).
        int k = 0;
        int l = 0;

        for (ArrayList<ArrayList<Entity>> i : Grid.getGrid()) {

            for (ArrayList<Entity> j : i) {
                if ( k > coordX-rangeVisibility && k < coordX+rangeVisibility  && l > coordY-rangeVisibility && l < coordY+rangeVisibility){
                    if(!j.get(0).isVisible()){ 

                        j.get(0).toggleVisibility(); 
                        j.get(0).sprite.toBack();
                        App.backgroundUpdate();
                        
                    }
                }
                l++;
            }
            l = 0;
            k++;
        }
    }
}
