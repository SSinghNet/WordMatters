package net.ssingh.wordmatters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView textPlay, textTitle;
    ImageView bigboss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textPlay = findViewById(R.id.textPlay);
        textTitle = findViewById(R.id.textTitle);
        bigboss = findViewById(R.id.bigboss);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOne-Regular.ttf");

        textPlay.setTypeface(typeface);
        textTitle.setTypeface(typeface);

        Bundle b = getIntent().getExtras();
        int value = 0;
        if(b != null){
            value += b.getInt("level");
        }

        final int finalValue = value;
        textPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(Home.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putInt("level", finalValue); //Your id
                a.putExtras(b); //Put your id to your next Intent
                startActivity(a);
            }
        });
    }
}