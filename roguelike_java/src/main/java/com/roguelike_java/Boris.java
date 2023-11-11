package com.roguelike_java;

public class Boris extends Unit {
    
    Boris(int X, int Y){
        super("Boris", X, Y, "Arobase.png", 20, "PJ");
        ListEntity.setBoris(this);
        App.displaySprite(this); //S'affiche.
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

    @Override
    public void relativeMove(int dX, int dY){
        super.relativeMove(dX, dY);

        if(canAttack(coordX + dX, coordY + dY)){
            Grid.getEnnemy(coordX + dX, coordY + dY).loseHp(atk);
        }

        UnitManager.enemyTurn(); //Lance le tour des ennemis
    }
}
