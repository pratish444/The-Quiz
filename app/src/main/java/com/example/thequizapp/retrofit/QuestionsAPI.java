package com.example.thequizapp.retrofit;

import com.example.thequizapp.model.QuestionList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionsAPI {

    @GET("my_Quiz_api.php")
    Call<QuestionList> getQuestions();
}
