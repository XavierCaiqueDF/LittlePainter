package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.LinkedList;

public class Grid {
    public static final int PADDING = 10;
    public static final int CELLSIZE = 40;
    private Rectangle grid;
    private int cols;
    private int rows;
    private LinkedList<Rectangle> list;

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.list = new LinkedList<>();
        //this.player = new Player();
    }

    public void init() {
        this.grid = new Rectangle(PADDING, PADDING, cols * CELLSIZE, rows * CELLSIZE);
        this.grid.draw();
    }

    public void makeGrid() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Rectangle rectangle = new Rectangle(CELLSIZE * j + PADDING, CELLSIZE * i + PADDING, CELLSIZE, CELLSIZE);
                rectangle.draw();
                list.add(rectangle);
            }
        }
    }

    public int getWidth() {
        return (cols - 1) * CELLSIZE;
    }

    public int getHeigth() {
        return (rows - 1) * CELLSIZE;
    }

    public void printListObjects() {
        for (Rectangle rectangle : list) {
            System.out.println(rectangle.getX());
        }
    }

    public LinkedList<Rectangle> getList() {
        return list;
    }

}
