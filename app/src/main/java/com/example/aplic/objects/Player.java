package com.example.aplic.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.aplic.ObjectGame;
import com.example.aplic.ScreenSize;

public class Player extends RectGame implements ObjectGame {



    public Player(Context context, int color, int width, int height, int pointX, int pointY) {
        super(context, color, width, height, pointX, pointY);
        pointGame = new PointGame(ScreenSize.width/2, (ScreenSize.height*4)/5, 0);
        setRect(this.rect);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(this.color);
        canvas.drawRect(this.rect, paint);
    }


    @Override
    public void update () {
        setRect(this.rect);
    }


    public Rect getRectangle() {
        return this.rect;
    }
    public void setRectangle(Rect rectangle) {
        this.rect = rectangle;
    }
}
