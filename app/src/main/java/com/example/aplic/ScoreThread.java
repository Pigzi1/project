package com.example.aplic;

public class ScoreThread extends Thread{
    private boolean running = false;
    private long lastUpdate;
    private GamePanel gamePanel;

    public void setRunning(boolean running) {
        this.running = running;
        this.lastUpdate = 1000;
    }

    // מטרת התרד היא לספור את הניקוד ולהוסיף 500 בכל שנייה
    public ScoreThread (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void run() {
        lastUpdate = System.currentTimeMillis();
        while (running) {
            if (System.currentTimeMillis() - lastUpdate > 1000) {
                lastUpdate = System.currentTimeMillis();
                gamePanel.scoreUpdater();
            }
        }
    }
}
