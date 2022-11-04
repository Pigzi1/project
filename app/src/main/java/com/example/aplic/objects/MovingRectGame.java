package com.example.aplic.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.aplic.ScreenSize;
import com.example.aplic.objects.RectGame;

import java.util.Random;

public class MovingRectGame extends RectGame {

    public MovingRectGame(Context context, int color, int width, int height, int pointX, int pointY) {
        super(context, color, width, height, pointX, pointY);
        int fNum = new Random().nextInt(3);
        this.setObjectFormation(fNum);
        setRect(this.rect);
    }
    public void moveObst() {
        this.pointGame.x += this.pointGame.getVelX();
        this.pointGame.y += this.pointGame.getVelY();
    }

    public void setObjectFormation (int fNum) {
        //fNum:: 0 = diagonal from top, 1 = vertical from top, 2 = horizontal from both sides
        switch (fNum) {
            case 0: this.pointGame.x = setRandomX(0); this.pointGame.y = -this.height; this.pointGame.setRandomPointVelocity(); break;
            case 1: this.color = Color.rgb(150, 0, 255); this.pointGame.x = setRandomX(1); this.pointGame.y = 0; this.pointGame.setVerticalPointVelocity(); break;
            case 2: this.color = Color.rgb(100, 100, 100); this.setHorizontalMovement(); break;
        }
    }

    public int setRandomX (int t) {
        Random rnd = new Random();
        int newX;
        // if 0 can spawn on edge of the screen, 1 can only be inside of the screen borders
        if (t == 0) {
            newX = -width + rnd.nextInt(2*width + ScreenSize.width);
        }
        else {
            newX = rnd.nextInt(ScreenSize.width);
        }
        return newX;

    }
    public int setRandomY () {
        Random rnd = new Random();
        return rnd.nextInt(ScreenSize.height);
    }
    public void setHorizontalMovement() {
        this.pointGame.setSpeed(10);
        this.pointGame.setVelY(0);
        this.pointGame.setVelX(this.pointGame.speed);
        if (new Random().nextInt(2) == 0) {
            this.pointGame.x = -2 * width;
        }
        else {
            this.pointGame.x = ScreenSize.width + 2 * width;
            this.pointGame.velX *= -1;
        }
        this.pointGame.y = this.setRandomY();
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.color);
        setRect(this.rect);
        canvas.drawRect(this.rect, paint);
    }

    @Override
    public void update() {
        this.moveObst();
    }
    
}
