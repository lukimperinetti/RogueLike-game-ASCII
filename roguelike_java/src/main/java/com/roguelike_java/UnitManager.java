package com.roguelike_java;
import java.util.ArrayList;


//CLASSE QUI S'OCCUPE DE FAIRE JOUER LES DIFFERENTES UNITES.
public class UnitManager {
    

    public static void enemyTurn(){

        ArrayList<Enemy> listEnemies = ListEntity.getListEnemies();

        for (int i = 0; i < listEnemies.size(); i++){
            listEnemies.get(i).doAction(); // pour chaque ennemi, on lui fait faire son tour.
        }

        //Rajouter ici qqchose si besoin pour relancer le tour du joueur.

    }

}


