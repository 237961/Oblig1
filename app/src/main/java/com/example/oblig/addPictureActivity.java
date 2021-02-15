package com.example.oblig;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class addPictureActivity extends AppCompatActivity {

    //ImgItem images[] = DataHolder.getInstance().getData();

    ImageView imgview;
    Button velgBilde;
    Uri uri;
    ImgDatabase db;
    int numItems;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_picture);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = ImgDatabase.getInstance(this);
        numItems = db.imgdeo().getAllitems().size();

        final EditText text1 = (EditText) findViewById(R.id.EditText01);
        final Button leggTil = (Button) findViewById(R.id.btn_leggtil);
        final TextView error = (TextView) findViewById(R.id.textViewError);
        final Button tilbake = (Button) findViewById(R.id.btn_picaddTilbake);
        imgview = (ImageView) findViewById(R.id.image_view);
        velgBilde = (Button) findViewById(R.id.btn_velgBilde);

        tilbake.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), seeAndDeleteActivity.class));
            }
        });

        //velger bilde fra galleri, sjekker om applikasjonen har tilgang (privacy). Om den ikke har tilgang, vil den spørre om det.
        velgBilde.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sjekker for adgang for versjon større eller lik 23
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //Ingen adgang, spørr om adgang
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //vis popup
                        requestPermissions(permissions, PERMISSION_CODE);
                    } else {
                        //Har adgang
                        velgBildeFraGalleri();
                    }
                } else {
                    //OS er eldre enn version 23 (mashmallow)
                    velgBildeFraGalleri();
                }
            }
        });

        //Legger til bilde i images og oppdaterer dataholderen om bilde er lastet og tekst lagt inn. Gir feilmelding ellers.
        leggTil.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(uri == null && text1.getText().toString().equals("")) {
                    error.setText("Du har ikke lagt til bilde eller tekst!");
                } else if(uri == null) {
                    error.setText("Du har ikke lagt til bilde!");
                } else if(text1.getText().toString().equals("")) {
                    error.setText("Du har ikke lagt til tekst!");
                } else {
                    ImgItem item = new ImgItem(0, (text1.getText().toString()), uri.toString());
                    db.imgdeo().insertImgitem(item);
                    startActivity(new Intent(getBaseContext(), seeAndDeleteActivity.class));
                    /*
                    ImgItem item = new ImgItem(uri, (text1.getText().toString()));
                    ImgItem temp[] = new ImgItem[images.length + 1];
                    for (int i = 0; i < images.length; i++) {
                        temp[i] = images[i];
                    }
                    temp[images.length] = item;
                    DataHolder.getInstance().setData(temp);
                    startActivity(new Intent(getBaseContext(), seeAndDeleteActivity.class));

                     */
                }
            }
        });

    }

    private void velgBildeFraGalleri() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //Sjekker om applikasjonen har tilgang til galleri.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    velgBildeFraGalleri();
                } else {
                    //ingen adgang
                    Toast.makeText(this, "Ingen adgang", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //Returnerer bilde.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //sett bilde til velgBilde
            uri = data.getData();
            imgview.setImageURI(data.getData());
        }
    }
}
