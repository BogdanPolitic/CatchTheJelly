package co.snOmOtiOn.bogdan.catchthejellyv112020;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.os.Handler;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.snOmOtiOn.bogdan.catchthejellyv11.R;

public class LoadingScreenActivity extends AppCompatActivity {

    static final int levelsNo = 45;
    static Coordinates[] trajectory;
    static int deltaTime = 350;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_loading_screen);

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
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        final int[] levelNo = new int[1];
        if (getIntent().hasExtra("New game request")) {
            levelNo[0] = 1;
        } else if (getIntent().hasExtra("Load game request")) {
            levelNo[0] = Integer.parseInt(getIntent().getExtras().getString("Load game request"));
        } else if (getIntent().hasExtra("From reward request")) {
            levelNo[0] = Integer.parseInt(getIntent().getExtras().getString("From reward request"));
        }

        // generarea traiectoriei in functie de nivel
        generateLevelTrajectory(levelNo[0]);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(trajectory.length + "");
        for (int i = 0; i < trajectory.length; i++) {
            stringBuilder.append(", " + trajectory[i].y);
            stringBuilder.append(", " + trajectory[i].x);
        }
        stringBuilder.append(", " +  deltaTime);
        final String stringToPutExtra = stringBuilder.toString();

        TextView loadingTxt = (TextView) findViewById(R.id.loadingTxt);
        String replaceTxt = "Loading level " + levelNo[0] + ". . .";
        loadingTxt.setText(replaceTxt);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toLevel = new Intent(getApplicationContext(), Predictable0.class);
                toLevel.putExtra(levelNo[0] + "", stringToPutExtra);
                startActivity(toLevel);
                // urmeaza switch case pentru fiecare nivel (urmeaza sa fac in viitor)
                // prin urmare, aici se vor genera hartile (nivelurile) care apoi se vor transmite cu putExtra catre activitatile de gameplay.
            }
        }, 500);
    }

    private void generateLevelTrajectory(int levelNo) {
        switch (levelNo) {
            case 1:
                trajectory = _CubeStaticSmall.main();
                deltaTime = 750;
                break;
            case 2:
                trajectory = _OrderRows.main();
                deltaTime = 750;
                break;
            case 3:
                trajectory = _CircularSpiral.main();
                deltaTime = 250;
                break;
            case 4:
                trajectory = _OrderRows.main();
                deltaTime = 250;
                break;
            case 5:
                trajectory = _DiagonalFlowy.main();
                deltaTime = 500;
                break;
            case 6:
                trajectory = _DiagonalFlowy.main();
                deltaTime = 250;
                break;
            case 7:
                trajectory = _OrderRows.main();
                deltaTime = 175;
                break;
            case 8:
                trajectory = _CircularSpiral.main();
                deltaTime = 175;
                break;
            case 9:
                trajectory = _DiagonalFlowy.main();
                deltaTime = 175;
                break;
            case 10:
                trajectory = _OrderRows.main();
                deltaTime = 100;
                break;
            case 11:
                trajectory = _CircularSpiral.main();
                deltaTime = 100;
                break;
            case 12:
                trajectory = _CircularMatchVertConstraint.main();
                deltaTime = 375;
                break;
            case 13:
                trajectory = _CircularMatchVertConstraint.main();
                deltaTime = 175;
                break;
            case 14:
                trajectory = _CircularMatchVertConstraint.main();
                deltaTime = 125;
                break;
            case 15:
                trajectory = _BasicSpiral.main();
                deltaTime = 250;
                break;
            case 16:
                trajectory = _BasicSpiral.main();
                deltaTime = 150;
                break;
            case 17:
                trajectory = _BasicSpiral.main();
                deltaTime = 100;
                break;
            case 18:
                trajectory = _BasicSpiralVertical.main();
                deltaTime = 125;
                break;
            case 19:
                trajectory = _BasicSpiralVertical.main();
                deltaTime = 75;
                break;
            case 20:
                trajectory = _AlwaysSteeringSpiral.main();
                deltaTime = 250;
                break;
            case 21:
                trajectory = _AlwaysSteeringSpiral.main();
                deltaTime = 175;
                break;
            case 22:
                trajectory = _AlwaysSteeringSpiral.main();
                deltaTime = 125;
                break;
            case 23:
                trajectory = _BrokenElipse.main();
                deltaTime = 250;
                break;
            case 24:
                trajectory = _BrokenElipse.main();
                deltaTime = 125;
                break;
            case 25:
                trajectory = _CubeStaticSmall.main();
                deltaTime = 250;
                break;
            case 26:
                trajectory = _CubeStaticSmall.main();
                deltaTime = 125;
                break;
            case 27:
                trajectory = _CubeStaticSmall.main();
                deltaTime = 75;
                break;
            case 28:
                trajectory = _CubeStaticSmallTricky.main();
                deltaTime = 250;
                break;
            case 29:
                trajectory = _CubeStaticSmallTricky.main();
                deltaTime = 125;
                break;
            case 30:
                trajectory = _CubeStaticSmallTricky2.main();
                deltaTime = 50;
                break;
            case 31:
                trajectory = _RandomLinesHorizontal.main();
                deltaTime = 150;
                break;
            case 32:
                trajectory = _RandomLinesHorizontal.main();
                deltaTime = 75;
                break;
            case 33:
                trajectory = _RandomLinesAndSenseHorizontal.main();
                deltaTime = 125;
                break;
            case 34:
                trajectory = _RandomLinesAndSenseHorizontal.main();
                deltaTime = 75;
                break;
            case 35:
                trajectory = _RandomLinesAndSense.main();
                deltaTime = 125;
                break;
            case 36:
                trajectory = _RandomLinesAndSense.main();
                deltaTime = 100;
                break;
            case 37:
                trajectory = _RandomLinesVertical.main();
                deltaTime = 100;
                break;
            case 38:
                trajectory = _RandomLinesAndSenseVertical.main();
                deltaTime = 100;
                break;
            case 39:
                trajectory = _HorizontalOrderedHalfLines.main();
                deltaTime = 150;
                break;
            case 40:
                trajectory = _HorizontalUnorderedHalfLines.main(0);
                deltaTime = 200;
                break;
            case 41:
                trajectory = _HorizontalUnorderedHalfLines.main(1);
                deltaTime = 175;
                break;
            case 42:
                trajectory = _Horizontal2BlockWide.main(0);
                deltaTime = 500;
                break;
            case 43:
                trajectory = _Horizontal2BlockWide.main(0);
                deltaTime = 375;
                break;
            case 44:
                trajectory = _RandomPoints.main(0);
                deltaTime = 675;
                break;
            case 45:
                trajectory = _RandomPoints.main(0);
                deltaTime = 475;
                break;
            default:
                trajectory = null;
                break;
        }
    }
}
