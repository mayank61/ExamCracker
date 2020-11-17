package teammayankteli1307.com.examcracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    List<Question> questionList =   new ArrayList<>();;

    TextView questiontext, counterText, numberqText;
    Button option1, option2, option3, option4;
    private int questionNumber = 0;
    CountDownTimer countDownTimer;
    int score=0;
    private FirebaseFirestore  firebaseFirestore ;
    int setId = 0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        questiontext = findViewById(R.id.questiontext);
        counterText = findViewById(R.id.countertext);
        numberqText = findViewById(R.id.numverQuestion);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        firebaseFirestore =FirebaseFirestore.getInstance();
        Intent intent= getIntent();

     setId=   intent.getIntExtra("set", 0);
progressBar  = findViewById(R.id.progreesbar2);
progressBar.setVisibility(View.VISIBLE);

        getQuestion();

    }

    private void getQuestion() {

       firebaseFirestore.collection("Quiz").document("Cat"+SetActivity.id).collection("Set"+setId).get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if(task.isSuccessful())
                       {

                           QuerySnapshot question  =  task.getResult();
                           for(QueryDocumentSnapshot q : question)
                           {
                               questionList.add(new Question(q.getString("Question"),q.getString("Option1"),q.getString("Option2"),q.getString("Option3"),q.getString("Option4"),Integer.parseInt(q.getString("Answer"))));
                           }
                           progressBar.setVisibility(View.GONE);
                           setQuestion(questionNumber);
                       }
                       else {
                           Toast.makeText(getApplicationContext(),"Not Successful",Toast.LENGTH_SHORT).show();
                       }
                   }
               });


    }

    @SuppressLint("SetTextI18n")
    private void setQuestion(int a) {
        questiontext.setText(questionList.get(a).getQuestion());
        counterText.setText("30");
        numberqText.setText(a + 1 + "/" + questionList.size());
        option1.setText(questionList.get(a).getOption1());
        option2.setText(questionList.get(a).getOption2());
        option3.setText(questionList.get(a).getOption3());
        option4.setText(questionList.get(a).getOption4());
        startTimer();


    }

    private void startTimer() {


        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                counterText.setText(String.valueOf((int) (l / 1000)));


            }

            @Override
            public void onFinish() {

                changeQuestion();

            }
        }.start();

    }

    @Override
    public void onClick(View view) {
        int answer = 0;
        switch (view.getId()) {
            case R.id.option1: {
                answer = 1;

            }
            break;
            case R.id.option2: {
                answer = 2;

            }
            break;

            case R.id.option3: {
                answer = 3;

            }
            break;
            case R.id.option4: {
                answer = 4;
            }
            break;


        }countDownTimer.cancel();
        correctAnser(answer, view);



    }

    @SuppressLint("SetTextI18n")
    private void changeQuestion() {
        questionNumber++;
        if (questionNumber < questionList.size() ) {
            playanim(questiontext, 0, 0);
            playanim(option1, 0, 1);
            playanim(option2, 0, 2);
            playanim(option3, 0, 3);
            playanim(option4, 0, 4);
            numberqText.setText((questionNumber + 1) + "/" + questionList.size());
            startTimer();
        } else {
            Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);

            intent.putExtra("SCORE",score+"/"+questionList.size());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
           // QuestionActivity.this.finish();
            startActivity(intent);
        }
    }

    private void playanim(final View view, final int value, int viewnum) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(600).setStartDelay(150).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (value == 0) {
                    switch (viewnum) {
                        case 0:
                            ((TextView) view).setText(questionList.get(questionNumber).getQuestion());
                            break;

                        case 1:
                            ((Button) (view)).setText(questionList.get(questionNumber).getOption1());
                            break;
                        case 2:
                            ((Button) (view)).setText(questionList.get(questionNumber).getOption2());
                            break;
                        case 3:
                            ((Button) (view)).setText(questionList.get(questionNumber).getOption3());
                            break;
                        case 4:
                            ((Button) (view)).setText(questionList.get(questionNumber).getOption1());
                            break;

                    }
                    if(viewnum!=0)
                    {
                        ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                    }
                }
                playanim(view, 1, 0);


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }

    private void correctAnser(int answer, View view) {
        if (answer == questionList.get(questionNumber).getAnswer()) {
            score++;
            ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

        } else {

            ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            switch (questionList.get(questionNumber).getAnswer()) {
                case 1:
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 3:
                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 4:
                    option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;


            }

        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        },1500);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
    }
}