package co.snOmOtiOn.bogdan.catchthejellyv112020;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.snOmOtiOn.bogdan.catchthejellyv11.R;

public class WatchAdActivity extends AppCompatActivity implements RewardedVideoAdListener {

    static int levelNo = 1;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_watch_ad);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        MobileAds.initialize(this, "ca-app-pub-7832856056540220~4819532245");

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) this);

        Button watchAdBtn = (Button) findViewById(R.id.watchAd);
        Button returnToMenuBtn = (Button) findViewById(R.id.returnToMenuDontWatchBtn);

        watchAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRewardedVideoAd();
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
            }
        });

        returnToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMenu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toMenu);
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
            }
        });
    }

    public void onStart() {
        super.onStart();


    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
        levelNo++;
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        // dupa cum se observa usor, nu scad, si nici nu adun la "lives". De ce? Pentru ca dupa aceasta activitate, playerul e trimis catre LoadingScreenActivity, ceea ce inseamna ca isi reia viata de a intra in acel nivel. Asta inseamna ca dupa vizionarea ad-ului, lives++, apoi imediat lives--. Deci nu mai are rost sa schimb variabila lives (care de fapt nu e variabila, ci o pot obtine si modifica din baza de date, dar nah).
        Intent toLoadingScreen = new Intent(getApplicationContext(), LoadingScreenActivity.class);
        toLoadingScreen.putExtra("From reward request", levelNo + "");
        startActivity(toLoadingScreen);
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
        // Load the next rewarded video ad.
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        if (getIntent().hasExtra("Current level")) {
            levelNo = Integer.parseInt(getIntent().getExtras().getString("Current level"));
        }
        levelNo--;
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
        DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.getAllData();
        res.moveToNext();
        int lives = res.getInt(1);
        int maxReachedLevel = res.getInt(2);
        int rewardNextWin = res.getInt(3);
        lives += 0;
        db.updateData("1", lives, maxReachedLevel, rewardNextWin);
    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }
}
