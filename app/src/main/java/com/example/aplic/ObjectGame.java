package com.example.aplic;

import android.graphics.Canvas;
import android.graphics.Point;

// ממשק שכל אובייקט יורש שממנו הוא מצייר את עצמו על המסך ומעדכן את המסך
public interface ObjectGame {
    void draw (Canvas canvas);
    void update ();
}
