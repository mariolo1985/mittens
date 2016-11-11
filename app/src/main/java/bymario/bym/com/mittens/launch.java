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


        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        Intent instrScreen = new Intent(launch.this, PranksterInstructions.class);
                        startActivity(instrScreen);
                    }
                }
                , 2000);
    }
}
