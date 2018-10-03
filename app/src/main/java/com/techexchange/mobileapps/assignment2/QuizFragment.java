package com.techexchange.mobileapps.assignment2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment implements QuizAdapter.ItemClickListener{
// public class QuizFragment extends Fragment {
    private static final String TAG = QuizFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private int questionIndex;
    private ArrayList<Question> questionList;
    private onQuestionClickedListener questionClickedListener;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            questionClickedListener = (onQuestionClickedListener) context;

        } catch (ClassCastException ex) {
            throw new ClassCastException(
                    "The Context did not implement OnQuestionClickedListener!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);
        recyclerView = rootView.findViewById(R.id.questions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle args = getArguments();
        if (args != null) {
            questionList = args.getParcelableArrayList(MainActivity.QUESTION_LIST);
        }
        adapter = new QuizAdapter(this, questionList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        // TODO: Call SingleQuestionFragment
        questionIndex = position;
    }

    interface onQuestionClickedListener {
        void onQuestionClicked(int position);
    }

}
