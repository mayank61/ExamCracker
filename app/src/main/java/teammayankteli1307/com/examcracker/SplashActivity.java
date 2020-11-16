package teammayankteli1307.com.examcracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {
    public static  List<String> Catlist = new ArrayList<>();
    private FirebaseFirestore firebaseFirestore ;
TextView appname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar = getSupportActionBar();
      //  actionBar.hide();


        appname = findViewById(R.id.appname);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.animation);
        appname.setAnimation(anim);
       // FirebaseApp.initializeApp(getApplicationContext());
        firebaseFirestore=FirebaseFirestore.getInstance();
        new Thread(new Runnable() {
            @Override
            public void run() {

                loadData();
              //  Intent intent  = new Intent(getApplicationContext(),MainActivity.class);
             //   SplashActivity.this.finish();
              //  startActivity(intent);

            }


        }).start();
    }

    private void loadData() {
        firebaseFirestore.collection("Quiz").document("Categories").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot dc = task.getResult();
                    if (dc.exists()) {
                        long count = (long) dc.get("number");
                        for(int i=1;i<=5;i++)
                        {
                            String s =(String) dc.get("Cat"+String.valueOf(i));
                            Catlist.add(s);
                        }
                        Intent intent  = new Intent(getApplicationContext(),MainActivity.class);
                        SplashActivity.this.finish();
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Dc not exists",Toast.LENGTH_SHORT).show();
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