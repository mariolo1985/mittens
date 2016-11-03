package bymario.bym.com.mittens;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class suprise extends AppCompatActivity {

    private CameraController _cameraController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suprise);


        Button btnStartGame = (Button) findViewById(R.id.btn_startgame);
        btnStartGame.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String eventMsg = "[x: " + Float.toString(event.getX()) + " ] [y: " + Float.toString(event.getY()) + "]";
                Log.e("MITTENS", eventMsg);

                View parent = (View) v.getParent();
                Button tmpBtn = (Button) parent.findViewById(R.id.btn_startgame);
                tmpBtn.setVisibility(View.GONE);
                // load animation
                final ImageView img = (ImageView) parent.findViewById(R.id.img_animation);
                img.setBackgroundResource(R.drawable.animation_1);
                // get bg as animtation
                AnimationDrawable animation1 = (AnimationDrawable) img.getBackground();
                animation1.start();
//                while(animation1.isRunning()){
                //                  int duration = animation1.getDuration(0) * animation1.getNumberOfFrames();
                //            }
                TextView txtTip = (TextView) parent.findViewById(R.id.txtTip1);
                txtTip.setVisibility(View.VISIBLE);

                parent.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        String eventMsg = "PARENT TOUCH : [x: " + Float.toString(event.getX()) + " ] [y: " + Float.toString(event.getY()) + "]";
                        Log.e("MITTENS", eventMsg);

                        img.setBackgroundResource(R.drawable.animation_2);
                        AnimationDrawable animation2 = (AnimationDrawable) img.getBackground();
                        animation2.start();

                        takePicture();

                        return true;
                    }
                });

                return false;
            }

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
    protected void onPause() {
        super.onPause();
        _cameraController.releaseCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _cameraController.releaseCamera();
    }


    public void takePicture() {
        _cameraController.getCameraInstance();
        _cameraController.takePicture();
    }
}
