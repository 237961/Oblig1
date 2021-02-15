package com.example.oblig;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImgDatabase db;
    List<ImgItem> list;

    //main activity, nå sender den kun brukeren videre til neste aktivitet, men kan eventuelt brukes til å lage en startmeny til de 3 aktivitetene.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = ImgDatabase.getInstance(this);
        list = db.imgdeo().getAllitems();

        if(list.size() < 3) {
            ImgItem item1 = new ImgItem(R.drawable.image1, "Finn", null);
            ImgItem item2 = new ImgItem(R.drawable.image2, "Kevin", null);
            ImgItem item3 = new ImgItem(R.drawable.image3, "Carl", null);

            db.imgdeo().insertImgitem(item1);
            db.imgdeo().insertImgitem(item2);
            db.imgdeo().insertImgitem(item3);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, seeAndDeleteActivity.class));
    }
}