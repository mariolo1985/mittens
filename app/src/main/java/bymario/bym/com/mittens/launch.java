package bymario.bym.com.mittens;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class launch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        // set splash
        ImageView imgViewSplash = (ImageView) findViewById(R.id.imgV_splash);
        imgViewSplash.setBackgroundResource(R.drawable.anime_splash);
        // ANIMATE SPLASH
        AnimationDrawable splashDrawable = (AnimationDrawable) imgViewSplash.getBackground();
        splashDrawable.start();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent instrScreen = new Intent(launch.this, instructions_screen.class);
                        startActivity(instrScreen);
                    }
                }
                , 2000);
    }
}
