package teammayankteli1307.com.examcracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
TextView tvscore;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tvscore=findViewById(R.id.scoretv);
        button=findViewById(R.id.buttondone);
        Intent intent = getIntent();
     String n  = intent.getStringExtra("SCORE");
        tvscore.setText(String.valueOf(n));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(),CategoryActivity.class);
             //   intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ScoreActivity.this.finish();
                startActivity(intent1);

            }
        });
    }
}