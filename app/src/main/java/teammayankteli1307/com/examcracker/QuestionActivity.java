package teammayankteli1307.com.examcracker;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    List<Question> questionList;

    TextView questiontext, counterText, numberqText;
    Button option1, option2, option3, option4;
    private int questionNumber = 0;
    CountDownTimer countDownTimer;

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
        getQuestion();

    }

    private void getQuestion() {
        questionList = new ArrayList<>();
        questionList.add(new Question("Question 1", "Opion A", "Option b", "opyion c ", "option d", 2));
        questionList.add(new Question("Question 2dfgdfgd", "Opion A", "Option b", "opyion c ", "option d", 1));
        questionList.add(new Question("Question Mayank Teli 3", "Opion A", "Option b", "opyion c ", "option d", 4));
        questionList.add(new Question("Question Nitt trichy 4", "Opion A", "Option b", "opyion c ", "option d", 2));
        questionList.add(new Question("Question ASD1", "Opion A", "Option b", "opyion c ", "option d", 1));
        questionList.add(new Question("Question 2", "Opion A", "Option b", "opyion c ", "option d", 2));
        questionList.add(new Question("Question 3", "Opion A", "Option b", "opyion c ", "option d", 2));
        questionList.add(new Question("Question 4", "Opion A", "Option b", "opyion c ", "option d", 3));
        questionList.add(new Question("Question 1", "Opion A", "Option b", "opyion c ", "option d", 2));
        questionList.add(new Question("Question 2", "Opion A", "Option b", "opyion c ", "option d", 3));
        questionList.add(new Question("Question 3", "Opion A", "Option b", "opyion c ", "option d", 2));
        questionList.add(new Question("Question 4", "Opion A", "Option b", "opyion c ", "option d", 3));
        setQuestion(questionNumber);

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
        if (questionNumber < questionList.size() - 1) {
            playanim(questiontext, 0, 0);
            playanim(option1, 0, 1);
            playanim(option2, 0, 2);
            playanim(option3, 0, 3);
            playanim(option4, 0, 4);
            numberqText.setText((questionNumber + 1) + "/" + questionList.size());
            startTimer();
        } else {
            Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
            QuestionActivity.this.finish();
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
}