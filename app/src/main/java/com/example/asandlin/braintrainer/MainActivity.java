package com.example.asandlin.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends ActionBarActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Boolean firstGame = Boolean.TRUE;

    TextView equationText;
    TextView timeText;
    TextView resultTextView;
    TextView scoreText;
    Button choice1Button;
    Button choice2Button;
    Button choice3Button;
    Button choice4Button;
    Button playButton;



    public void startGame (View view) {

        if (firstGame)  {

            Button goButton = (Button) findViewById(R.id.goButton);

            goButton.setVisibility(View.INVISIBLE);
            timeText.setVisibility(View.VISIBLE);
            scoreText.setVisibility(View.VISIBLE);
            equationText.setVisibility(View.VISIBLE);
            choice1Button.setVisibility(View.VISIBLE);
            choice2Button.setVisibility(View.VISIBLE);
            choice3Button.setVisibility(View.VISIBLE);
            choice4Button.setVisibility(View.VISIBLE);

            firstGame = false;

        }

        playButton.setVisibility(View.INVISIBLE);

        resultTextView.setText("");
        scoreText.setText("0/0");
        numberOfQuestions = 0;
        score = 0;

        choice1Button.setClickable(true);
        choice2Button.setClickable(true);
        choice3Button.setClickable(true);
        choice4Button.setClickable(true);

        new CountDownTimer(31000, 1000) {

            public void onTick(long millisecondsUntilDone) {

                //Method run when counter is counting down (every second)

                String secondString = Integer.toString((int) (millisecondsUntilDone / 1000));

                if (millisecondsUntilDone / 1000 <= 9) {

                    secondString = "0" + secondString;
                }

                if (secondString == "31") {

                    secondString = "30";

                }

                timeText.setText(secondString + "s");

                Log.i("Seconds left:", String.valueOf(millisecondsUntilDone / 1000));

            }

            public void onFinish() {

                //Method run when counter is finished (after 10 seconds)

                Log.i("Countdown time finished", "woohoo!");

                timeText.setText(0 + "s");

                resultTextView.setText("Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

                playButton.setVisibility(View.VISIBLE);

                choice1Button.setClickable(false);
                choice2Button.setClickable(false);
                choice3Button.setClickable(false);
                choice4Button.setClickable(false);

            }
        }.start();


        generateQuestion();

    }

    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        equationText.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i=0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);

            }else{

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        choice1Button.setText(Integer.toString(answers.get(0)));
        choice2Button.setText(Integer.toString(answers.get(1)));
        choice3Button.setText(Integer.toString(answers.get(2)));
        choice4Button.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer (View view) {

            TextView scoreText = (TextView) findViewById(R.id.scoreText);

            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

                score++;
                resultTextView.setText("Correct!");
                Log.i("Score is: ", Integer.toString(score));

            }else{

                resultTextView.setText("Wrong");
            }

        numberOfQuestions++;
        scoreText.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (Button) findViewById(R.id.playButton);
        equationText = (TextView) findViewById(R.id.equationText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        timeText = (TextView) findViewById(R.id.timeText);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        choice1Button = (Button) findViewById(R.id.choice1Button);
        choice2Button = (Button) findViewById(R.id.choice2Button);
        choice3Button = (Button) findViewById(R.id.choice3Button);
        choice4Button = (Button) findViewById(R.id.choice4Button);

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
}
