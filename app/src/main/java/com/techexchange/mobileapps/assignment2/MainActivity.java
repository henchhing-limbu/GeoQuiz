package com.techexchange.mobileapps.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            getQuestionList();
        }
        catch (IOException e) {
            Log.d(TAG, "IOException: " + e.toString());
        }

    }

    private void getQuestionList() throws IOException {
        // TODO: Needs to take care of FileNotFoundException
        InputStream in = getResources().openRawResource(getResources().getIdentifier("country_list", "raw", getPackageName()));
        StringBuilder out = QuestionListFactory.readInputStream(in);

        questionList = QuestionListFactory.generateQuestionList(out);
    }
}
