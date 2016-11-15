package bymario.bym.com.mittens;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by MLo on 11/15/2016.
 */
public class PlayerScared extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_scared);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}
