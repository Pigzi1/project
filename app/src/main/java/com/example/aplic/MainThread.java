package com.example.aplic;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;


public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    public double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private volatile boolean running;
    public static Canvas canvas;
    private long totalTime = 0;

    private final Object pauseLock;

    public void setRunning (boolean running) {
        this.running = running;
    }

    public long getTotalTime () {
        return this.totalTime;
    }

    public MainThread (SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        this.pauseLock = new Object();
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public static Canvas getCanvas() {
        return canvas;
    }
    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }


    // כאשר התרד רץ כשהמשתנה הבוליאני running הוא אמת
    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        Long waitTime;
        int frameCount = 0;
        this.totalTime = 0;
        long targetTime = 1000/MAX_FPS;
        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                synchronized (surfaceHolder) {
                    lockCanvas();
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0)
                    sleep(waitTime);
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }

        Log.i(TAG, "run: finish running, out of run loop");
    }

    public void lockCanvas () {
        synchronized (pauseLock) {
            canvas = this.surfaceHolder.lockCanvas();
        }
    }

    public void pauseThread () {
//        lockCanvas();
        this.running = false;
/*        synchronized (pauseLock) {
            this.running = false;
            try {
                pauseLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }
    public void unPauseThread () {
        synchronized (pauseLock) {
            pauseLock.notify();
        }
    }
}