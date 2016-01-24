package com.somilgo.emoji_game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        final TextView highscore_display = (TextView) findViewById(R.id.highscore_view);
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        highscore_display.setText("Most Emojis in 1 Minute: " + prefs.getInt("highscore",0));
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


    public void onClickTimedGame(View view)
    {
        Intent timedGameIntent = new Intent(this, Timed_Game.class);
        startActivity(timedGameIntent);

    }

    public void onClickHighestScore(View view)
    {
        Intent scoreGameIntent = new Intent(this, HighestScore.class);
        startActivity(scoreGameIntent);
    }

}
