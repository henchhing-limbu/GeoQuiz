package com.techexchange.mobileapps.assignment2;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class QuestionListFactory {
    private static final String TAG = QuestionListFactory.class.getSimpleName();

    public static StringBuilder readInputStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();
        return out;
    }

    public static List<Question> generateQuestionList(StringBuilder in) {
        List<String> inList = Arrays.asList(in.toString().split("\",\"|\"\""));
        Log.d(TAG, inList.toString());
        ArrayList<String> country = new ArrayList<>();
        HashMap<String, String> capitalToCountry = new HashMap<>();
        int x = inList.size()-1;
        int i = 3;
        while (i < x) {
            if (!(inList.get(i).equals("countryCapital"))) {
                if (!capitalToCountry.containsKey(inList.get(i))) {
                    capitalToCountry.put(inList.get(i+1), inList.get(i));
                    country.add(inList.get(i));
                }
                i++;
            }
            i++;
        }

        ArrayList<Question> questionList = new ArrayList<>();
        Random rand = new Random();
        for (String key: capitalToCountry.keySet()) {
            questionList.add(new Question(key + " is the capital of which country?", capitalToCountry.get(key),
                    country.get(rand.nextInt(247) + 0), country.get(rand.nextInt(247) + 0),
                    country.get(rand.nextInt(247) + 0)));
        }
        return questionList;
    }

}
