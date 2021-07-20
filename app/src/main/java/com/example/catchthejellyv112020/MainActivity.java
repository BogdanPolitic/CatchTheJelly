package com.example.catchthejellyv112020;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static final co.snOmOtiOn.bogdan.catchthejellyv112020.DatabaseHelper[] db = new co.snOmOtiOn.bogdan.catchthejellyv112020.DatabaseHelper[1];
    static int livesNo = 5;
    static int maxReachedLevel = 0;
    static int rewardNextWin = 0;
    static Button showTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Button gameProgressBtn = findViewById(R.id.gameProgressBtn);
        gameProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toGameProgress = new Intent(getApplicationContext(), co.snOmOtiOn.bogdan.catchthejellyv112020.GameProgressActivity.class);
                startActivity(toGameProgress);
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
            }
        });

        Button newGameBtn = findViewById(R.id.newGameBtn);
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toNewGame = new Intent(getApplicationContext(), co.snOmOtiOn.bogdan.catchthejellyv112020.LoadingScreenActivity.class);
                toNewGame.putExtra("New game request", "1");
                startActivity(toNewGame);
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
            }
        });

        db[0] = new co.snOmOtiOn.bogdan.catchthejellyv112020.DatabaseHelper(this);
        if (db[0].getAllData().getCount() == 0) {
            db[0].insertData(livesNo, maxReachedLevel, rewardNextWin);
        }
        if (db[0].getAllData().getCount() == 2) { // pentru combaterea unei erori aparute ABSOLUT fara motiv!
            db[0].deleteData(2 + "");
        }

        showTable = (Button) findViewById(R.id.showTable);
        showTable.setVisibility(View.GONE);
        //ShowAllData();
    }

    public void ShowAllData() {
        showTable.setVisibility(View.VISIBLE);
        showTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db[0].getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "No data found.");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :" + res.getString(0) + "\n"); // 0 e indexul ptr campul ID
                    buffer.append("Lives :" + res.getString(1) + "\n"); // 1 e indexul pt campul NAME
                    buffer.append("maxReachedLevel :" + res.getString(2) + "\n"); // 2 e indxeul pt campul SURNAME
                    buffer.append("rewardNextWin :" + res.getString(3) + "\n\n"); // 3 e indexul pt campul MARKS
                }

                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
