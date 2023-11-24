package com.roguelike_java.Entities;

import com.roguelike_java.App;
import com.roguelike_java.Grid;
import com.roguelike_java.ListEntity;
import com.roguelike_java.UI.UItext;
import com.roguelike_java.Utils;

public class Orc extends Enemy {

    private int turn;

    public Orc(int X, int Y) {
        super("Orc", X, Y, "Orc.png", 40, 5);
        App.displaySprite(this);
        this.turn = 0;

        poids = 1;
    }

    public void doAction() {

        // SE DIRIGE VERS LE JOUEUR :
        // Joue a chaque tour.
        if (turn == 1) {
            turn++;
        } else {
            int Xplayer = ListEntity.getBoris().coordX;
            int Yplayer = ListEntity.getBoris().coordY;

            int SignX = 1;
            int SignY = 1;

            if (Xplayer < coordX) {
                SignX = -1;
            }
            if (Yplayer < coordY) {
                SignY = -1;
            }

            // Test de si le joueur est plus proche verticalement ou horizontalement :
            if (Utils.distance1D(coordX, Xplayer) > Utils.distance1D(coordY, Yplayer)) {

                // Test s'il peut attaquer le joueur :
                if (this.canAttack(coordX + SignX, coordY)) {
                    UItext.printText(name + " attaque Boris.");
                    ListEntity.getBoris().loseHp(atk);
                }
                // Test s'il peut avancer dans selon X, sinon il avance selon Y :
                else {
                    if (this.canMove(coordX + SignX, coordY)) {
                        relativeMove(SignX, 0);
                    } else {
                        relativeMove(0, SignY);
                    }
                }

            } else {

                // Test s'il peut attaquer le joueur :
                if (this.canAttack(coordX, coordY + SignY)) {
                    UItext.printText(name + " attaque Boris.");
                    ListEntity.getBoris().loseHp(atk);
                }
                // Test s'il peut avancer selon Y, sinon il avance selon X
                else {
                    if (this.canMove(coordX, coordY + SignY)) {
                        relativeMove(0, SignY);
                    } else {
                        relativeMove(SignX, 0);
                    }
                }
            }
            turn = 0;
        }

    }

    @Override
    public Boolean canMove(int X, int Y) {

        String tag;

        // CONDITION DE DEPLACEMENT
        for (int i = 0; i < Grid.getGrid().get(X).get(Y).size(); i++) {
            tag = Grid.getGrid().get(X).get(Y).get(i).getTag();

            if (tag == "bloc") {
                return false;
            }
            if (tag == "enemy") {
                return false;
            }
            if (tag == "PJ") {
                return false;
            }
        }
        return true;
    }

}
