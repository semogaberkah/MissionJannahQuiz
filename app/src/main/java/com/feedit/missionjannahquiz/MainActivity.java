package com.feedit.missionjannahquiz;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.feedit.missionjannahquiz.Loading.LoadingTaskFinishedListener;
import com.feedit.missionjannahquiz.entity.QuizEntity;
import com.feedit.missionjannahquiz.helper.QuizHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends Activity implements LoadingTaskFinishedListener{

    private QuizHelper quizHelper;
    private QuizEntity quizEntity;
    private List<QuizEntity> listQuiz;

    private ProgressBar progressBarTimer;
    private ProgressBar progressBarMisi;

    private int life=3;
    private int misi=0;

    private int totalQuestion=0;
    private int randomQuestion = 0;

    private Button btnQuestion,btnOptA,btnOptB,btnOptC,btnOptD,btnLife1,btnLife2,btnLife3;

    MediaPlayer mediaPlayerCorrect;
    MediaPlayer mediaPlayerWrong;

    int []numbers;

    TableLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        // init component
        progressBarTimer = (ProgressBar) findViewById(R.id.progressBarTimer);
        progressBarMisi = (ProgressBar) findViewById(R.id.progressBarMisi);
        btnQuestion = (Button) findViewById(R.id.btnQuestion);
        btnOptA = (Button) findViewById(R.id.btnOptionA);
        btnOptB = (Button) findViewById(R.id.btnOptionB);
        btnOptC = (Button) findViewById(R.id.btnOptionC);
        btnOptD = (Button) findViewById(R.id.btnOptionD);
        btnLife1 = (Button) findViewById(R.id.btnLife1);
        btnLife2 = (Button) findViewById(R.id.btnLife2);
        btnLife3 = (Button) findViewById(R.id.btnLife3);
        mediaPlayerCorrect = MediaPlayer.create(MainActivity.this,R.raw.correct);
        mediaPlayerWrong = MediaPlayer.create(MainActivity.this,R.raw.wrong);

        //show quiz
        //set question
        setRandomQuestion();
        setQuizView();

        progressBarMisi.setProgress(misi);
        // timer

        btnOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnQuestion.setEnabled(true);
                Loading.isStart = false;

                final String answer = btnOptA.getText().toString();
                if (quizEntity.getAnswer().equals(answer)) {
                    btnOptA.setBackgroundResource(R.drawable.answer_true);
                    mediaPlayerCorrect.start();
                } else {
                    btnOptA.setBackgroundResource(R.drawable.answer_false);
                    mediaPlayerWrong.start();

                    if(quizEntity.getAnswer().equals(btnOptB.getText().toString())){
                        btnOptB.setBackgroundResource(R.drawable.answer_true);
                    }else if(quizEntity.getAnswer().equals(btnOptC.getText().toString())){
                        btnOptC.setBackgroundResource(R.drawable.answer_true);
                    }else if(quizEntity.getAnswer().equals(btnOptD.getText().toString())){
                        btnOptD.setBackgroundResource(R.drawable.answer_true);
                    }
                }

                getQuizAnswer(answer);
            }
        });

        btnOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnQuestion.setEnabled(true);
                Loading.isStart = false;

                final String answer = btnOptB.getText().toString();
                if(quizEntity.getAnswer().equals(answer)){
                    btnOptB.setBackgroundResource(R.drawable.answer_true);
                    mediaPlayerCorrect.start();
                }else{
                    btnOptB.setBackgroundResource(R.drawable.answer_false);
                    mediaPlayerWrong.start();

                    if(quizEntity.getAnswer().equals(btnOptA.getText().toString())){
                        btnOptA.setBackgroundResource(R.drawable.answer_true);
                    }else if(quizEntity.getAnswer().equals(btnOptC.getText().toString())){
                        btnOptC.setBackgroundResource(R.drawable.answer_true);
                    }else if(quizEntity.getAnswer().equals(btnOptD.getText().toString())){
                        btnOptD.setBackgroundResource(R.drawable.answer_true);
                    }

                }

                getQuizAnswer(answer);
            }
        });

        btnOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnQuestion.setEnabled(true);
                Loading.isStart = false;

                final String answer = btnOptC.getText().toString();
                if(quizEntity.getAnswer().equals(answer)){
                    btnOptC.setBackgroundResource(R.drawable.answer_true);
                    mediaPlayerCorrect.start();
                }else{
                    btnOptC.setBackgroundResource(R.drawable.answer_false);
                    mediaPlayerWrong.start();

                    if(quizEntity.getAnswer().equals(btnOptA.getText().toString())){
                        btnOptA.setBackgroundResource(R.drawable.answer_true);
                    }else if(quizEntity.getAnswer().equals(btnOptB.getText().toString())){
                        btnOptB.setBackgroundResource(R.drawable.answer_true);
                    }else if(quizEntity.getAnswer().equals(btnOptD.getText().toString())){
                        btnOptD.setBackgroundResource(R.drawable.answer_true);
                    }

                }

                getQuizAnswer(answer);

            }
        });

        btnOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnQuestion.setEnabled(true);
                Loading.isStart = false;

                final String answer = btnOptD.getText().toString();
                if(quizEntity.getAnswer().equals(answer)){
                    btnOptD.setBackgroundResource(R.drawable.answer_true);
                    mediaPlayerCorrect.start();
                }else{
                    btnOptD.setBackgroundResource(R.drawable.answer_false);
                    mediaPlayerWrong.start();

                    if(quizEntity.getAnswer().equals(btnOptA.getText().toString())){
                        btnOptA.setBackgroundResource(R.drawable.answer_true);
                    }else if(quizEntity.getAnswer().equals(btnOptB.getText().toString())){
                        btnOptB.setBackgroundResource(R.drawable.answer_true);
                    }else if(quizEntity.getAnswer().equals(btnOptC.getText().toString())){
                        btnOptC.setBackgroundResource(R.drawable.answer_true);
                    }

                }

                getQuizAnswer(answer);
            }
        });

        btnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                btnQuestion.setEnabled(false);
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            setQuizView();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTaskFinished() {
        // new activity layout
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    getQuizAnswer("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setRandomQuestion(){
        quizHelper = new QuizHelper(this);
        listQuiz = quizHelper.getAllQuiz();

        int size = listQuiz.size(); //size of list

        ArrayList<Integer> listRandom = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            listRandom.add(i);
        }

        int qid=0;
        numbers = new int[size];

        Random rand = new Random();
        while (listRandom.size() > 0) {
            int index = rand.nextInt(listRandom.size());
            numbers[qid] = listRandom.remove(index);
            qid++;
        }

    }

    private void setQuizView() {
        quizHelper = new QuizHelper(this);
        listQuiz = quizHelper.getAllQuiz();
        quizEntity = listQuiz.get(numbers[randomQuestion]);

        //enabled button
        buttonUnLock();

        // default button color
        buttonDefaultColor();

        // the method which will put all things together
        btnQuestion.setText(quizEntity.getQuestion());
        btnOptA.setText(quizEntity.getOptionA());
        btnOptB.setText(quizEntity.getOptionB());
        btnOptC.setText(quizEntity.getOptionC());
        btnOptD.setText(quizEntity.getOptionD());

        // timer
        Loading.progressStatus=100;
        Loading.isStart = true;
        new Loading(progressBarTimer,this).execute("");


        randomQuestion++;
        totalQuestion++;
    }

    private void getQuizAnswer(String answerString) {
        if (quizEntity.getAnswer().equals(answerString)) {

            //button lock
            buttonLock();

            misi+=10;
            progressBarMisi.setProgress(misi);

            if (totalQuestion >= 5) {
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);

                            //Intent intent = new Intent(MainActivity.this, JannahActivity.class);
                            //startActivity(intent);
                           // finish();

                            disableLayout();
                            LayoutInflater inflater = getLayoutInflater();
                            getWindow().addContentView(inflater.inflate(R.layout.alert_layout, null),
                                    new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.FILL_PARENT,
                                           ViewGroup.LayoutParams.FILL_PARENT));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            //button answer cannot be selected

        }else{
            life-=1;

            //button answer cannot be selected
            buttonLock();

            switch (life){
                case 0:
                    btnLife1.setBackgroundResource(R.drawable.dead);
                    Intent intent = new Intent(MainActivity.this,MotivationActivity.class);
                    startActivity(intent);
                    finish();

                    break;
                case 1:
                    btnLife2.setBackgroundResource(R.drawable.dead);
                    break;
                case 2:
                    btnLife3.setBackgroundResource(R.drawable.dead);
                    break;
            }

        }


    }

    private void buttonLock(){
        btnOptA.setEnabled(false);
        btnOptB.setEnabled(false);
        btnOptC.setEnabled(false);
        btnOptD.setEnabled(false);
    }

    private void buttonUnLock(){
        btnOptA.setEnabled(true);
        btnOptB.setEnabled(true);
        btnOptC.setEnabled(true);
        btnOptD.setEnabled(true);
    }

    private void buttonDefaultColor(){
        btnOptA.setBackgroundResource(R.drawable.answer);
        btnOptB.setBackgroundResource(R.drawable.answer);
        btnOptC.setBackgroundResource(R.drawable.answer);
        btnOptD.setBackgroundResource(R.drawable.answer);
    }

    private void disableLayout(){
        mLayout = (TableLayout) findViewById(R.id.mainLayout);
        for(int i=0;i<mLayout.getChildCount();i++){
            View view = mLayout.getChildAt(i);
            view.setEnabled(false);
        }
    }

}
