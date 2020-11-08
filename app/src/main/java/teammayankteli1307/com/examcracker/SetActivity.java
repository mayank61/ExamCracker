package teammayankteli1307.com.examcracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class SetActivity extends AppCompatActivity {
Toolbar toolbar1;
GridView setsGrid;
ArrayList<String> list = new ArrayList();
TextView textsettype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        Intent intent = getIntent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();;
        setsGrid=findViewById(R.id.gridsets);

     String s =   intent.getStringExtra("cat");
    // toolbar1.setTitle(s);
        textsettype=findViewById(R.id.textView2);
        textsettype.setText(s);
        for(int i=1;i<28;i++)
        {
            list.add(String.valueOf(i));
        }
        SetsGridAdapter setsGridAdapter = new SetsGridAdapter(getApplicationContext(),list);


        setsGrid.setAdapter(setsGridAdapter);
        toolbar1=findViewById(R.id.toolbar1);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(),CategoryActivity.class);
                startActivity(intent1);
            }
        });

    }
}