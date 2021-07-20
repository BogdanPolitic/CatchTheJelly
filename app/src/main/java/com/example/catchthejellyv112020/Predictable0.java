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
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.snOmOtiOn.bogdan.catchthejellyv11.R;

class CoordinateArray {
    Coordinates[] array;
    public CoordinateArray(int length) {
        array = new Coordinates[length];
    }
}

public class Predictable0 extends AppCompatActivity {

    private AdView mAdView;
    static final int[] levelNo = new int[1];
    static final int gameRangeX = 10, gameRangeY = 6;
    static final ImageView[][] playRange = new ImageView[gameRangeY][gameRangeX];
    static final int maxNoHearts = 13;
    static int currentNoHearts;
    static final ImageView[] hearts = new ImageView[maxNoHearts];
    static boolean clickedOnce = false;
    static int roundCurrent = 0;
    static int roundWhenClicked = -1;
    static int deltaTime = 350;
    static Runnable[] imageMotions; // este necesar sa le pot accesa din tot programul, pentru a le da kill cand este cazul
    //static final Coordinates[] traj = new Coordinates[gameRangeX * gameRangeY]; // este static deoarece ne trebuie si in functii; este final pentru ca ne trebuie si in inner classes (cele din interiorul handlerelor)
    static final CoordinateArray[] traj = new CoordinateArray[1];
    static Handler[] trajHandlers; // fiecare handler este responsabil cu un thread din vectorul de Runnable numit "imageMotions". Trebuie de asemenea accesat atunci cand ii dau kill (adica removeCallBacks)
    static final TextView[] verifyTxt = new TextView[1];
    static Button backToMenuBtn;
    static Button okBtn;
    static TextView secondsRemainingTxt;
    static TextView secondsNumberTxt;
    static TextView timeoutTxt;
    static int totalTime, totalTimeSeconds;
    static Runnable[] timings;
    static Handler[] timers;
    static Handler timeoutHandler;
    static Runnable timeoutExe;
    static MediaPlayer backgroundMusic;
    static Handler backgroundMusicHandler;
    static Runnable backgroundMusicRunnable;
    static MediaPlayer winSound;
    static final int orangeJelly = R.drawable.orangejelly_void;
    static final int redJelly = R.drawable.redjelly_void;
    static final int greenJelly = R.drawable.greenjelly_void;
    static final int blueJelly = R.drawable.bluejelly_void;
    static int currentJelly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_predictable0);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-7832856056540220~4819532245");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        varInit();

        int screenWidth = (int) (((float) getWindowManager().getDefaultDisplay().getWidth() * 1920) / (float) 1776);
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        // setting the platRange (those 60 circles forming the map)
        playRange[0][0] = (ImageView) findViewById(R.id.jellyId00);
        playRange[0][1] = (ImageView) findViewById(R.id.jellyId01);
        playRange[0][2] = (ImageView) findViewById(R.id.jellyId02);
        playRange[0][3] = (ImageView) findViewById(R.id.jellyId03);
        playRange[0][4] = (ImageView) findViewById(R.id.jellyId04);
        playRange[0][5] = (ImageView) findViewById(R.id.jellyId05);
        playRange[0][6] = (ImageView) findViewById(R.id.jellyId06);
        playRange[0][7] = (ImageView) findViewById(R.id.jellyId07);
        playRange[0][8] = (ImageView) findViewById(R.id.jellyId08);
        playRange[0][9] = (ImageView) findViewById(R.id.jellyId09);

        playRange[1][0] = (ImageView) findViewById(R.id.jellyId10);
        playRange[1][1] = (ImageView) findViewById(R.id.jellyId11);
        playRange[1][2] = (ImageView) findViewById(R.id.jellyId12);
        playRange[1][3] = (ImageView) findViewById(R.id.jellyId13);
        playRange[1][4] = (ImageView) findViewById(R.id.jellyId14);
        playRange[1][5] = (ImageView) findViewById(R.id.jellyId15);
        playRange[1][6] = (ImageView) findViewById(R.id.jellyId16);
        playRange[1][7] = (ImageView) findViewById(R.id.jellyId17);
        playRange[1][8] = (ImageView) findViewById(R.id.jellyId18);
        playRange[1][9] = (ImageView) findViewById(R.id.jellyId19);

        playRange[2][0] = (ImageView) findViewById(R.id.jellyId20);
        playRange[2][1] = (ImageView) findViewById(R.id.jellyId21);
        playRange[2][2] = (ImageView) findViewById(R.id.jellyId22);
        playRange[2][3] = (ImageView) findViewById(R.id.jellyId23);
        playRange[2][4] = (ImageView) findViewById(R.id.jellyId24);
        playRange[2][5] = (ImageView) findViewById(R.id.jellyId25);
        playRange[2][6] = (ImageView) findViewById(R.id.jellyId26);
        playRange[2][7] = (ImageView) findViewById(R.id.jellyId27);
        playRange[2][8] = (ImageView) findViewById(R.id.jellyId28);
        playRange[2][9] = (ImageView) findViewById(R.id.jellyId29);

        playRange[3][0] = (ImageView) findViewById(R.id.jellyId30);
        playRange[3][1] = (ImageView) findViewById(R.id.jellyId31);
        playRange[3][2] = (ImageView) findViewById(R.id.jellyId32);
        playRange[3][3] = (ImageView) findViewById(R.id.jellyId33);
        playRange[3][4] = (ImageView) findViewById(R.id.jellyId34);
        playRange[3][5] = (ImageView) findViewById(R.id.jellyId35);
        playRange[3][6] = (ImageView) findViewById(R.id.jellyId36);
        playRange[3][7] = (ImageView) findViewById(R.id.jellyId37);
        playRange[3][8] = (ImageView) findViewById(R.id.jellyId38);
        playRange[3][9] = (ImageView) findViewById(R.id.jellyId39);

        playRange[4][0] = (ImageView) findViewById(R.id.jellyId40);
        playRange[4][1] = (ImageView) findViewById(R.id.jellyId41);
        playRange[4][2] = (ImageView) findViewById(R.id.jellyId42);
        playRange[4][3] = (ImageView) findViewById(R.id.jellyId43);
        playRange[4][4] = (ImageView) findViewById(R.id.jellyId44);
        playRange[4][5] = (ImageView) findViewById(R.id.jellyId45);
        playRange[4][6] = (ImageView) findViewById(R.id.jellyId46);
        playRange[4][7] = (ImageView) findViewById(R.id.jellyId47);
        playRange[4][8] = (ImageView) findViewById(R.id.jellyId48);
        playRange[4][9] = (ImageView) findViewById(R.id.jellyId49);

        playRange[5][0] = (ImageView) findViewById(R.id.jellyId50);
        playRange[5][1] = (ImageView) findViewById(R.id.jellyId51);
        playRange[5][2] = (ImageView) findViewById(R.id.jellyId52);
        playRange[5][3] = (ImageView) findViewById(R.id.jellyId53);
        playRange[5][4] = (ImageView) findViewById(R.id.jellyId54);
        playRange[5][5] = (ImageView) findViewById(R.id.jellyId55);
        playRange[5][6] = (ImageView) findViewById(R.id.jellyId56);
        playRange[5][7] = (ImageView) findViewById(R.id.jellyId57);
        playRange[5][8] = (ImageView) findViewById(R.id.jellyId58);
        playRange[5][9] = (ImageView) findViewById(R.id.jellyId59);

        //setting the circles positions
        for (int i = 0; i < gameRangeY; i++) {
            for (int j = 0; j < gameRangeX; j++) {
                playRange[i][j].setImageResource(R.drawable.paint_format_void);
                playRange[i][j].setX((int) ((float) ((100 + 140 * j) * screenWidth) / (float) 1920));
                playRange[i][j].setY((int) ((float) ((100 + 140 * i) * screenHeight) / (float) 1080));
                playRange[i][j].setScaleX((float) screenHeight / (float) 1080);
                playRange[i][j].setScaleY((float) screenHeight / (float) 1080);
            }
        }

        hearts[0] = (ImageView) findViewById(R.id.heartImg0);
        hearts[1] = (ImageView) findViewById(R.id.heartImg1);
        hearts[2] = (ImageView) findViewById(R.id.heartImg2);
        hearts[3] = (ImageView) findViewById(R.id.heartImg3);
        hearts[4] = (ImageView) findViewById(R.id.heartImg4);
        hearts[5] = (ImageView) findViewById(R.id.heartImg5);
        hearts[6] = (ImageView) findViewById(R.id.heartImg6);
        hearts[7] = (ImageView) findViewById(R.id.heartImg7);
        hearts[8] = (ImageView) findViewById(R.id.heartImg8);
        hearts[9] = (ImageView) findViewById(R.id.heartImg9);
        hearts[10] = (ImageView) findViewById(R.id.heartImg10);
        hearts[11] = (ImageView) findViewById(R.id.heartImg11);
        hearts[12] = (ImageView) findViewById(R.id.heartImg12);

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.getAllData();
        res.moveToNext();
        currentNoHearts = res.getInt(1);

        //setting the hearts positions
        for (int i = 0; i < maxNoHearts; i++) {
            if (i >= currentNoHearts)
                hearts[i].setImageResource(R.drawable.heart_placeholder);
            else
                hearts[i].setImageResource(R.drawable.heart);
            hearts[i].setX((int) ((float) ((100 + 140 * i) * screenWidth) / (float) 1920));
            hearts[i].setY((int) ((float) ((125 + 140 * gameRangeY) * screenHeight) / (float) 1080));
            hearts[i].setScaleX((float) screenHeight / (float) 1080);
            hearts[i].setScaleY((float) screenHeight / (float) 1080);
            hearts[i].setVisibility(View.VISIBLE);
        }

        // the BACK TO MENU button
        backToMenuBtn = (Button) findViewById(R.id.backToMenuBtn);
        backToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toMenu);
                for (int i = 0; i < traj[0].array.length; i++) {
                    trajHandlers[i].removeCallbacks(imageMotions[i]);
                }
                MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                buttonSound.start();
                backgroundMusic.stop();
                finish();
            }
        });

        secondsRemainingTxt = (TextView) findViewById(R.id.secondsRemaniningTxt);
        secondsRemainingTxt.setVisibility(View.VISIBLE);
        secondsNumberTxt = (TextView) findViewById(R.id.secondsNumberTxt);
        secondsNumberTxt.setVisibility(View.VISIBLE);
        timeoutTxt = (TextView) findViewById(R.id.timeoutTxt);
        timeoutTxt.setVisibility(View.GONE);
        okBtn = (Button) findViewById(R.id.okBtn);
        okBtn.setVisibility(View.GONE);
        timeoutHandler = new Handler();
        timeoutExe = new Runnable() {
            @Override
            public void run() {
                /*Intent goToTimeoutFault = new Intent(getApplicationContext(), TimeoutFaultActivity.class);
                goToTimeoutFault.putExtra("LevelNo", levelNo[0] + "");
                startActivity(goToTimeoutFault);*/
                for (int i = 0; i < traj[0].array.length; i++) {
                    trajHandlers[i].removeCallbacks(imageMotions[i]);
                }
                for (int i = 0; i < timers.length; i++) {
                    timers[i].removeCallbacks(timings[i]);
                }
                for (int y = 0; y < gameRangeY; y++) {
                    for (int x = 0; x < gameRangeX; x++) {
                        playRange[y][x].setVisibility(View.GONE);
                    }
                }
                for (int i = 0; i < maxNoHearts; i++) {
                    hearts[i].setVisibility(View.GONE);
                }
                backToMenuBtn.setVisibility(View.GONE);
                secondsRemainingTxt.setVisibility(View.GONE);
                secondsNumberTxt.setVisibility(View.GONE);
                timeoutTxt.setVisibility(View.VISIBLE);
                okBtn.setVisibility(View.VISIBLE);

                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent goToNext = new Intent(getApplicationContext(), GameLostActivity.class);
                        goToNext.putExtra("LevelNo", levelNo[0] + "");
                        startActivity(goToNext);
                        for (int i = 0; i < traj[0].array.length; i++) {
                            trajHandlers[i].removeCallbacks(imageMotions[i]);
                        }
                        for (int i = 0; i < timers.length; i++) {
                            timers[i].removeCallbacks(timings[i]);
                        }
                        MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.buttonsound);
                        buttonSound.start();
                        finish();
                        backgroundMusic.stop();
                    }
                });

                MediaPlayer timeoutSound = MediaPlayer.create(getApplicationContext(), R.raw.timeoutsound);
                timeoutSound.start();
            }
        };

        backgroundMusicHandler = new Handler();
        backgroundMusicRunnable = new Runnable() {
            @Override
            public void run() {
                if (levelNo[0] % 3 == 0)
                    backgroundMusic = MediaPlayer.create(getApplicationContext(), R.raw.backgroundmusic1);
                else if (levelNo[0] % 3 == 1)
                    backgroundMusic = MediaPlayer.create(getApplicationContext(), R.raw.backgroundmusic2);
                else
                    backgroundMusic = MediaPlayer.create(getApplicationContext(), R.raw.backgroundmusic3);
                if (backgroundMusic != null)
                    if (!backgroundMusic.isPlaying())
                        backgroundMusic.start();
            }
        };
        backgroundMusicHandler.postDelayed(backgroundMusicRunnable, 200);   // am zis 200, ca sa nu fie chiar 0 (adica de la 0.2 sec)
    //}

    //@Override
    //protected void onStart() {
    //    super.onStart();
        // alegerea traiectoriei
        int[] trajInfo = new int[1]; // e redundanta initializarea aici, dar trebuie sa pacalesc compilatorul ca array-ul a fost initializat in orice caz
        for (int i = 1; i <= LoadingScreenActivity.levelsNo; i++) {
            if (getIntent().hasExtra(i + "")) {
                levelNo[0] = i;
                String[] trajInfoString = getIntent().getExtras().getString(i + "").replaceAll(" ", "").split(",");
                trajInfo = new int[trajInfoString.length];
                for (int j = 0; j < trajInfo.length; j++) {
                    trajInfo[j] = Integer.parseInt(trajInfoString[j]);
                }
                break;
            }
        }
        if (levelNo[0] % 4 == 0)
            currentJelly = orangeJelly;
        else if (levelNo[0] % 4 == 1)
            currentJelly = redJelly;
        else if (levelNo[0] % 4 == 2)
            currentJelly = greenJelly;
        else
            currentJelly = blueJelly;

        TextView tw = findViewById(R.id.testTxt);
        String s = trajInfo.length + "," + trajInfo[0];
        tw.setText(s);

        traj[0] = new CoordinateArray(trajInfo[0]);
        for (int i = 0; i < trajInfo[0]; i++) {
            traj[0].array[i] = new Coordinates(trajInfo[2 * i + 1], trajInfo[2 * i + 2]);
        }
        deltaTime = LoadingScreenActivity.deltaTime;

        totalTime = gameRangeY * gameRangeX * deltaTime;
        totalTimeSeconds = totalTime / 1000;
        timings = new Runnable[totalTimeSeconds];
        timers = new Handler[totalTimeSeconds];
        for (int i = 0; i < totalTimeSeconds; i++) {
            timers[i] = new Handler();
        }

        imageMotions = new Runnable[traj[0].array.length];
        trajHandlers = new Handler[traj[0].array.length];
        for (int i = 0; i < traj[0].array.length; i++) {
            trajHandlers[i] = new Handler();
        }

        // deplasarea desenului conform traiectoriei
        playRange[traj[0].array[0].y][traj[0].array[0].x].setImageResource(currentJelly);
        for (int i = 1; i < traj[0].array.length; i++) {
            nextAppearance(i, deltaTime);
        }
        trajHandlers[traj[0].array.length - 1].postDelayed(new Runnable() {
            @Override
            public void run() {
                playRange[traj[0].array[traj[0].array.length - 1].y][traj[0].array[traj[0].array.length - 1].x].setImageResource(R.drawable.paint_format_void);
            }
        }, deltaTime * traj[0].array.length);

        for (int i = 0; i < totalTimeSeconds; i++) {
            timers(i, totalTimeSeconds - i);
        }
        timeoutHandler.postDelayed(timeoutExe, (totalTimeSeconds + 1) * 1000);

        // stabilirea celulei (cercului) pe care s-a dat click, si notificarea tuturor celorlalte celule de a opri procesul de deplasare
        for (int i = 0; i < gameRangeY; i++) {
            for (int j = 0; j < gameRangeX; j++) {
                final int finalI = i;
                final int finalJ = j;
                playRange[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!clickedOnce) {
                            roundWhenClicked = roundCurrent;
                            final int roundCurrentLocalVar = roundCurrent;
                            if (finalI == traj[0].array[roundCurrent].y && finalJ == traj[0].array[roundCurrent].x) { // s-a dat click unde trebuie
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        playRange[traj[0].array[roundCurrentLocalVar].y][traj[0].array[roundCurrentLocalVar].x].setImageResource(R.drawable.win_format_void);
                                    }
                                }, deltaTime);
                                Button nextLevelBtn = (Button) findViewById(R.id.menuBtn);
                                nextLevelBtn.setVisibility(View.VISIBLE);
                                nextLevelBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (levelNo[0] < LoadingScreenActivity.levelsNo) {
                                            Intent goToNext = new Intent(getApplicationContext(), GameWonActivity.class);
                                            goToNext.putExtra("LevelNo", levelNo[0] + "");
                                            startActivity(goToNext);
                                        } else {
                                            Intent gameCompleted = new Intent(getApplicationContext(), GameCompletedActivity.class);
                                            startActivity(gameCompleted);
                                        }
                                        for (int i = 0; i < traj[0].array.length; i++) {
                                            trajHandlers[i].removeCallbacks(imageMotions[i]);
                                        }
                                        for (int i = 0; i < timers.length; i++) {
                                            timers[i].removeCallbacks(timings[i]);
                                        }
                                        timeoutHandler.removeCallbacks(timeoutExe);
                                        MediaPlayer buttonSound = MediaPlayer.create(getApplicationContext(), R.raw.mapsound);
                                        buttonSound.start();
                                        backgroundMusic.stop();
                                        finish();
                                    }
                                });
                                new Handler().postDelayed(new Runnable() {  // if the user does not press the nextLevel button in the next 2 secs
                                    public void run() { // now comes same thing as 6 lines above
                                        if (levelNo[0] < LoadingScreenActivity.levelsNo) {
                                            Intent goToNext = new Intent(getApplicationContext(), GameWonActivity.class);
                                            goToNext.putExtra("LevelNo", levelNo[0] + "");
                                            startActivity(goToNext);
                                        } else {
                                            Intent gameCompleted = new Intent(getApplicationContext(), GameCompletedActivity.class);
                                            startActivity(gameCompleted);
                                        }
                                        for (int i = 0; i < traj[0].array.length; i++) {
                                            trajHandlers[i].removeCallbacks(imageMotions[i]);
                                        }
                                        for (int i = 0; i < timers.length; i++) {
                                            timers[i].removeCallbacks(timings[i]);
                                        }
                                        timeoutHandler.removeCallbacks(timeoutExe);
                                        backgroundMusic.stop();
                                        finish();
                                    }
                                }, 1500);
                                MediaPlayer correctPress = MediaPlayer.create(getApplicationContext(), R.raw.correctpress);
                                correctPress.start();
                                winSound = MediaPlayer.create(getApplicationContext(), R.raw.winsound);
                                winSound.start();
                            } else {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        playRange[finalI][finalJ].setImageResource(R.drawable.loss_format_void);
                                    }
                                }, deltaTime);
                                new Handler().postDelayed(new Runnable() {  // if the user does not press the nextLevel button in the next 2 secs
                                    public void run() { // now comes same thing as 6 lines above
                                        Intent goToNext = new Intent(getApplicationContext(), GameLostActivity.class);
                                        goToNext.putExtra("LevelNo", levelNo[0] + "");
                                        startActivity(goToNext);
                                        for (int i = 0; i < traj[0].array.length; i++) {
                                            trajHandlers[i].removeCallbacks(imageMotions[i]);
                                        }
                                        for (int i = 0; i < timers.length; i++) {
                                            timers[i].removeCallbacks(timings[i]);
                                        }
                                        timeoutHandler.removeCallbacks(timeoutExe);
                                        backgroundMusic.stop();
                                        finish();
                                    }
                                }, 1000);
                                MediaPlayer wrongPress = MediaPlayer.create(getApplicationContext(), R.raw.wrongpress);
                                wrongPress.start();
                                MediaPlayer lossSound = MediaPlayer.create(getApplicationContext(), R.raw.losssound);
                                lossSound.start();
                            }
                        }
                    }
                });
            }
        }
    }

    // functie folosita pentru a evita updatarea indexului din cadrul procesului in loop-ul de procese (handlere)
    private void nextAppearance(final int index, int deltaTime) {
        imageMotions[index] = new Runnable() {
            @Override
            public void run() {
                if (!clickedOnce) {
                    roundCurrent = index;
                    playRange[traj[0].array[index - 1].y][traj[0].array[index - 1].x].setImageResource(R.drawable.paint_format_void);
                    if (roundWhenClicked == -1) {
                        playRange[traj[0].array[index].y][traj[0].array[index].x].setImageResource(currentJelly);
                    } else {
                        String s = roundCurrent + "";
                        clickedOnce = true;
                    }
                }
            }
        };
        trajHandlers[index].postDelayed(imageMotions[index], deltaTime * index);
    }

    private void timers(final int index, final int secondsLeft) {
        timings[index] = new Runnable() {
            public void run() {
                TextView verifyTxt = (TextView) findViewById(R.id.secondsNumberTxt);
                String timeLeft = (secondsLeft - 1) + "";
                verifyTxt.setText(timeLeft);
                /*if (index == 0) {
                    if (levelNo[0] % 3 == 0)
                        backgroundMusic = MediaPlayer.create(getApplicationContext(), R.raw.backgroundmusic1);
                    else if (levelNo[0] % 3 == 1)
                        backgroundMusic = MediaPlayer.create(getApplicationContext(), R.raw.backgroundmusic2);
                    else
                        backgroundMusic = MediaPlayer.create(getApplicationContext(), R.raw.backgroundmusic3);
                    if (backgroundMusic != null)
                        if (!backgroundMusic.isPlaying())
                            backgroundMusic.start();
                }*/
            }
        };
        timers[index].postDelayed(timings[index], 1000 * index);
    }

    private void varInit() {
        clickedOnce = false;
        roundCurrent = 0;
        roundWhenClicked = -1;
    }
}
