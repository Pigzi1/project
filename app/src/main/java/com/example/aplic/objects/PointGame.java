package com.example.aplic.objects;

import static android.content.ContentValues.TAG;

import android.graphics.Point;
import android.util.Log;

import com.example.aplic.ScreenSize;

import java.util.Random;


public class PointGame extends Point {
    protected float velX, velY;
    protected int speed;
    protected int type;


    public PointGame(int x, int y, int type) {
        super(x, y);
        this.type = type;
    }

    public float getVelX() {
        return velX;
    }
    public void setVelX(float velX) {
        this.velX = velX;
    }
    public float getVelY() {
        return velY;
    }
    public void setVelY(float velY) {
        this.velY = velY;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int i) {
        this.speed = i + new Random().nextInt(i);
    }

    public void setVelFromType () {
        switch (type) {
            case 0: this.velX = 0; this.velY = 0; this.speed = 20;
                break;
            case 1:
                setRandomPointVelocity();
        }
    }

    public void setRandomPointVelocity() {
        Random rnd = new Random();
        this.setSpeed(5);
        float rndFX = rnd.nextFloat();
        float rndFY = 1 + rnd.nextFloat();
        this.velX = rndFX * this.speed;
        this.velY = rndFY * this.speed;
        Log.i(TAG, "setPointVelocity: speed " + speed);
        Log.i(TAG, "setPointVelocity: X " + velX);
        Log.i(TAG, "setPointVelocity: Y " + velY);
        if (this.x >= ScreenSize.width/2) {
            this.velX *= -1;
            Log.i(TAG, "setPointVelocity: negatived");
        }

    }
    public void setVerticalPointVelocity () {
        this.velX = 0;
        this.setSpeed(10);
        this.velY = this.speed;
    }
}
