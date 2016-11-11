package bymario.bym.com.mittens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class PlayerTitle extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_playertitle);

        Button btnPlay = (Button) findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerInstr = new Intent(PlayerTitle.this, PlayerInstructions.class);
                startActivity(playerInstr);
            }
        });
    } // end oncreate

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }// end backpress
}
