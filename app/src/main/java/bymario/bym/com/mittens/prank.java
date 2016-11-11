package bymario.bym.com.mittens;

import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class prank extends AppCompatActivity {

    private CameraController _cameraController;
    private int _prankScreenCount = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prank_layout);

        /*
        // MANUAL PIC BUTTON
        Button btnManualPic = (Button) findViewById(R.id.btn_manualPic);
        btnManualPic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                takePicture();
                v.setVisibility(View.GONE);
                return true;
            }
        });
*/
        // LOAD ANIMATION
        final ImageView img = (ImageView) findViewById(R.id.imgView_prank);
        img.setBackgroundResource(R.drawable.anime_prank);
        // START ANIMATION
        AnimationDrawable animeStartPrank = (AnimationDrawable) img.getBackground();
        animeStartPrank.start();

        final RelativeLayout prankLayout = (RelativeLayout) findViewById(R.id.rel_prank);
        prankLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (_prankScreenCount) {
                    case 0:

                        img.setBackgroundResource(R.drawable.anime_prank_2);

                        AnimationDrawable animePrank2 = (AnimationDrawable) img.getBackground();
                        animePrank2.start();

                        long dur = animePrank2.getNumberOfFrames() * 200;
                        Handler durHandler = new Handler();
                        durHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                _prankScreenCount = 1;
                            }
                        }, dur);

                        break;
                    case 1:
                        img.setBackgroundResource(R.drawable.anime_prank_jump);

                        AnimationDrawable animePrankJump = (AnimationDrawable) img.getBackground();
                        animePrankJump.start();

                        long dur2 = animePrankJump.getNumberOfFrames() * 200;
                        Handler durHandler2 = new Handler();
                        durHandler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                img.setBackgroundResource(R.drawable.anime_prank_2);
                                AnimationDrawable animePrankWalk = (AnimationDrawable) img.getBackground();
                                animePrankWalk.start();
                                _prankScreenCount = 2;
                            }
                        }, dur2);

                        break;

                    case 2:
                        img.setBackgroundResource(R.drawable.anime_prank_jump);

                        AnimationDrawable animePrankJump2 = (AnimationDrawable) img.getBackground();
                        animePrankJump2.start();

                        long dur3 = animePrankJump2.getNumberOfFrames() * 200;
                        Handler durHandler3 = new Handler();
                        durHandler3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //img.setBackground(getResources().getDrawable(R.drawable.scare));
                                //prankLayout.setBackgroundColor(getResources().getColor(R.color.colorBg));
                            }
                        }, dur3);
                        break;

                    case 3:

                        break;

                    default:
                        break;
                }

                //takePicture();
                // TO DO - REMVE EVENT LISTENER

                return true;
            }
        });// end on touch listner
    }// end on create


    @Override
    protected void onStart() {
        super.onStart();
        _cameraController = new CameraController(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        _cameraController.releaseCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _cameraController.releaseCamera();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();// end on backpress
    }

    // CAMERA HELPERS
    public void takePicture() {
        _cameraController.getCameraInstance();
        _cameraController.takePicture();
    }
}
