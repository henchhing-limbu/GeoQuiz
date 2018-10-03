package com.techexchange.mobileapps.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
    private String question;
    private String correctAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;
    private int index;

    public Question() { }

    public Question(String question, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, int index) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.index = index;
    }
    public String getQuestion() {
        return question;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public String getWrongAnswer1() {
        return wrongAnswer1;
    }
    public String getWrongAnswer2() {
        return wrongAnswer2;
    }
    public String getWrongAnswer3() {
        return wrongAnswer3;
    }
    public int getIndex() { return index; }

    private Question(Parcel in) {
        question = in.readString();
        correctAnswer = in.readString();
        wrongAnswer1 = in.readString();
        wrongAnswer2 = in.readString();
        wrongAnswer3 = in.readString();
        index = in.readInt();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.question);
        dest.writeString(this.correctAnswer);
        dest.writeString(this.wrongAnswer1);
        dest.writeString(this.wrongAnswer2);
        dest.writeString(this.wrongAnswer3);
        dest.writeInt(this.index);
    }
}
