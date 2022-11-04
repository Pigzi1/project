package com.example.aplic;

import android.widget.Switch;

import java.util.ArrayList;
import java.util.Random;

public class ObjectArrayManager {

    private int randomClass;
    private Class<?> cls;
    private ArrayList<Class<?>> obstList;

    public ObjectArrayManager () {
        randomClass = new Random().nextInt(2);
        generateClass(randomClass);
    }

    private void generateClass(int randomClass) {
        switch (randomClass) {
            case 1: break;
            case 2: break;
        }
    }

}