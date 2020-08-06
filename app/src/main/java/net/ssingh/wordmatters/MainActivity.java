package net.ssingh.wordmatters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int presCounter = 0;
    private String[] keys;
    private String textAnswer = "BIRD";
    private int maxPresCounter = textAnswer.length();
    TextView textQuestion, textTitle, numLetters, levelNum;
    Animation smallbigforth;
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smallbigforth);
        numLetters = findViewById(R.id.numLetters);
        levelNum = findViewById(R.id.levelNum);
        textQuestion = findViewById(R.id.textQuestion);

        Bundle b = getIntent().getExtras();

        if(b != null){
            value = b.getInt("level");
        }

        switch(value){
            case 1:
                keys = new String[]{"T", "I", "G", "E", "R", "A"};
                textAnswer = "TIGER";
                textQuestion.setText("Category: Animals");
                break;
            case 2:
                keys = new String[]{"H", "O", "R", "S", "E", "L"};
                textAnswer = "HORSE";
                textQuestion.setText("Category: Animals");
                break;
            case 3:
                keys = new String[]{"E", "E", "L", "M", "U", "L"};
                textAnswer = "EMU";
                textQuestion.setText("Category: Animals");
                break;
            case 4:
                keys = new String[]{"N", "O", "L", "M", "I", "L"};
                textAnswer = "LION";
                textQuestion.setText("Category: Animals");
                break;
            case 5:
                keys = new String[]{"N", "B", "L", "M", "E", "U"};
                textAnswer = "BLUE";
                textQuestion.setText("Category: Colors");
                break;
            case 6:
                keys = new String[]{"R", "O", "N", "A", "G", "E"};
                textAnswer = "ORANGE";
                textQuestion.setText("Category: Colors");
                break;
            case 7:
                keys = new String[]{"P", "U", "R", "P", "L", "E"};
                textAnswer = "PURPLE";
                textQuestion.setText("Category: Colors");
                break;
            default:
                keys = new String[]{"B", "I", "R", "D", "X", "C"};
                textAnswer = "BIRD";
                textQuestion.setText("Category: Animals");
                break;
        }
        numLetters.setText(String.valueOf(textAnswer.length()));
        levelNum.setText("Level " + (value + 1));
        keys = shuffleArray(keys);


        for (String key : keys) {
            addView(((LinearLayout) findViewById(R.id.layoutParent)), key, ((EditText) findViewById(R.id.editText)));
        }
        maxPresCounter = textAnswer.length();

    }


    private String[] shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    private void addView(LinearLayout viewParent, final String text, final EditText editText) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayoutParams.rightMargin = 30;
        linearLayoutParams.height = getResources().getDimensionPixelSize(R.dimen.text_view_height);
        linearLayoutParams.width = getResources().getDimensionPixelSize(R.dimen.text_view_width);

        final TextView textView = new TextView(this);
        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(16);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOne-Regular.ttf");

        textQuestion = findViewById(R.id.textQuestion);
        textTitle = findViewById(R.id.textTitle);
        levelNum = findViewById(R.id.levelNum);

        levelNum.setTypeface(typeface);
        numLetters.setTypeface(typeface);
        textQuestion.setTypeface(typeface);
        textTitle.setTypeface(typeface);
        editText.setTypeface(typeface);
        textView.setTypeface(typeface);

        textView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(presCounter < maxPresCounter) {
                    if (presCounter == 0){
                        editText.setText("");
                    }

                    editText.setText(editText.getText().toString() + text);
                    textView.startAnimation(smallbigforth);
                    textView.animate().alpha(0).setDuration(300);
                    textView.setClickable(false);
                    presCounter++;

                    if (presCounter == maxPresCounter){
                        doValidate();
                    }
                }
            }
        });

        viewParent.addView(textView);
    }


    private void doValidate() {
        presCounter = 0;

        final EditText editText = findViewById(R.id.editText);
        LinearLayout linearLayout = findViewById(R.id.layoutParent);

        if(editText.getText().toString().equals(textAnswer)) {
//            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent a = new Intent(MainActivity.this,BossAct.class);
                    Bundle b = new Bundle();
                    b.putInt("currentLevel", value); //Your id
                    a.putExtras(b); //Put your id to your next Intent
                    startActivity(a);
                    finish();
                    editText.setText("");
                }
            }, 25);


        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                }
            }, 50);

        }

        keys = shuffleArray(keys);
        linearLayout.removeAllViews();
        for (String key : keys) {
            addView(linearLayout, key, editText);
        }
    }

}
