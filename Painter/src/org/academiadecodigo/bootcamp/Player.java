package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class Player {
    Rectangle player;

    public Player() {
        this.player = new Rectangle(Grid.PADDING, Grid.PADDING, Grid.CELLSIZE, Grid.CELLSIZE);
    }

    public void init() {
        this.player.draw();
        this.player.setColor(Color.BLUE);
        this.player.fill();
        //keyboardInit();
    }

    public void moveUp() {
        this.player.translate(0, -Grid.CELLSIZE);
    }

    public void moveDown() {
        this.player.translate(0, Grid.CELLSIZE);
    }

    public void moveLeft() {
        this.player.translate(-Grid.CELLSIZE, 0);
    }

    public void moveRight() {
        this.player.translate(Grid.CELLSIZE, 0);
    }

    public int getX() {
        return player.getX();
    }

    public int getY() {
        return player.getY();
    }
}