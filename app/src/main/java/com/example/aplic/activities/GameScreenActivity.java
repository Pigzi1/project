package com.example.aplic.activities;


import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.aplic.GamePanel;
import com.example.aplic.R;
import com.example.aplic.dialogs.PauseDialog;

public class GameScreenActivity extends Activity implements View.OnClickListener {

    GamePanel gamePanel;
    FrameLayout frameLayout;
    ImageButton ibPause;
    Button p, up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        gamePanel = new GamePanel(this);
        frameLayout = findViewById(R.id.cvGame);
        frameLayout.addView(gamePanel);
        ibPause = findViewById(R.id.ibPause);
        ibPause.setOnClickListener(this);
        p = findViewById(R.id.p);
        up = findViewById(R.id.up);
        p.setOnClickListener(this);
        p.setOnClickListener(this);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    // מוריד מהפריים לייאווט את המסך הקודם ושם עליו את החדש
    public void onRetry(GamePanel newGamePanel) {
        frameLayout.removeView(this.gamePanel);
        frameLayout.addView(newGamePanel);
        this.gamePanel = newGamePanel;
    }

    // עוצר את המשחק בלחיצה
    @Override
    public void onClick(View view) {
        if (view == ibPause) {
            Log.i(TAG, "onClick: paused game");
            this.gamePanel.setGamePaused(true);
        }
        else if (view == p) {
            this.gamePanel.getThread().setRunning(false);
        }
        else if (view == up) {
            this.gamePanel.getThread().setRunning(true);
        }

    }
}