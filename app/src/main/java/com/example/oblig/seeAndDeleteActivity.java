package com.example.oblig;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;

import java.util.List;

public class seeAndDeleteActivity extends AppCompatActivity {

    //ImgItem images[] = DataHolder.getInstance().getData();

    List<ImgItem> items;
    ImgDatabase db;
    int imgCounter;
    int numItems;

    //setter startbilde til siste bilde i images slik at ved nye bilder vil det siste bilde vises først.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_and_delete);

        db = ImgDatabase.getInstance(this);
        items = db.imgdeo().getAllitems();
        imgCounter = items.size() -1;
        numItems = db.imgdeo().getAllitems().size();


        final EditText text1 = (EditText) findViewById(R.id.EditText01);
        final Button venstre = (Button) findViewById(R.id.btn_venstre);
        final Button hoyre = (Button) findViewById(R.id.btn_hoyre);
        final Button slett = (Button) findViewById(R.id.btn_slett);
        final Button startQuiz = (Button) findViewById(R.id.btn_startQuiz);
        final Button leggtilbilde = (Button) findViewById(R.id.btn_leggtilbilde);
        final TextView navn = (TextView) findViewById(R.id.textViewNavn);
        final ImageView showImg = (ImageView) findViewById(R.id.show_img_view);
        final TextView noImges = (TextView) findViewById(R.id.textViewNoImg);


        if(items.size() > 0) {
            if(items.get(items.size() -1).getImgResId() == 0) {
                showImg.setImageURI(Uri.parse(items.get(items.size()-1).getUri()));
            } else {
                showImg.setImageResource(items.get(items.size()-1).getImgResId());
            }
            navn.setText(items.get(items.size()-1).getNavn());
        } else {
            showImg.setImageResource(R.drawable.ic_baseline_image_24);
            navn.setText("Ingen Bilder");
        }


        //starter quiz om det er flere enn 2 bilder
        startQuiz.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(items.size() < 2) {
                    noImges.setText("Ikke nok bilder til å starte quiz!");
                } else {
                    startActivity(new Intent(getBaseContext(), quizActivity.class));
                }
            }
        });

        //link til leggtilbilde aktivitet.
        leggtilbilde.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), addPictureActivity.class);
                //DataHolder.getInstance().setData(images);
                startActivity(intent);
            }
        });

        //blar til høyre
        hoyre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                endreBilde(showImg, navn, 1);
            }
        });

        //blar til venstre
        venstre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                endreBilde(showImg, navn, 0);
            }
        });

        //sletter valgte bilde og blar 1 til høyre om det er flere bilder igjen.
        slett.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.size() == 0) {
                    return;
                } else {
                    db.imgdeo().deleteImgItem(items.get(imgCounter));
                    items = db.imgdeo().getAllitems();
                    endreBilde(showImg, navn, 0);
                    /*
                    for (int i = 0; i < items.size(); i++) {
                        if (images[i] == null) {
                            ImgItem temp[] = new ImgItem[images.length - 1];
                            for (int index = 0; index < i; index++) {
                                temp[index] = images[index];
                            }
                            for (int j = i; j < images.length - 1; j++) {
                                temp[j] = images[j + 1];
                            }
                            images = temp;
                            endreBilde(showImg, navn, 1);
                            break;
                        }
                    }
                     */
                }
            }
        });
    }

    //Metoden som endrer bilde ved å bla til venstre/høyre
    private void endreBilde(ImageView showImg, TextView navn, int retning) {
            if(items.size() == 0) {
                showImg.setImageResource(R.drawable.ic_baseline_image_24);
                navn.setText("Ingen Bilder");
            } else if(retning == 0){
                if(imgCounter <= 0) {
                    imgCounter = items.size()-1;
                } else {
                    imgCounter--;
                }
                if(items.get(imgCounter).getImgResId() == 0) {
                    showImg.setImageURI(Uri.parse(items.get(imgCounter).getUri()));
                } else {
                    showImg.setImageResource(items.get(imgCounter).getImgResId());
                }
                navn.setText(items.get(imgCounter).getNavn());
            } else if(retning == 1) {
                if(imgCounter >= (items.size() -1)) {
                    imgCounter = 0;
                } else {
                    imgCounter++;
                }
                if(items.get(imgCounter).getImgResId() == 0) {
                    showImg.setImageURI(Uri.parse(items.get(imgCounter).getUri()));
                } else {
                    showImg.setImageResource(items.get(imgCounter).getImgResId());
                }
                    navn.setText(items.get(imgCounter).getNavn());
            }
    }
}