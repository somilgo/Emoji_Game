package com.somilgo.emoji_game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by somil on 1/23/16.
 */
public class HighestScore extends Activity {

    private int score;
    private int randomEmoji;
    private int[] emojis;
    private int time;
    private CountDownTimer CountUp;

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

        final TextView timer = (TextView) findViewById(R.id.timer);
        time = 0;

        CountUp = new CountDownTimer(900000000,1000)
        {
            public void onTick(long millisUntilFinished)
            {
                time++;
                timer.setText(""+time);
                if (score >=5)
                {
                    gameOver();
                }
            }

            public void onFinish()
            {
                //this shouldn't finish
            }
        }.start();




    }

    public void gameOver()
    {
        CountUp.cancel();
        Intent gameOverIntent = new Intent(this, GameOver.class);
        Bundle extras = new Bundle();
        extras.putInt("score",time);
        extras.putBoolean("timed",false);
        gameOverIntent.putExtras(extras);
        score = 0;
        startActivity(gameOverIntent);
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }
}
