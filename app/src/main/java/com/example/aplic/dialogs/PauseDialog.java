package com.example.aplic.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aplic.GamePanel;
import com.example.aplic.activities.GameScreenActivity;
import com.example.aplic.R;

public class PauseDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private Button btResume;
    private Button btStartOver;
    private Button btLevelSelect;
    private GameScreenActivity activity;


    public PauseDialog (Context context) {
        super(context);
        this.context = context;
        this.activity = (GameScreenActivity) context;
    }

    @Override
    protected void onCreate (final Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.dialog_pause);
        setCancelable(false);

        btResume = findViewById(R.id.btResume);
        btStartOver = findViewById(R.id.btStartOver);
        btLevelSelect = findViewById(R.id.btLevelSelect);

        btResume.setOnClickListener(this);
        btStartOver.setOnClickListener(this);
        btLevelSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //כפתור להמשיך את המשחק (כרגע לא עובד)
        if (view == btResume) {
            this.dismiss();
            this.activity.getGamePanel().getThread().setRunning(true);
            this.activity.getGamePanel().setGamePaused(false);
            this.activity.getGamePanel().getThread().unPauseThread();
//            this.activity.getGamePanel().getThread().
        }
        // להתחיל משחק מחדש
        else if (view == btStartOver) {
            this.dismiss();
            GamePanel newGamePanel = new GamePanel(activity);
            this.activity.onRetry(newGamePanel);
        }
        // לחזור מסך אחורה
        else if (view == btLevelSelect) {
            this.dismiss();
            this.activity.finish();
        }
    }
}
