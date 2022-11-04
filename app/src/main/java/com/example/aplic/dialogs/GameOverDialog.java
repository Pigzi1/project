package com.example.aplic.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplic.GamePanel;
import com.example.aplic.activities.GameScreenActivity;
import com.example.aplic.R;

public class GameOverDialog extends Dialog implements View.OnClickListener {

    private TextView tvScore;
    private Button btBack;
    private Button btRetry;
    private int score;
    private Context context;
    private GameScreenActivity activity;

    public GameOverDialog(Context context, int score) {
        super(context);
        this.context = context;
        this.activity = (GameScreenActivity) context;
        this.score = score;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_gameover);
        setCancelable(false);

        tvScore = findViewById(R.id.tvScore);
        btBack = findViewById(R.id.btBack);
        btRetry = findViewById(R.id.btRetry);
        btBack.setOnClickListener(this);
        btRetry.setOnClickListener(this);

        tvScore.setText("Total Score: " + score);
    }

    @Override
    public void onClick(View view) {

        // כפתור חזרה אחורה
        if (view == btBack) {
            this.dismiss();
            GameScreenActivity gg = (GameScreenActivity)context;
            gg.getGamePanel().getThread().setRunning(false);
            this.activity.finish();
        }

        // כפתור התחלה מחדש
        else if (view == btRetry) {
            this.dismiss();
            GamePanel newGamePanel = new GamePanel(activity);
            this.activity.onRetry(newGamePanel);
        }
    }
}
