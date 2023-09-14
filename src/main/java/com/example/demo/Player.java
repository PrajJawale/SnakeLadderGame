package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;

    private int currentPosition;
    private String name ;

    private static  Board gameBoard = new Board();
    public Player(int Tilesize , Color coinColor , String userName){
        coin = new Circle(Tilesize/2);
        coin.setFill(coinColor);
        movePlayer(1);
        currentPosition = 1;
        name = userName;
    }

    public void movePlayer(int diceValue){
       if(currentPosition +diceValue<=100){
           currentPosition+=diceValue;
           TranslateTransition secondMove=null, firstMove= translateAnimation(diceValue);


//           int x = gameBoard.getXcoordinte(currentPosition);
//           int y = gameBoard.getYcoordinte(currentPosition);
//
//           coin.setTranslateX(x);
//           coin.setTranslateY(y);


            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(newPosition!=currentPosition && newPosition!=-1){
               currentPosition = newPosition;
               translateAnimation(6);
           }

            if(secondMove==null)    {
                firstMove.play();
            }  else{
                SequentialTransition sq = new SequentialTransition(firstMove,new PauseTransition(Duration.millis(1000)));
                sq.play();
            }

       }
    }
    public void goInitialPosition(){
        currentPosition=0;
        movePlayer(1);
    }
    boolean isWinner(){
        if(currentPosition==100){
            return true;
        }
        return false;
    }


    public TranslateTransition translateAnimation(int diceValue){
        TranslateTransition tr = new TranslateTransition(Duration.millis(200*diceValue),coin);
        tr.setToX(gameBoard.getXcoordinte(currentPosition));
        tr.setToY(gameBoard.getYcoordinte(currentPosition));
        tr.setAutoReverse(false);
        tr.play();
        return tr;
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
