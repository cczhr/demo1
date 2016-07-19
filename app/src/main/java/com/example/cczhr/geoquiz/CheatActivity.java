package com.example.cczhr.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE="com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN="com.bignerdranch.android.geoquiz.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data =new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
    public static boolean wasAnswerShown(Intent result){
        return  result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }

    //接收QuizActivity的boolean对错
    public  static Intent newIntent (Context packageContext, boolean answerIsTrue){
        Intent i=new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);//(EXTRA_ANSWER_IS_TRUE 为String标签 保存
        return i; //返回i
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);//false只是默认值 改true也一样 getIntent()是获取由上一个Activity传递过来的Intent对象put是传这里的get是拿


        mAnswerTextView=(TextView)findViewById(R.id.answer_text_view);//显示答案的TextView

        mShowAnswer=(Button) findViewById(R.id.show_answer_button);//偷看按钮
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            //显示
            @Override
            public void onClick(View view) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.ture_button);
                }
                else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
    }
}
