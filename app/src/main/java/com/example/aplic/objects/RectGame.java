package com.example.aplic.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.aplic.ObjectGame;

public abstract class RectGame implements ObjectGame {

    protected int color;
    protected PointGame pointGame;
    protected int width, height;
    Context context;
    protected Rect rect;

    public RectGame(Context context, int color, int width, int height, int pointX, int pointY) {
        this.context = context;
        this.color = color;
        this.width = width;
        this.height = height;
        this.pointGame = new PointGame(pointX, pointY, 1);
        this.rect = new Rect();
    }

    public void setRect(Rect rectangle) {
        int l = this.pointGame.x-this.width/2;
        int t = this.pointGame.y-this.height/2;
        int r = this.pointGame.x+this.width/2;
        int b = this.pointGame.y+this.height/2;
        rectangle.set(l, t, r, b);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public PointGame getPointGame() {
        return pointGame;
    }

    public boolean collision (Player p) {
        return Rect.intersects(this.rect, p.getRectangle());
    }

    @Override
    public abstract void draw(Canvas canvas);

    @Override
    public abstract void update();

//    @Override
//    public void update() {
//        ObstRect temp = this;
//        if (temp instanceof MovingRectObst) {
//            MovingRectObst tempMove = (MovingRectObst)temp;
//            tempMove.moveObst();
//        }
//        rect.set(this.objRectPoint.x - rect.width()/2, objRectPoint.y - rect.height()/2, objRectPoint.x + rect.width()/2, objRectPoint.y + rect.height()/2);
//    }
}

