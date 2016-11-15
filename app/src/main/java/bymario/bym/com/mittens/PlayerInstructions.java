package bymario.bym.com.mittens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlayerInstructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_playerinstructions);

        // START GAME MUSIC
        Intent gameMusicService = new Intent(this,GameMusicService.class);
        startService(gameMusicService);


        Button btnPlay = (Button) findViewById(R.id.btn_startGame);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent titleIntent = new Intent(PlayerInstructions.this, prank.class);
                startActivity(titleIntent);
            }
        });
    }// end on create

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }// end backpress
}
