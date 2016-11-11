package bymario.bym.com.mittens;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PranksterInstructions extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction_layout);

        Button btnInitPrank = (Button) findViewById(R.id.btn_initPrank);
        btnInitPrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playerTitle = new Intent(PranksterInstructions.this, PlayerTitle.class);
                startActivity(playerTitle);
            }
        });
    }// end oncreate

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }// end backpress
}
