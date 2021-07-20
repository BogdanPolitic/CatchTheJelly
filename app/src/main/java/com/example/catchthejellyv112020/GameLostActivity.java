package co.snOmOtiOn.bogdan.catchthejellyv112020;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.snOmOtiOn.bogdan.catchthejellyv11.R;

public class GameLostActivity extends AppCompatActivity {

    static Button retryBtn, toMenuBtn;
    static final int[] levelNo = new int[1];
    static final DatabaseHelper dbs[] = new DatabaseHelper[1];
    static Runnable crying;
    static Handler cryingTime;
    static MediaPlayer buttonSound;
    static MediaPlayer gameLostSound;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game_lost);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-7832856056540220~9903366799");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        /*
        mMyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });
        WRITE THE CODE BELOW :
        */

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        retryBtn = (Button) findViewById(R.id.retryBtn);
        toMenuBtn = (Button) findViewById(R.id.menuLossBtn);
        dbs[0] = new DatabaseHelper(this);

        if (getIntent().hasExtra("LevelNo"))
            levelNo[0] = Integer.parseInt(getIntent().getExtras().getString("LevelNo"));

        if (levelNo[0] == 1) {
            retryBtn.setText("Retry");
        }

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLoadingScreen = new Intent(getApplicationContext(), LoadingScreenActivity.class);
                toLoadingScreen.putExtra("Load game request", (levelNo[0]) + "");
                startActivity(toLoadingScreen);

                if (levelNo[0] == 1)
                    return;

                DatabaseHelper db = dbs[0];
                Cursor res = db.getAllData();
                res.moveToNext();
                int lives = res.getInt(1);
                int maxReachedLevel = res.getInt(2);
                int rewardNextWin = res.getInt(3);
                if (lives > 0)
                    db.updateData("1", lives - 1, maxReachedLevel, rewardNextWin);
                else {
                    Intent toWatchAd = new Intent(getApplicationContext(), WatchAdActivity.class);
                    toWatchAd.putExtra("Current level", levelNo[0] + "");
                    startActivity(toWatchAd);
                }
                buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
                cryingTime.removeCallbacks(crying);
                gameLostSound.stop();
            }
        });
        toMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMenu = new Intent(getApplicationContext(), GameProgressActivity.class);    // TO BE UPDATED! -- to gameProgress
                toMenu.putExtra("Returning from game", levelNo[0] + "");
                startActivity(toMenu);
                buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
                cryingTime.removeCallbacks(crying);
                gameLostSound.stop();
            }
        });

        crying = new Runnable() {
            @Override
            public void run() {
                gameLostSound = MediaPlayer.create(getApplicationContext(), R.raw.gamelostsound);
                gameLostSound.start();
            }
        };
        cryingTime = new Handler();
        cryingTime.postDelayed(crying, 0);

        Predictable0.backgroundMusic.stop();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
