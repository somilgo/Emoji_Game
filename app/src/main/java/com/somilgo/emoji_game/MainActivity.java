package com.somilgo.emoji_game;

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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView find = (TextView) (findViewById(R.id.emojiSpace));
        int[] emojis = Functions.getEmojiList();
        final int randomEmoji = emojis[(int)(Math.random()*emojis.length)];

        find.setText(Functions.getEmojiByUnicode(randomEmoji));
        final Button enterButton = (Button) (findViewById(R.id.button));
        final EditText field = (EditText) (findViewById(R.id.input));
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int correct = (Character.codePointAt(Functions.getEmojiByUnicode(randomEmoji).toString(), 0));
                    int guessed = (Character.codePointAt(field.getText().toString(), 0));

                    if (correct == guessed)
                    {
                        find.setText("Nice!");
                    }
                }
                catch (Exception e){

                }




            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
