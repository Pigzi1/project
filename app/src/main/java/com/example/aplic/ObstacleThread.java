package com.example.aplic;

public class ObstacleThread extends Thread{
    private boolean running;
    private GamePanel gamePanel;

    public void setRunning(boolean running) {
        this.running = running;
    }
    public ObstacleThread (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {

    }
}
