package com.roguelike_java.Entities;

import java.util.ArrayList;
import java.util.List;

import com.roguelike_java.Entities.Items.*;
import com.roguelike_java.UI.UILife;
import com.roguelike_java.UI.UIequipment;
import com.roguelike_java.UI.UItext;
import com.roguelike_java.*;

public class Boris extends Unit {
    
    int rangeVisibility = 7;
    int rangeVisibilitySquare;
    int defaultAtk = 10;
    Weapon weapon;

    public Boris(int X, int Y){
        super("Boris", X, Y, "Arobase.png", 20, "PJ");
        ListEntity.setBoris(this);
        App.displaySprite(this); //S'affiche.

        this.setAtk(defaultAtk);
        this.weapon = null;

        rangeVisibilitySquare = rangeVisibility*rangeVisibility;

        UILife.initLife();
        UILife.displayLife(hp, maxHp);
    }

    //GETTERS :
    public Weapon getWeapon(){
        return weapon;
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

            onPlayerMove();

        } else if(canAttack(coordX + dX, coordY + dY)){
            
            UItext.printText("Boris attaque un " + Grid.getEnnemy(coordX + dX, coordY + dY).getName() + ".");
            Grid.getEnnemy(coordX + dX, coordY + dY).loseHp(atk);
            
        }

        this.playerVisibility();
        UnitManager.enemyTurn();
    }

    @Override
    public void setVisibility(boolean b){
        
    }

    //Methode qui gère le champ de vision du joueur
    public void playerVisibility(){

        //Pour compter les itérations du foreach (c'est nul mais j'ai pas mieux).
        int k = 0; //X
        int l = 0; //Y

        //DECORS :
        for (ArrayList<ArrayList<Entity>> i : Grid.getGrid()) {

            for (ArrayList<Entity> j : i) {
                if ( Utils.distance2Dsquare(coordX, coordY, k, l) <= rangeVisibilitySquare){
                    if(!j.get(0).isVisible()){ 

                        j.get(0).toggleVisibility(); 
                        j.get(0).sprite.toBack();
                        App.backgroundUpdate();                     
                    }
                    j.get(0).sprite.setOpacity(l);
                }
                else if (isVisible()){
                    j.get(0).sprite.setOpacity(0.5);
                }
                l++;
            }
            l = 0;
            k++;    
        }


        //UNITES :
        for (Unit unit : ListEntity.getListEnemies()) {
            if (  Utils.distance2Dsquare(coordX, coordY, unit.getCoordX(), unit.getCoordY()) <= rangeVisibilitySquare ){
                unit.setVisibility(true);
            }
            else{ unit.setVisibility(false);}            
        }

        //ITEMS :
        for (Item item : ListEntity.getListItems()) {
            if ( !item.isInInventory()){

                if (Utils.distance2Dsquare(coordX, coordY, item.getCoordX(), item.getCoordY()) <= rangeVisibilitySquare ){
                    item.setVisibility(true);
                }
                else{ item.setVisibility(false);}    
            }
        }
    }

    //Test toutes les entitées sur la case du joueur, et écrit les descriptions si besoin dans le récapitulatif.
    public void onPlayerMove(){

        ArrayList<Entity> listEntity = Grid.getEntities(coordX, coordY);
        for (Entity entity : listEntity) {
            if (entity instanceof Item){ 
                ((Item)entity).description();
            }
        }
    }

    //Regarde ce qui se trouve sur la meme case que Boris, et intéragit avec.
    public void interaction(){
        
        boolean bool = false;
        for (Entity entity : Grid.getEntities(coordX, coordY)) {
            if (entity.getName() == "Stairs") { bool= true; }
        }
        
        if ( bool ) { Grid.newLevel(); }
    }

    //EQUIPE DEPUIS LE SOL
    public void equipWeapon(Weapon weapon){
        this.weapon = weapon;
        this.atk = weapon.getAtk();

        UItext.printText("Boris equipe : " + weapon.getName() );
        UItext.printText(" ");
        UIequipment.updateEquipment();

        UnitManager.enemyTurn();
    }


    public void takeItem(){
        Item item = Grid.getItem(coordX, coordY);

        if (Inventory.isInventoryFull()){
            UItext.printText("L'inventaire de Boris est plein.");
        }
        else if (item != null){
            item.takeItem();
            UItext.printText("Boris ramasse : " + item.getName());
            item.setIsInInventory(true);
        }
        else {
            UItext.printText("Il n'y a rien a ramasser.");
        }
        UItext.printText(" ");

        UnitManager.enemyTurn();
    }

    //Condition de défaite
    @Override
    public void loseHp(int damage){
        super.loseHp(damage);

        UItext.printText("Il vous reste : " + hp + "PV.");
        UItext.printText(" ");

        UILife.displayLife(hp, maxHp);

        if (hp == 0){
            GamestateManager.defeat();;
        }
    }

    @Override
    public void heal(int healValue){
        super.heal(healValue);

        UILife.displayLife(hp, maxHp);
    }

    //DEBUG :
    public void printEntity(){
        System.out.println("--");
        for (Entity entity : Grid.getEntities(coordX, coordY)) {
            System.out.println(entity.getName());

            if (entity.getName()=="Stairs"){ 
                System.out.println(entity.isVisible());
            }
        }
    }

}
