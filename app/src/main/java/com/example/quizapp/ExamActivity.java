package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener {

   @BindView(R.id.timer) TextView timer;
   @BindView(R.id.finish_exam) Button finish_exam_btn;
   @BindView(R.id.q1_answers_group) RadioGroup questionOneGroup;
   @BindView(R.id.q2_answers_group) RadioGroup questionTwoeGroup;
   @BindView(R.id.q3_answers_group) RadioGroup questionThreeGroup;
   @BindView(R.id.q4_answers_group) RadioGroup questionFourGroup;
   private int studentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        finish_exam_btn.setOnClickListener(this);

        //this block of code to handle the timer for exam
        //every 1000 milli second = 1 second
        new CountDownTimer(180000, 1000) {

            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                timer.setText("Remaining: " + TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) +":"+ TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)%60);
            }

            public void onFinish() {
                finishExam();
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.finish_exam){
            finishExam();
        }
    }

    public void finishExam(){
        if(questionOneGroup.getCheckedRadioButtonId() == R.id.q1_a2_btn){
            studentScore++;
        }
        if(questionTwoeGroup.getCheckedRadioButtonId() == R.id.q2_a1_btn){
            studentScore++;
        }
        if(questionThreeGroup.getCheckedRadioButtonId() == R.id.q3_a3_btn){
            studentScore++;
        }
        if(questionFourGroup.getCheckedRadioButtonId() == R.id.q4_a1_btn){
            studentScore++;
        }
        Intent resultIntent = new Intent(this,ResultActivity.class);
        resultIntent.putExtra("studentName",getIntent().getExtras().getString("studentName"));
        resultIntent.putExtra("studentID",getIntent().getExtras().getString("studentID"));
        resultIntent.putExtra("studentScore",studentScore);
        startActivity(resultIntent);
        finish();
    }
}