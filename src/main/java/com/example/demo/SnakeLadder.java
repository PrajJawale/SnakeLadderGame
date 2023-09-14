package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventListener;

public class SnakeLadder extends Application {
    public static final int tileSize =40,width=10,height=10;
    public static final  int ButtonLine = tileSize*height+50, LabelLine = tileSize*height+25;
    private Dice dice = new Dice();
    Player playerFirst,playerSecond;

    boolean gameStarted= false , playerFirstTurn=false, playerSecondTurn=false;

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+100);
        for(int y =0;y<height;y++){
            for(int x=0;x<width;x++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(x*tileSize);
                tile.setTranslateY(y*tileSize);
                root.getChildren().addAll(tile);
            }
        }

        Image img = new Image("C:\\Users\\Lenovo\\Desktop\\Snake&LadderGame\\src\\main\\java\\Snake&LadderImage.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(tileSize*height);
        board.setFitWidth(tileSize*width);


        //Button
        Button player1 = new Button("Player1");
        Button Start  = new Button("Start");
        Button player2 = new Button("Player2");

//        player1.setDisable(true);
//        player2.setDisable(true);

        player1.setTranslateX(40);
        player1.setTranslateY(ButtonLine);
        Start.setTranslateY(ButtonLine);
        Start.setTranslateX(190);
        player2.setTranslateY(ButtonLine);
        player2.setTranslateX(310);

       //Label
        Label playeOne = new Label("Your Turn P1");
        Label DiceLabel = new Label("Start The Game");
        Label playerTwo = new Label("Your Turn P2");

        playeOne.setTranslateY(LabelLine);
        playeOne.setTranslateX(27);
        DiceLabel.setTranslateY(LabelLine);
        DiceLabel.setTranslateX(180);
        playerTwo.setTranslateY(LabelLine);
        playerTwo.setTranslateX(290);

        playerFirst = new Player(tileSize,Color.BLACK,"Prajwal");
        playerSecond = new Player(tileSize-5,Color.WHITE,"Ishwari");


        player1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
             if(gameStarted && playerFirstTurn){
                 int diceValue = (int)dice.getRollDiceValue();
                 DiceLabel.setText(" Dice Value is "+ diceValue);
                 playerFirst.movePlayer(diceValue);

                 if(playerFirst.isWinner()){
                     DiceLabel.setText("Winner is " + playerFirst.getName());
                     player1.setDisable(true);
                     player2.setDisable(true);
                     playeOne.setText("");
                     playerTwo.setText("");

                     Start.setDisable(false);
                     Start.setText("Restart");
                     gameStarted = false;
                 }
                 else
                 {
                     player1.setDisable(true);
                     player2.setDisable(false);
                     playeOne.setText("");
                     playerFirstTurn = false;
                     playerSecondTurn = true;
                     playerTwo.setText("Your Turn : " + playerSecond.getName());
                 }
             }

            }
        });

        player2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted && playerSecondTurn){
                    int diceValue = (int)dice.getRollDiceValue();
                    DiceLabel.setText(" Dice Value is "+ diceValue);
                    playerSecond.movePlayer(diceValue);

                   if(playerSecond.isWinner()){
                       DiceLabel.setText("Winner is " + playerSecond.getName());
                       player1.setDisable(true);
                       player2.setDisable(true);
                       playeOne.setText("");
                       playerTwo.setText("");

                       Start.setDisable(false);
                       Start.setText("Restart");
                       gameStarted = false;
                   }else{
                       player1.setDisable(false);
                       player2.setDisable(true);
                       playerSecondTurn=false;
                       playerTwo.setText("");
                       playerFirstTurn=true;
                       playeOne.setText("Your Turn : " + playerFirst.getName());
                   }


                }
            }
        });

        Start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                playerFirstTurn = true;
                playerFirst.goInitialPosition();
                playerSecond.goInitialPosition();
                playeOne.setText("Your Turn "+playerFirst.getName());
                Start.setDisable(true);
                player1.setDisable(false);
                DiceLabel.setText("Game Started");

            }
        });


        root.getChildren().addAll(board,player1,player2,Start,playeOne,playerTwo,DiceLabel,playerFirst.getCoin(),playerSecond.getCoin());
       // root.setPrefSize(width,height);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(SnakeLadder.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
//
    launch();

    }
}