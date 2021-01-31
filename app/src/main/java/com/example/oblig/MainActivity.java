package com.example.oblig;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //main activity, nå sender den kun brukeren videre til neste aktivitet, men kan eventuelt brukes til å lage en startmeny til de 3 aktivitetene.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, seeAndDeleteActivity.class));
    }
}