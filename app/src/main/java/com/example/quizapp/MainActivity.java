package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.name_et) EditText name_et;
    @BindView(R.id.id_et) EditText id_et;
    @BindView(R.id.iAgree_cb) CheckBox iAgree_cb;
    @BindView(R.id.startExam_btn) Button startExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iAgree_cb.setOnCheckedChangeListener(this);
        startExam.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == iAgree_cb.getId()) {
            if (iAgree_cb.isChecked()) {
                startExam.setVisibility(View.VISIBLE);
            } else {
                startExam.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == startExam.getId()) {
            startExam();
        }
    }

    public void startExam(){
        if (name_et.getText().toString().matches("") || id_et.getText().toString().matches("")) {
            Toast.makeText(this, R.string.toast_inCompleted_data, Toast.LENGTH_LONG).show();
        } else {
            Intent startExamIntent = new Intent(this, ExamActivity.class);
            startExamIntent.putExtra("studentName", name_et.getText().toString());
            startExamIntent.putExtra("studentID", id_et.getText().toString());
            startActivity(startExamIntent);
        }
    }
}