package com.somilgo.emoji_game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by somil on 1/23/16.
 */
public class GameOver extends Activity {

    private int score;
    private boolean timed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Intent game = getIntent();
        Bundle extras = game.getExtras();
        score = extras.getInt("score");
        timed = extras.getBoolean("timed");
        final TextView score_view = (TextView) findViewById(R.id.score_view);
        if (timed) {
            score_view.setText("You found " + score + " emojis!");
            if(prefs.getInt("highscore", 0) < score)
            {
                editor.putInt("highscore", score);
                editor.commit();
            }
        }
        else
        {
            score_view.setText("You found 5 emojis in " + score + " seconds");
            if(prefs.getInt("highscore2", 99999999) > score)
            {
                editor.putInt("highscore2", score);
                editor.commit();
            }
        }
    }

    public void onClickMain(View view)
    {
        Intent mainMenu = new Intent(this, MainActivity.class);
        startActivity(mainMenu);
    }

}
