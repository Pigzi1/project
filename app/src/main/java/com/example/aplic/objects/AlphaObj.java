package com.example.aplic.objects;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.aplic.ScreenSize;
import com.example.aplic.activities.GameScreenActivity;
import com.example.aplic.objects.Player;
import com.example.aplic.objects.RectGame;

import java.util.Random;

public class AlphaObj extends RectGame {
    Player player;
    GameScreenActivity gameScreenActivity;
    Paint paint;
    public AlphaObj(Context context, int color, int width, int height, int pointX, int pointY) {
        super(context, color, width, height, pointX, pointY);
        this.pointGame.x = new Random().nextInt(ScreenSize.width);
        this.pointGame.speed = 15;
        this.pointGame.velY = this.pointGame.speed * (1 + new Random().nextFloat());
        this.pointGame.velX = 0;
        setRect(this.rect);
        paint = new Paint();
        paint.setColor(color);
        paint.setAlpha(255);
        gameScreenActivity = (GameScreenActivity) context;
    }

    public void moveObst() {
        this.pointGame.x += this.pointGame.getVelX();
        this.pointGame.y += this.pointGame.getVelY();
    }

    public void changeAlpha() {
        int currAlpha = this.paint.getAlpha();
        int newAlpha;
        player = gameScreenActivity.getGamePanel().getPlayer();
        if (player.getPointGame().velX == 0 && player.getPointGame().velY == 0)
            newAlpha = currAlpha + 25;
        else
            newAlpha = currAlpha - 25;
        newAlpha = Math.min (255, Math.max(0, newAlpha));
        this.paint.setAlpha(newAlpha);
        Log.i(TAG, "changeAlpha: alpha " + newAlpha);
    }

    @Override
    public void draw(Canvas canvas) {
        setRect(this.rect);
        canvas.drawRect(this.rect, paint);
    }

    @Override
    public void update() {
        this.moveObst();
        this.changeAlpha();
    }
}
