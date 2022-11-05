package com.example.aplic;

public class ScoreThread extends Thread{
    private boolean running = false;
    private GamePanel gamePanel;

    public void setRunning(boolean running) {
        this.running = running;
    }

    // מטרת התרד היא לספור את הניקוד ולהוסיף 500 בכל שנייה
    public ScoreThread (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                gamePanel.scoreUpdater();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
