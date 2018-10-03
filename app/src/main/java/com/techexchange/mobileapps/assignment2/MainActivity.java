package com.techexchange.mobileapps.assignment2;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.view.View;

public class MainActivity extends AppCompatActivity implements QuizFragment.onQuestionClickedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    static final String QUESTION_LIST = "question_list";
    private List<Question> questionList;

    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            questionList = getQuestionList();
        }
        catch (IOException e) {
            Log.d(TAG, "IOException: " + e.toString());
        }

        // creating quiz fragment
        Fragment quizFrag = new QuizFragment();
        fm = this.getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.main_activity, quizFrag);
        ft.commit();

        // getSupportFragmentManager().beginTransaction()
                // .replace(R.id.questions, quizFrag, quizFrag.getClass().getSimpleName());
        // .addToBackStack(null).commit();
        Bundle args = new Bundle();
        args.putParcelableArrayList(QUESTION_LIST, (ArrayList<? extends Parcelable>) questionList);
        quizFrag.setArguments(args);

        fm = getSupportFragmentManager();
    }

    private List<Question> getQuestionList() throws IOException {
        InputStream in = getResources().openRawResource(getResources().getIdentifier("country_list", "raw", getPackageName()));
        StringBuilder out = QuestionListFactory.readInputStream(in);
        return QuestionListFactory.generateQuestionList(out);
    }

    // TODO: We don't need adapter for SingleQuestionFragment
    /*
    private final class SingleQuestionFragmentAdapter extends FragmentStatePagerAdapter {
        public SingleQuestionFragmentAdapter(FragmentManager fm) {
            super(fm);
            Toast.makeText(getApplicationContext(), "Public constructor called", Toast.LENGTH_SHORT).show();
        }
        @Override
        public Fragment getItem(int position) {
            Question newQues = questionList.get(position);
            Toast.makeText(getApplicationContext(), "The quetion is :\n" + newQues.getQuestion(), Toast.LENGTH_SHORT).show();
            return SingleQuestionFragment.createFragmentFromQuestion(questionList.get(position));
        }

        @Override
        public int getCount() {
            return questionList.size();
        }
    }
    */

    @Override
    public void onQuestionClicked(int position) {
        // TODO: Call the single question fragment
        Log.d(TAG, "Creating single question fragment!");
        Fragment questionFrag = new SingleQuestionFragment();
    }
}
