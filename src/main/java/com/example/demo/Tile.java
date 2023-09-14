package com.example.demo;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.RED);
        setStroke(Color.BLACK);
    }

    public static void main(String[] args) {

    }

}

