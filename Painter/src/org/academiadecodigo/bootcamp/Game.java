package org.academiadecodigo.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;

public class Game implements KeyboardHandler {
    private Player player;
    private Grid grid;
    private boolean fill = false;

    public Game(int cols, int rows) {
        this.player = new Player();
        this.grid = new Grid(cols, rows);
    }

    public void init() {
        grid.init();
        player.init();
        keyboardInit();
        grid.makeGrid();
    }

    public void paint() {
        for (Rectangle rectangle : grid.getList()) {
            if (player.getX() == rectangle.getX() & player.getY() == rectangle.getY()) {
                if (fill) {
                    if (rectangle.isFilled()) {
                        rectangle.draw();
                    } else {
                        rectangle.setColor(Color.BLACK);
                        rectangle.fill();
                    }
                }
            }
        }
    }

    public void load() throws IOException {
        // create a new file reader
        FileReader reader = new FileReader("SaveFile.txt");

        // wrap the file reader using a buffered reader
        BufferedReader bReader = new BufferedReader(reader);

        String[] line = bReader.readLine().split("");

        for (int i = 0; i < grid.getList().size(); i++) {
            if (line[i].equals("1")) {
                grid.getList().get(i).fill();
                continue;
            }
            grid.getList().get(i).draw();
        }
        // using the buffered reader we can read lines
        bReader.close();
    }

    public void save() throws IOException {
        // create a new file writer
        FileWriter writer = new FileWriter("SaveFile.txt");

        // wrap the file writer using a buffered writer
        BufferedWriter bWriter = new BufferedWriter(writer);

        //add text to buffer
        for (Rectangle rectangle : grid.getList()) {
            if (rectangle.isFilled()) {
                bWriter.write("1");
            } else {
                bWriter.write("0");
            }
        }

        bWriter.flush(); // if the buffer is not full, flush will force disk write
        bWriter.close(); // auto-flush is done on close
    }

    private void keyboardInit() {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent upPressed = new KeyboardEvent();
        upPressed.setKey(KeyboardEvent.KEY_UP);
        upPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent downPressed = new KeyboardEvent();
        downPressed.setKey(KeyboardEvent.KEY_DOWN);
        downPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent rightPressed = new KeyboardEvent();
        rightPressed.setKey(KeyboardEvent.KEY_RIGHT);
        rightPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent leftPressed = new KeyboardEvent();
        leftPressed.setKey(KeyboardEvent.KEY_LEFT);
        leftPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent spacePressed = new KeyboardEvent();
        spacePressed.setKey(KeyboardEvent.KEY_SPACE);
        spacePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent cPressed = new KeyboardEvent();
        cPressed.setKey(KeyboardEvent.KEY_C);
        cPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent sPressed = new KeyboardEvent();
        sPressed.setKey(KeyboardEvent.KEY_S);
        sPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent lPressed = new KeyboardEvent();
        lPressed.setKey(KeyboardEvent.KEY_L);
        lPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(rightPressed);
        keyboard.addEventListener(downPressed);
        keyboard.addEventListener(upPressed);
        keyboard.addEventListener(leftPressed);
        keyboard.addEventListener(spacePressed);
        keyboard.addEventListener(cPressed);
        keyboard.addEventListener(sPressed);
        keyboard.addEventListener(lPressed);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_DOWN:
                if (player.getY() < grid.getHeigth()) {
                    player.moveDown();
                    paint();

                }
                break;
            case KeyboardEvent.KEY_UP:
                if (player.getY() > Grid.CELLSIZE) {
                    player.moveUp();
                    paint();

                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (player.getX() > 0) {
                    player.moveLeft();
                    paint();
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (player.getX() < grid.getWidth()) {
                    player.moveRight();
                    paint();
                }
                break;

            case KeyboardEvent.KEY_SPACE:
                if (player.getX() < grid.getWidth()) {
                    fill = !fill;
                }
                break;

            case KeyboardEvent.KEY_C:
                for (Rectangle rectangle : grid.getList()) {
                    rectangle.draw();
                }
                break;

            case KeyboardEvent.KEY_S:
                try {
                    save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case KeyboardEvent.KEY_L:
                try {
                    load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
