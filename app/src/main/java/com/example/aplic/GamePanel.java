package com.example.aplic;

import static android.content.ContentValues.TAG;
import static com.example.aplic.ScreenSize.height;
import static com.example.aplic.ScreenSize.width;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.res.ResourcesCompat;

import com.example.aplic.activities.GameScreenActivity;
import com.example.aplic.dialogs.GameOverDialog;
import com.example.aplic.dialogs.PauseDialog;
import com.example.aplic.objects.AlphaObj;
import com.example.aplic.objects.MovingRectGame;
import com.example.aplic.objects.Player;
import com.example.aplic.objects.PointGame;

import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private ScoreThread scoreThread;
    private Context context;

    private Player player;
    private ObstacleArray obstacleArray;
    private int score = 0;

    private boolean gamePaused = false;
    private boolean gameOver = false;

    public DisplayMetrics metrics = this.getResources().getDisplayMetrics();

    public GamePanel(Context context) {
        super(context);
        this.context = context;

        width = metrics.widthPixels;
        height = metrics.heightPixels;
        System.out.println("w" + width);
        System.out.println("h" + height);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        player = new Player(this.context, Color.rgb(255, 0, 0), 100, 100, ScreenSize.width/2, (ScreenSize.height*4)/5);
        obstacleArray = new ObstacleArray(context, MovingRectGame.class, 15);

        scoreThread = new ScoreThread(this);
        scoreThread.setRunning(true);
        scoreThread.start();

        setFocusable(true);
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public Player getPlayer() {
        return player;
    }

    public MainThread getThread() {
        return thread;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        // מתחיל את התרד כשנוצר המסך
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
//        thread.setRunning(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        float x = e.getX();
        float y = e.getY();

        int w = player.getWidth(); int h = player.getHeight();
        //שומר שהשחקן לא יצא מהגבולות של המסך
        if (x < w/2) {x = w/2;}
        if (x > width - w/2) {x = width - w/2;}
        if (y < h/2) {y = h/2;}
        if (y > height - h/2) {y = height - h/2;}
        PointGame playerPoint= player.getPointGame();
        float previousX = playerPoint.x;
        float previousY = playerPoint.y;

        // מזיז את הדמות בקצב אחיד אל מיקום ה touch event
        if (e.getAction() != MotionEvent.ACTION_UP)
        {
                float dX = x - previousX;
                float dY = y - previousY;

                float distance = (float)Math.sqrt(Math.pow(x - playerPoint.x, 2) + Math.pow(y - playerPoint.y, 2));

                float directionX = dX / distance;
                float directionY = dY / distance;

                if (distance > 0) {
                    playerPoint.setVelX(directionX * playerPoint.getSpeed());
                    playerPoint.setVelY(directionY * playerPoint.getSpeed());
                }
                playerPoint.set((int)previousX + (int)playerPoint.getVelX(), (int)previousY + (int)playerPoint.getVelY());
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // מצייר את הכאנבאס
        canvas.drawColor(Color.BLUE);
        player.draw(canvas);
        player.getPointGame().setVelFromType();
        obstacleArray.draw(canvas);
//        alphaObj.draw(canvas);

        scoreDrawer(canvas);

        if (gamePaused) {
            if (gameOver) {
                thread.setRunning(false);
                Log.i(TAG, "draw: about to stop the thread");
                new Handler(Looper.getMainLooper()).post(this::makeGameOverDialog);
            }
            if (!gameOver) {
                Log.i(TAG, "draw: about to pause the thread");
                this.thread.pauseThread();
                new Handler(Looper.getMainLooper()).post(this::makeGamePausedDialog);
            }
        }
    }


    public void update(){
        if (gamePaused)
            Log.i(TAG, "update: game paused");
        if(gameOver) {
            Log.i(TAG, "update: game over");
        }
        // מעדכן את מיקום האובייקטים על המסך כל עוד המשחק לא נעצר
        if(!gamePaused) {
            player.update();
            obstacleArray.update();
//            alphaObj.update();

            // אם המשחק רואה שקרתה התנגשות המשחק יעצר ויזיז את השחקן אל המיקום ההתחלתי שלו
            if (obstacleArray.collision(player)) {
                gameOver = true;
                gamePaused = true;
                Log.i(TAG, "update: collision -> game over!");
                player.getPointGame().set(width/2, height - 100);
                player.update();
            }
        }
    }

    // עוד לא בשימוש ויתווסף בהמשך
    private void obstacleDrawer(Canvas canvas) {
        ObstacleArray newArray = new ObstacleArray(this.context, MovingRectGame.class,10 + new Random().nextInt(10));
        newArray.draw(canvas);
    }


    public void scoreDrawer(Canvas canvas) {
        // מצייר על המסך את מספר הנקודות שצבר על השחקן שאותם הוא מקבל מהתרד של הניקוד
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
        final Typeface typeface = ResourcesCompat.getFont(context, R.font.franklin);
        paint.setTypeface(typeface);
        canvas.drawText("SCORE: " + score, width/2, 150, paint);
    }

    // מייצר את הדיאלוג כאשר השחקן נפסל
    public void makeGameOverDialog () {
        GameOverDialog dialog = new GameOverDialog(context, score);
        dialog.show();
    }

    // מייצר את הדיאלוג כאשר השחקן לוחץ על כפתור העצירה של המשחק
    public void makeGamePausedDialog() {
//        this.thread.pauseThread();
        this.thread.setRunning(false);
        PauseDialog dialog = new PauseDialog(context);
        dialog.show();
    }

    // מעדכן את הניקוד
    public void scoreUpdater() {
        setScore(getScore() + 500);
    }
}
