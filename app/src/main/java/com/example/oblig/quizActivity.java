package com.example.oblig;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class quizActivity extends AppCompatActivity {

    ImgItem images[] = DataHolder.getInstance().getData();
    Random random = new Random();
    ImageView quizImage;
    EditText svar;
    int randomImg;
    int score = 0;
    int antSvar = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        quizImage = (ImageView) findViewById(R.id.quiz_image);
        final Button sjekkSvar = (Button) findViewById(R.id.btn_sendSvar);
        svar = (EditText) findViewById(R.id.inputQuiz);
        final Button tilbake = (Button) findViewById(R.id.btn_tilbake);
        final TextView riktignavn = (TextView) findViewById(R.id.riktignavnText);
        final TextView scoreText = (TextView) findViewById(R.id.scoreText);
        tilfeldigBilde();

        //sjekker svar, øker score om riktig.
        sjekkSvar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(svar.getText().toString().toUpperCase().equals(images[randomImg].getNavn().toUpperCase())) {
                    score++;
                    riktignavn.setText("Du svarte riktig!");
                } else {
                    riktignavn.setText("Du svarte feil, rett navn var " + images[randomImg].getNavn());
                }
                antSvar++;
                scoreText.setText("Score: " + score + "/" + antSvar);
                tilfeldigBilde();
            }
        });

        tilbake.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), seeAndDeleteActivity.class));
            }
        });
    }

    //Velger et tilfeldig bilde fra Images men ikke samme 2 ganger på rad.
    private void tilfeldigBilde() {
        int temp = randomImg;
        randomImg = random.nextInt(images.length);
        svar.setText("");
        if(images.length > 1) {
            while(temp == randomImg) {
                randomImg = random.nextInt(images.length);
            }
        }

        if(images[randomImg].getImgResId() == 0) {
            quizImage.setImageURI(images[randomImg].getUri());
        } else {
            quizImage.setImageResource(images[randomImg].getImgResId());
        }
    }
}
