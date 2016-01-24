package com.somilgo.emoji_game;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.lang.StringEscapeUtils;

import java.nio.charset.Charset;

/**
 * Created by somil on 1/23/16.
 */
public class Timed_Game extends Activity {

    private int score;
    private int randomEmoji;
    private int[] emojis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timed_game);

        final TextView find = (TextView) (findViewById(R.id.emojiSpace));
        emojis = Functions.getEmojiList();
        randomEmoji = emojis[(int)(Math.random()*emojis.length)];

        find.setText(Functions.getEmojiByUnicode(randomEmoji));
        final Button enterButton = (Button) (findViewById(R.id.button));
        final EditText field = (EditText) (findViewById(R.id.input));
        final TextView scoreText = (TextView) (findViewById(R.id.score_text_view));

        score = 0;

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int correct = (Character.codePointAt(Functions.getEmojiByUnicode(randomEmoji).toString(), 0));
                    int guessed = (Character.codePointAt(field.getText().toString(), 0));

                    if (correct == guessed)
                    {
                        randomEmoji = emojis[(int)(Math.random()*emojis.length)];
                        find.setText(Functions.getEmojiByUnicode(randomEmoji));
                        field.setText("");
                        score++;
                        scoreText.setText("Score: " + score);
                    }
                    else
                    {
                        field.setText("");
                    }
                }
                catch (Exception e){
                    field.setText("");
                }
            }
        });
        field.setImeOptions(EditorInfo.IME_ACTION_DONE);
        field.setOnEditorActionListener(new EditText.OnEditorActionListener()
        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    enterButton.performClick();
                    return true;
                }
                return false;
            }
        });

        final TextView timer = (TextView) findViewById(R.id.timer);

        new CountDownTimer(61000,1000)
        {
            public void onTick(long millisUntilFinished)
            {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish()
            {
                gameOver();

            }
        }.start();


    }

    public void gameOver()
    {
        Intent gameOverIntent = new Intent(this, GameOver.class);
        Bundle extras = new Bundle();
        extras.putInt("score",score);
        extras.putBoolean("timed",true);
        gameOverIntent.putExtras(extras);
        startActivity(gameOverIntent);
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
