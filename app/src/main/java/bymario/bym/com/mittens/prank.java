package bymario.bym.com.mittens;

import android.content.Intent;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ImageView;
import android.widget.RelativeLayout;


public class prank extends AppCompatActivity {

    private CameraController _cameraController;
    private int _prankScreenCount = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prank_layout);

        final Intent gameMusicService = new Intent(this, GameMusicService.class);
        final Intent scareMusicService = new Intent(this, ScareMusicService.class);
        final Intent PlayerScared = new Intent(this, PlayerScared.class);

        // LOAD ANIMATION
        final ImageView img = (ImageView) findViewById(R.id.imgView_prank);
        img.setBackgroundResource(R.drawable.anime_prank_2);
        // START ANIMATION
        AnimationDrawable animeStartPrank = (AnimationDrawable) img.getBackground();
        animeStartPrank.start();

        final RelativeLayout prankLayout = (RelativeLayout) findViewById(R.id.rel_prank);
        prankLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (_prankScreenCount) {
                    case 0:

                        img.setBackgroundResource(R.drawable.anime_prank_jump);

                        AnimationDrawable animePrank2 = (AnimationDrawable) img.getBackground();
                        animePrank2.start();

                        long dur = animePrank2.getNumberOfFrames() * 100;
                        Handler durHandler = new Handler();
                        durHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                img.setBackgroundResource(R.drawable.anime_prank_2);
                                AnimationDrawable animePrankJump = (AnimationDrawable) img.getBackground();
                                animePrankJump.start();
                                _prankScreenCount = 1;
                            }
                        }, dur);

                        break;
                    case 1:
                        img.setBackgroundResource(R.drawable.anime_prank_jump);

                        AnimationDrawable animePrankJump = (AnimationDrawable) img.getBackground();
                        animePrankJump.start();

                        long dur2 = animePrankJump.getNumberOfFrames() * 100;
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

                        long dur3 = animePrankJump2.getNumberOfFrames() * 100;
                        Handler durHandler3 = new Handler();
                        durHandler3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                stopService(gameMusicService);
                                startService(scareMusicService);

                                img.setImageResource(R.drawable.scare);
                                prankLayout.setBackgroundColor(getResources().getColor(R.color.colorBg));
                                takePicture();
                                _prankScreenCount = 3;
                            }
                        }, dur3 - 100);
                        break;

                    case 3:
                        startActivity(PlayerScared);
                        break;

                    default:
                        break;
                }// end switch
            }
        });

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
        stopAllAudioService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _cameraController.releaseCamera();
        stopAllAudioService();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();// end on backpress
        stopAllAudioService();
    }

    // CAMERA HELPERS
    public void takePicture() {
        _cameraController.getCameraInstance();
        _cameraController.takePicture();
    }

    public void stopAllAudioService() {
        Intent gameMusicService = new Intent(this, GameMusicService.class);
        Intent scareMusicService = new Intent(this, ScareMusicService.class);
        stopService(gameMusicService);
        stopService(scareMusicService);
    }
}
