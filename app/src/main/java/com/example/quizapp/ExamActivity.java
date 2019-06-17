package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
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
   @BindView(R.id.q5_a1_btn) CheckBox questionFiveA1;
   @BindView(R.id.q5_a2_btn) CheckBox questionFiveA2;
   @BindView(R.id.q5_a3_btn) CheckBox questionFiveA3;
   @BindView(R.id.q5_a4_btn) CheckBox questionFiveA4;
   @BindView(R.id.q6_a1_btn) CheckBox questionSixA1;
   @BindView(R.id.q6_a2_btn) CheckBox questionSixA2;
   @BindView(R.id.q6_a3_btn) CheckBox questionSixA3;
   @BindView(R.id.q6_a4_btn) CheckBox questionSixA4;
   @BindView(R.id.q7_answer) EditText questionSevenAnswer_et;
   private int studentScore =0;

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
        if(questionFiveA2.isChecked()){
            studentScore++;
        }
        if(questionSixA3.isChecked()){
            studentScore++;
        }
        if(questionSevenAnswer_et.getText().toString().matches("no")){
            studentScore++;
        }

        int percentage = (Math.multiplyExact(studentScore, 100) / 7);

        if (percentage >= 50) {
            Toast.makeText(this,getString(R.string.studentName) + " " + getIntent().getStringExtra("studentName")+"\n"+
                    getString(R.string.studentID) + " " + getIntent().getStringExtra("studentID")+"\n"+
                    getString(R.string.studentScore_part1) + " " + studentScore + " " + getString(R.string.studentScore_part2)+"\n"+
                            getString(R.string.studentPercentage) + " " + percentage + "%"+"\n"+
                    getString(R.string.studentState_part1) + " " + getString(R.string.studentPass),Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,getString(R.string.studentName) + " " + getIntent().getStringExtra("studentName")+"\n"+
                    getString(R.string.studentID) + " " + getIntent().getStringExtra("studentID")+"\n"+
                    getString(R.string.studentScore_part1) + " " + studentScore + " " + getString(R.string.studentScore_part2)+"\n"+
                    getString(R.string.studentPercentage) + " " + percentage + "%"+"\n"+
                    getString(R.string.studentState_part1) + " " + getString(R.string.studentFail),Toast.LENGTH_LONG).show();
        }
        finish();
    }
}