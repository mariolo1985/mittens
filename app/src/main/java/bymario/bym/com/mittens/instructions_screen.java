package bymario.bym.com.mittens;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class instructions_screen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("MITTENS","CREATE INSTRUCTION SCREEN");

        setContentView(R.layout.instruction_layout);

        Button btnInitPrank = (Button) findViewById(R.id.btn_initPrank);
        btnInitPrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInitPrank = new Intent(instructions_screen.this,prank.class);
                startActivity(intentInitPrank);
            }
        });

    }// end on create

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }// end onback press

}
