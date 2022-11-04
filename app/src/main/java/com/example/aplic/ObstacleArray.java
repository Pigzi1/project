package com.example.aplic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import com.example.aplic.objects.AlphaObj;
import com.example.aplic.objects.MovingRectGame;
import com.example.aplic.objects.Player;
import com.example.aplic.objects.RectGame;

import java.lang.reflect.Type;


public class ObstacleArray {
//    private final MovingRectGame[] mroList;
    private final RectGame[] objList;
    private Class<?> objectType;
    private int id;
    private Context context;

    public ObstacleArray(Context context, Class<?> classType,int numOfObst) {
        this.context = context;
        this.objectType = classType;
        setIdFromClass(classType);
        this.objList = new RectGame[numOfObst];


        for (int i = 0; i < numOfObst; i++) {
            RectGame currObst = makeNewInstance();
            objList[i] = currObst;
        }
    }

    public boolean collision (Player player) {
        for (RectGame obj : objList)
            if (obj.collision(player))
                return true;
        return false;
    }

    public void draw(Canvas canvas) {
        for (RectGame obj: objList) {
            obj.draw(canvas);
        }
    }

    public void update() {
        for (RectGame obj : objList) {
            obj.update();
        }
    }

    private void setIdFromClass(Class<?> classType) {
        if (classType == AlphaObj.class) {
            this.id = 0;
        }
        else if (classType == MovingRectGame.class) {
            this.id = 1;
        }
    }

    public RectGame makeNewInstance() {
        RectGame currObj;
        switch (this.id) {
            case 0: currObj = new AlphaObj(context, Color.rgb(0,255,0),100,100,0,0); break;
            case 1: currObj = new MovingRectGame(context, Color.rgb(0,255,0),100,100,0,0); break;
            default: currObj = null; break;
        }
        return currObj;
    }
}
