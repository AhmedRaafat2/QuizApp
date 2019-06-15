package com.example.quizapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.student_name) TextView studentName;
    @BindView(R.id.student_id) TextView studentID;
    @BindView(R.id.student_score) TextView studentScore;
    @BindView(R.id.student_percentage) TextView studentPercentage;
    @BindView(R.id.student_state) TextView studentState;
    @SuppressLint("SetTextI18n")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        studentName.setText(getString(R.string.studentName) + " " + getIntent().getStringExtra("studentName"));
        studentID.setText(getString(R.string.studentID) + " " + getIntent().getStringExtra("studentID"));

        final int SCORE = getIntent().getIntExtra("studentScore", 0);
        int percentage = (Math.multiplyExact(SCORE, 100) / 4);
        //Need Comment on this case please
        // i used here (int percentage = SCORE / 4 * 100;)
        //but i didn't work. and every time gives me that percentage =0
        studentScore.setText(getString(R.string.studentScore_part1) + " " + SCORE + " " + getString(R.string.studentScore_part2));
        studentPercentage.setText(getString(R.string.studentPercentage) + " " + percentage + "%");

        if (percentage >= 50) {
            studentState.setText(getString(R.string.studentState_part1) + " " + getString(R.string.studentPass));
        } else {
            studentState.setText(getString(R.string.studentState_part1) + " " + getString(R.string.studentFail));
        }
    }
}