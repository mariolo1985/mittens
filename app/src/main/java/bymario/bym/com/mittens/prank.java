package bymario.bym.com.mittens;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class prank extends AppCompatActivity {

    private CameraController _cameraController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.prank_layout);

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

        Button btnStartPrank = (Button) findViewById(R.id.btn_startPrank);
        btnStartPrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = (View) v.getParent();// PARENT VIEW NEEDED (v = btn)
                v.setVisibility(View.GONE);// HIDE THIS BUTTON

                // LOAD ANIMATION
                final ImageView img = (ImageView) parent.findViewById(R.id.imgView_prank);
                img.setBackgroundResource(R.drawable.anime_prank);
                // START ANIMATION
                AnimationDrawable animeStartPrank = (AnimationDrawable) img.getBackground();
                animeStartPrank.start();

                // SHOW HELPER TEXT
                TextView tvJump = (TextView) parent.findViewById(R.id.txtViewJump);
                tvJump.setVisibility(View.VISIBLE);

                // SHOW MNAUL PIC BTN
                final Button btnManualPic = (Button) parent.findViewById(R.id.btn_manualPic);
                btnManualPic.setVisibility(View.VISIBLE);
                parent.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Log.e("MITTENS", "SCREEN TOUCHED");
                        TextView tvJump = (TextView) v.findViewById(R.id.txtViewJump);
                        tvJump.setVisibility(View.GONE);

                        btnManualPic.setVisibility(View.GONE);
                        img.setBackgroundResource(R.drawable.anime_prank_2);

                        AnimationDrawable animePrank2 = (AnimationDrawable) img.getBackground();
                        animePrank2.start();

                        //takePicture();
                        // TO DO - REMVE EVENT LISTENER
                        return true;
                    }
                });
            }// end on click
        });
    }

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
