package com.example.cczhr.geoquiz;

/**
 * Created by cczhr on 2016/7/9.
 */
public class Question {
    private  int mTextResId;
    private  boolean mAnswerTrue;
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }


    public  Question(int mTextResId,boolean mAnswerTrue){
        this.mAnswerTrue=mAnswerTrue;
        this.mTextResId=mTextResId;
    }


}
