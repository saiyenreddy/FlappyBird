package com.example.learning;

import java.awt.*;
import java.io.IOException;

public class Game implements Runnable {
        private GameWindow gameWindow;
        private GamePanel gamePanel;
        private Thread gameThread;
        private final int setfps = 60;
    public Game() throws IOException {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocusInWindow();
        startGameLoop();

    }
    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run() {
        double timePerFrame = 1000000000 / setfps;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        while(true) {
            now = System.nanoTime();
            if(now- lastFrame >= timePerFrame) {
                gamePanel.repaint();
            }
        }
    }
}
