package teammayankteli1307.com.examcracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    GridView categoryView;
    Toolbar toolbar;
  private   int[] images = {R.drawable.icon, R.drawable.test, R.drawable.icon, R.drawable.test, R.drawable.icon, R.drawable.test, R.drawable.icon, R.drawable.test, R.drawable.icon, R.drawable.test,R.drawable.icon, R.drawable.test, R.drawable.icon, R.drawable.test, R.drawable.icon, R.drawable.test, R.drawable.icon, R.drawable.test, R.drawable.icon, R.drawable.test};
  private   String[] names = {"English", "Maths", "History", "Jee", "Neet", "IAS", "RAS", "UPpsc", "SSC", "geographhy","English", "Maths", "History", "Jee", "Neet", "IAS", "RAS", "UPpsc", "SSC", "geographhy"};
private List<Example> examples ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_example,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
toolbar=findViewById(R.id.toolbar);
toolbar.inflateMenu(R.menu.item_example);
      examples =   new ArrayList<>();
        ActionBar actionBar = getSupportActionBar();
       // actionBar.hide();
toolbar.setNavigationOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
Intent intent = new Intent(getApplicationContext(),MainActivity.class);
startActivity(intent);
    }
});
toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.item1)
        {
            Toast.makeText(getApplicationContext(),"Your toolbar working right",Toast.LENGTH_LONG).show();
        }
        return false;
    }
});

        categoryView = findViewById(R.id.gridCategory);
        for(int i=0;i<5;i++)
        {

          examples.add(new Example(SplashActivity.Catlist.get(i),images[i]));

        }
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),examples);
        categoryView.setAdapter(customAdapter);
        categoryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
Intent intent = new Intent(getApplicationContext(),SetActivity.class);
intent.putExtra("cat",SplashActivity.Catlist.get(i));
intent.putExtra("id",i+1);
startActivity(intent);

            }
        });

    }
}