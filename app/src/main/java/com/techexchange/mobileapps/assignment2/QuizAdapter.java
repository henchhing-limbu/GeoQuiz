package com.techexchange.mobileapps.assignment2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import java.util.List;
import android.view.View;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuesViewHolder> {
    private QuizFragment context;
    private List<Question> questionList;
    private ItemClickListener itemClickListener;

    public class QuesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView question, questionNo;

        public QuesViewHolder(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            questionNo = itemView.findViewById(R.id.question_no);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public QuizAdapter(QuizFragment context, List<Question> quesList) {
        this.context = context;
        this.questionList = quesList;
    }

    @Override
    public QuizAdapter.QuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView v = (CardView) inflater.inflate(R.layout.card_layout, parent, false);
        return new QuesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final QuesViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.question.setText(question.getQuestion());
        holder.questionNo.setText("Question #" + Integer.toString(question.getIndex()));
    }

    Question getItem(int id) {
        return questionList.get(id);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
    void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
