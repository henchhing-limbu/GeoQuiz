package com.techexchange.mobileapps.assignment2;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.HashSet;

public class SingleQuestionFragment extends Fragment{
    private static final String TAG = SingleQuestionFragment.class.getSimpleName();

    private static final String ARG_QUESTION_TEXT = "ARG_QUESTION_TEXT";
    private static final String ARG_CORRECT_ANSWER = "ARG_CORRECT_ANSWER";
    private static final String ARG_WRONG_ANSWER1 = "ARG_WRONG_ANSWER1";
    private static final String ARG_WRONG_ANSWER2 = "ARG_WRONG_ANSWER2";
    private static final String ARG_WRONG_ANSWER3 = "ARG_WRONG_ANSWER3";
    private static final String ARG_SELECTED_ANSWER = "ARG_SELECTED_ANSWER";

    private TextView questionText;
    private RadioGroup radioGroup;
    private ArrayList<RadioButton> radioButtons;
    private Button submitButton;
    private int correctButtonId;
    private HashSet<String> answers;

    private onQuestionAnsweredListener answerListener;

    public SingleQuestionFragment() {
        // require empty constructor
    }

    static SingleQuestionFragment createFragmentFromQuestion(Question question) {
        Bundle fragArgs = new Bundle();
        fragArgs.putString(ARG_QUESTION_TEXT, question.getQuestion());
        fragArgs.putString(ARG_CORRECT_ANSWER, question.getCorrectAnswer());
        fragArgs.putString(ARG_WRONG_ANSWER1, question.getWrongAnswer1());
        fragArgs.putString(ARG_WRONG_ANSWER2, question.getWrongAnswer2());
        fragArgs.putString(ARG_WRONG_ANSWER3, question.getWrongAnswer3());

        SingleQuestionFragment frag = new SingleQuestionFragment();
        frag.setArguments(fragArgs);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.question_fragment_view, container, false);
        super.onCreate(savedInstanceState);

        radioGroup = rootView.findViewById(R.id.radio_group);
        questionText = rootView.findViewById(R.id.single_question);
        submitButton = rootView.findViewById(R.id.submit_ans);

        radioButtons = new ArrayList<>();
        radioButtons.add(rootView.findViewById(R.id.radio_one));
        radioButtons.add(rootView.findViewById(R.id.radio_two));
        radioButtons.add(rootView.findViewById(R.id.radio_three));
        radioButtons.add(rootView.findViewById(R.id.radio_four));

        submitButton.setOnClickListener(this::onSubmitButtonPressed);

        Bundle args = getArguments();
        if (args != null) {
            answers = new HashSet<>();
            answers.add(ARG_WRONG_ANSWER1);
            answers.add(ARG_CORRECT_ANSWER);
            answers.add(ARG_WRONG_ANSWER2);
            answers.add(ARG_WRONG_ANSWER3);

            questionText.setText(args.getString(ARG_QUESTION_TEXT));
            Iterator<String> ans = answers.iterator();
            int i = 0;
            while (ans.hasNext()) {
                if (ans.equals(ARG_CORRECT_ANSWER)) {
                    correctButtonId = radioButtons.get(i).getId();
                }
                radioButtons.get(i).setText(ans.toString());
                i++;
            }
        }
        return rootView;
    }

    private void onSubmitButtonPressed(View v) {
        Button selectedButton = (Button) v;
        int radioId = radioGroup.getCheckedRadioButtonId();
    }

    interface onQuestionAnsweredListener {
        void onQuestionAnswered(String selectedAnswer, int questionId);
    }


}
