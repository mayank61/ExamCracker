package teammayankteli1307.com.examcracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SetActivity extends AppCompatActivity {
    Toolbar toolbar1;
    GridView setsGrid;
    int id;
    ArrayList<Integer> list = new ArrayList();
    TextView textsettype;
   private FirebaseFirestore firebaseFirestore;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        Intent intent = getIntent();
        progressBar = findViewById(R.id.progreesbar);
        progressBar.setVisibility(View.VISIBLE);
        id = intent.getIntExtra("id", 1);
        setsGrid = findViewById(R.id.gridsets);
firebaseFirestore = FirebaseFirestore.getInstance();
        String s = intent.getStringExtra("cat");
        // toolbar1.setTitle(s);

        textsettype = findViewById(R.id.textView2);
        textsettype.setText(s);

        //    SetsGridAdapter setsGridAdapter = new SetsGridAdapter(getApplicationContext(),list);


        //  setsGrid.setAdapter(setsGridAdapter);
        toolbar1 = findViewById(R.id.toolbar1);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent1);
            }
        });
        setsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getApplicationContext(), QuestionActivity.class);
                intent1.putExtra("set", i);
                startActivity(intent1);
            }
        });
        loadData();

    }

    public void loadData() {
        firebaseFirestore.collection("Quiz").document("Cat"+id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot dc = task.getResult();
                   try {
                       if (dc.exists()) {
                           long count = (long) dc.get("Sets");
                           for(int i = 1;i<=count;i++)
                           {
                               list.add(i);
                           }

                           SetsGridAdapter setsGridAdapter = new SetsGridAdapter(getApplicationContext(),list);

                         progressBar.setVisibility(View.GONE);
                            setsGrid.setAdapter(setsGridAdapter);


                       }
                       else
                       {
                           Toast.makeText(getApplicationContext(),"Dc not exists",Toast.LENGTH_SHORT).show();
                       }

                   } catch (Exception e)
                   {
                       Log.d("cfsdgsg",e.getMessage());
                   }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"not Successful /n "+task.getException(),Toast.LENGTH_SHORT).show();
                    Log.e("NO success", "onComplete: ",task.getException() );
                }

            }


        });

    }
}