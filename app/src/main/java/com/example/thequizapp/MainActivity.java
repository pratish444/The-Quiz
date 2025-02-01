package com.example.thequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.thequizapp.databinding.ActivityMainBinding;
import com.example.thequizapp.model.Question;
import com.example.thequizapp.model.QuestionList;
import com.example.thequizapp.viewmodel.QuizViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    QuizViewModel quizViewModel;
    List<Question> questionList;
    int currentIndex = 0;

    static int result = 0;
    static int totalQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        result = 0;
        totalQuestions = 0;

        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        loadQuestions();

        binding.btnNext.setOnClickListener(view -> handleNextQuestion());
    }

    private void loadQuestions() {
        quizViewModel.getQuestionListLiveData().observe(this, new Observer<QuestionList>() {
            @Override
            public void onChanged(QuestionList questions) {
                if (questions != null && !questions.isEmpty()) {
                    questionList = questions;
                    totalQuestions = questionList.size();
                    displayQuestion(0);
                }
            }
        });
    }

    private void displayQuestion(int index) {
        if (index < questionList.size()) {
            Question q = questionList.get(index);
            binding.txtQuestion.setText("Question " + (index + 1) + ": " + q.getQuestion());
            binding.radio1.setText(q.getOption1());
            binding.radio2.setText(q.getOption2());
            binding.radio3.setText(q.getOption3());
            binding.radio4.setText(q.getOption4());
            binding.radioGroup.clearCheck();

            if (index == questionList.size() - 1) {
                binding.btnNext.setText("Finish");
            } else {
                binding.btnNext.setText("Next");
            }
        }
    }

    private void handleNextQuestion() {
        int selectedOptionId = binding.radioGroup.getCheckedRadioButtonId();
        if (selectedOptionId == -1) {
            Toast.makeText(this, "Please select an answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedOptionId);
        if (selectedRadioButton.getText().toString().equals(questionList.get(currentIndex).getCorrectOption())) {
            result++;
        }

        if (currentIndex < totalQuestions - 1) {
            currentIndex++;
            displayQuestion(currentIndex);
        } else {
            Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
