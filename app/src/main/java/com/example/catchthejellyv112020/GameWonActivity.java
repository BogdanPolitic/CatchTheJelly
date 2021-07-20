package co.snOmOtiOn.bogdan.catchthejellyv112020;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.snOmOtiOn.bogdan.catchthejellyv11.R;


public class GameWonActivity extends AppCompatActivity {

    static Button menuBtn, nextLevelBtn;
    TextView bonusText;
    static final int[] levelNo = new int[1];
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game_won);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        MobileAds.initialize(this, "ca-app-pub-7832856056540220~4819532245");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Predictable0.backgroundMusic.stop();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

                Intent toLoadingScreen = new Intent(getApplicationContext(), LoadingScreenActivity.class);
                toLoadingScreen.putExtra("Load game request", levelNo[0] + "");
                startActivity(toLoadingScreen);
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
                Predictable0.backgroundMusic.stop();
                finish();
            }
        });

        menuBtn = (Button) findViewById(R.id.menuBtn);
        nextLevelBtn = (Button) findViewById(R.id.nextLevelBtn);
        bonusText = (TextView) findViewById(R.id.bonusText);

        if (getIntent().hasExtra("LevelNo")) {  // stai chill, intotdeauna trebuie sa functioneze cond asta :D
            levelNo[0] = Integer.parseInt(getIntent().getExtras().getString("LevelNo"));
            levelNo[0]++;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMenu = new Intent(getApplicationContext(), GameProgressActivity.class);    // TO BE UPDATED! -- to gameProgress
                startActivity(toMenu);
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
            }
        });

        nextLevelBtn.setText(nextLevelBtn.getText().toString().concat("(" + levelNo[0] + ")"));
        nextLevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });
        Predictable0.backgroundMusic.stop();

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.getAllData();
        res.moveToNext();
        int lives = res.getInt(1);
        int maxReachedLevel = res.getInt(2);
        int rewardNextWin = res.getInt(3);
        if (maxReachedLevel < levelNo[0] - 1)
            maxReachedLevel = levelNo[0] - 1;
        if (rewardNextWin == 1) {
            lives++;
            bonusText.setVisibility(View.VISIBLE);
            rewardNextWin = 0;
        } else {
            bonusText.setVisibility(View.GONE);
            rewardNextWin = 1;
        }
        db.updateData("1", lives, maxReachedLevel, rewardNextWin);
    }
}
