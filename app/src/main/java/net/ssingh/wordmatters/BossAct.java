package net.ssingh.wordmatters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class BossAct extends AppCompatActivity {

    TextView textQuestion, textTitle, textBtn;
    ImageView bigboss;
    Animation smalltobig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOne-Regular.ttf");

        textQuestion = findViewById(R.id.textQuestion);
        textTitle = findViewById(R.id.textTitle);
        textBtn = findViewById(R.id.textBtn);

        bigboss = findViewById(R.id.bigboss);
        bigboss.startAnimation(smalltobig);

        textQuestion.setTypeface(typeface);
        textTitle.setTypeface(typeface);
        textBtn.setTypeface(typeface);

        Bundle b = getIntent().getExtras();
        int value = 1;
        if(b != null){
            value += b.getInt("currentLevel");
        }

        final int finalValue = value;
        textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BossAct.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putInt("level", finalValue); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                finish();
            }
        });

        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BossAct.this, Home.class);
                Bundle b = new Bundle();
                b.putInt("level", finalValue); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

    }

}
