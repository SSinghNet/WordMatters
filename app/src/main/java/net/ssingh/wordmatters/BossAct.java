package net.ssingh.wordmatters;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class BossAct extends AppCompatActivity {

    TextView textScreen, textQuestion, textTitle, textBtn;
    ImageView bigboss;
    Animation smalltobig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);

        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");

        textQuestion = findViewById(R.id.textQuestion);
        textScreen = findViewById(R.id.textScreen);
        textTitle = findViewById(R.id.textTitle);
        textBtn = findViewById(R.id.textBtn);

        bigboss = findViewById(R.id.bigboss);
        bigboss.startAnimation(smalltobig);


        textQuestion.setTypeface(typeface);
        textScreen.setTypeface(typeface);
        textTitle.setTypeface(typeface);
        textBtn.setTypeface(typeface);
    }

}
