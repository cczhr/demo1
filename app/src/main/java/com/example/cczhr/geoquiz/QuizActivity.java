package com.example.cczhr.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTureButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrveButton;
    private TextView mQuestionTextView;


    private  int mCurrentIndex=0;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT=0;
    private boolean mIsCheater;




    //封装函数
    private void updateQuestion(){
        int question=mQuestionBank[mCurrentIndex].getTextResId();//mCurrentIndex数组顺序getTextResId()返回字符串的资源id
        mQuestionTextView.setText(question);//显示内容
    }

    private  void  checkAnswer(boolean userPressedTure){
        boolean answerIsTure=mQuestionBank[mCurrentIndex].isAnswerTrue();//返回对错
        int messageResId=0;
        if(mIsCheater){
            messageResId=R.string.judgment_toast;
        }
        else {


            if (userPressedTure == answerIsTure) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show();//this的意思是在内部类显示
    }
    private  Question[] mQuestionBank= new  Question[]{
            new Question(R.string.question_oceans,true),//R.string.question_oceans是字符串的资源id
            new Question(R.string.question_africa,true),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,false),


    };


//启动
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle)called");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);

        mTureButton= (Button) findViewById(R.id.true_button);
        mFalseButton= (Button) findViewById(R.id.false_button);
        mCheatButton=(Button) findViewById(R.id.cheat_button);

        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mPrveButton=(ImageButton)findViewById(R.id.prev_button);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);//0是默认值
        }
        updateQuestion();




        //Ture的按钮
        mTureButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
              //  Toast.makeText(QuizActivity.this,R.string.incorrect_toast,Toast.LENGTH_LONG).show();//Toast.makeText提示框参数（字符id,显示时间）
                checkAnswer(true);
            }
        });

        //False的按钮
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               // Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_LONG).show();
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View v){
                if(mCurrentIndex<mQuestionBank.length-1) {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;//数组0123循环
                    mIsCheater=false;
                    updateQuestion();
                }
            }
        });
        mPrveButton.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View v){
                if(mCurrentIndex>0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;//数组0123循环
                    updateQuestion();
                }
            }
        });

        //cheat按钮偷看
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View v){
               // Intent i=new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();//当前题目的boolean
                Intent i=CheatActivity.newIntent (QuizActivity.this,answerIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);//这里采用startActivityForResult来做跳转，此处的REQUEST_CODE_CHEAT为一个依据，可以写其他的值，但一定要>=0
               // startActivity(i);
            }
        });
    }
        protected void onActivityResult (int requestCode,int resultCode,Intent data){
            if (resultCode!= Activity.RESULT_OK){
                return;
            }
            if(requestCode==REQUEST_CODE_CHEAT){
                if(data==null){
                    return;
                }
                mIsCheater=CheatActivity.wasAnswerShown(data);
            }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,mCurrentIndex+"");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
    }




