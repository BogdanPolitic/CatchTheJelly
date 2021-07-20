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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;
import com.snOmOtiOn.bogdan.catchthejellyv11.R;

public class GameProgressActivity extends AppCompatActivity {

    static int page = 0;    // for now; I can put it in SQL later
    final int[] maxReachedLevel = new int[1];
    static final int LEVELS_PER_SCREEN = 4; // ABSOLUTELY UNCHANGABLE
    static final int PAGES_NO = (LoadingScreenActivity.levelsNo - 1) / LEVELS_PER_SCREEN + 1;
    final ImageView[] arrowUp = new ImageView[1]; //(ImageView) findViewById(R.id.arrowUp);
    final ImageView[] arrowDown = new ImageView[1];//(ImageView) findViewById(R.id.arrowDown);
    static ImageView[] moduloLvlImg = new ImageView[LEVELS_PER_SCREEN];
    static TextView[] moduloLvlTxt = new TextView[LEVELS_PER_SCREEN];
    static DatabaseHelper[] db = new DatabaseHelper[1];
    private InterstitialAd mInterstitialAd;
    static int y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game_progress);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        Button returnToMenu = (Button) findViewById(R.id.returnToMenu);
        returnToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent toMenu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toMenu);
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
            }
        });

        moduloLvlImg = new ImageView[LEVELS_PER_SCREEN];
        moduloLvlImg[0] = (ImageView) findViewById(R.id.moduloLvl1);
        moduloLvlImg[1] = (ImageView) findViewById(R.id.moduloLvl2);
        moduloLvlImg[2] = (ImageView) findViewById(R.id.moduloLvl3);
        moduloLvlImg[3] = (ImageView) findViewById(R.id.moduloLvl4);

        moduloLvlTxt = new TextView[LEVELS_PER_SCREEN];
        moduloLvlTxt[0] = (TextView) findViewById(R.id.moduloLvl1Txt);
        moduloLvlTxt[1] = (TextView) findViewById(R.id.moduloLvl2Txt);
        moduloLvlTxt[2] = (TextView) findViewById(R.id.moduloLvl3Txt);
        moduloLvlTxt[3] = (TextView) findViewById(R.id.moduloLvl4Txt);

        arrowUp[0] = (ImageView) findViewById(R.id.arrowUp);
        arrowDown[0] = (ImageView) findViewById(R.id.arrowDown);

        db[0] = new DatabaseHelper(this);
    }

    public void onStart() {
        super.onStart();

        if (getIntent().hasExtra("Returning from game")) {
            page = (Integer.parseInt(getIntent().getExtras().getString("Returning from game")) - 1) / 4;
        }

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.getAllData();
        res.moveToNext();
        maxReachedLevel[0] = res.getInt(2) + 1; // ultimul e cel cu - 1 heart

        if (page == 0) {
            arrowUp[0].setVisibility(View.GONE);
        }
        if (page == PAGES_NO - 1) { // nu are cum sa fie mai mare de atat
            arrowDown[0].setVisibility(View.GONE);
        }

        updateImages(maxReachedLevel[0]);
        setLevelsText();

        arrowUp[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page > 0) page--;
                if (page == 0) { // logic ca nu are cum sa fie mai mic decat atat
                    arrowUp[0].setVisibility(View.GONE);
                    arrowDown[0].setVisibility(View.VISIBLE);
                } else if (page == PAGES_NO - 1) {
                    arrowUp[0].setVisibility(View.VISIBLE);
                    arrowDown[0].setVisibility(View.GONE);
                } else {
                    arrowUp[0].setVisibility(View.VISIBLE);
                    arrowDown[0].setVisibility(View.VISIBLE);
                    for (int i = LoadingScreenActivity.levelsNo % LEVELS_PER_SCREEN; i < LEVELS_PER_SCREEN; i++) {
                        moduloLvlImg[i].setVisibility(View.VISIBLE);
                        moduloLvlTxt[i].setVisibility(View.VISIBLE);
                    }
                }
                updateImages(maxReachedLevel[0]);
                setLevelsText();
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
            }
        });

        arrowDown[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page < PAGES_NO - 1) page++;
                if (page == PAGES_NO - 1) { // nu are cum sa fie mai mare de atat
                    arrowUp[0].setVisibility(View.VISIBLE); // only if PAGES_NO > 1 !!!!
                    arrowDown[0].setVisibility(View.GONE);
                    for (int i = LoadingScreenActivity.levelsNo % LEVELS_PER_SCREEN; i < LEVELS_PER_SCREEN; i++) {
                        moduloLvlImg[i].setVisibility(View.GONE);
                        moduloLvlTxt[i].setVisibility(View.GONE);
                    }
                } else if (page == 0) {
                    arrowUp[0].setVisibility(View.GONE);
                    arrowDown[0].setVisibility(View.VISIBLE);
                } else {
                    arrowUp[0].setVisibility(View.VISIBLE);
                    arrowDown[0].setVisibility(View.VISIBLE);
                }
                updateImages(maxReachedLevel[0]);
                setLevelsText();
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
            }
        });

        for (int i = 0; i < LEVELS_PER_SCREEN; i++) {
            SetImageListener(i);
        }
    }

    private void updateImages(int maxReachedLevel) {
        int imagesNumber;
        int t = maxReachedLevel - page * LEVELS_PER_SCREEN;
        if (t < 0) t = 0;
        if (t > LEVELS_PER_SCREEN)
            imagesNumber = LEVELS_PER_SCREEN;
        else
            imagesNumber = t;
        for (int i = 0; i < imagesNumber; i++) {
            moduloLvlImg[i].setImageResource(R.drawable.ingame_preview);
            if (i == t - 1 && maxReachedLevel != 0) {
                moduloLvlImg[i].setImageResource(R.drawable.ingame_preview_minus);
            }
        }
        for (int i = imagesNumber; i < LEVELS_PER_SCREEN; i++) {
            moduloLvlImg[i].setImageResource(R.drawable.ingame_preview_grey);
        }
    }

    private void setLevelsText() {
        for (int i = 0; i < LEVELS_PER_SCREEN; i++) {   // merge optimizat codul aici
            String levelText = "Level " + (page * 4 + i + 1);
            moduloLvlTxt[i].setText(levelText);
        }
    }

    private void SetImageListener(final int index) {
        moduloLvlImg[index].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index + page * 4 >= maxReachedLevel[0]) return;
                if (maxReachedLevel[0] > 0 && index + page * 4 == maxReachedLevel[0] - 1) { // daca e ultimul nivel (cel la care ai ajuns maxim, dar pe care nu l-ai terminat/inceput inca)
                    Button returnToMenu = (Button) findViewById(R.id.returnToMenu);
                    String s = maxReachedLevel[0] + " > 0; ";
                    returnToMenu.setText(s);
                    Cursor res = db[0].getAllData();
                    res.moveToNext();
                    int lives = res.getInt(1);
                    int maxReachedLevel = res.getInt(2);
                    int rewardNextWin = res.getInt(3);
                    if (lives > 0)
                        db[0].updateData("1", lives - 1, maxReachedLevel, rewardNextWin);   // costs 2 lives
                    else {
                        Intent toWatchAd = new Intent(getApplicationContext(), WatchAdActivity.class);
                        toWatchAd.putExtra("Current level", (index + page * 4 + 1) + "");
                        startActivity(toWatchAd);
                        return;
                    }
                } else {
                    Button returnToMenu = (Button) findViewById(R.id.returnToMenu);
                    returnToMenu.setText("Return to menu");
                }
                Intent toLevel = new Intent(getApplicationContext(), LoadingScreenActivity.class);
                toLevel.putExtra("Load game request", (page * 4 + index + 1) + "");
                startActivity(toLevel);
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.mapsound);
                buttonSound.start();
            }
        });
    }
}