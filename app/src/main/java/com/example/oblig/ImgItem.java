package com.example.oblig;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.net.Uri;
import android.os.Parcel;

//klasse til å håndtere bilde objekter, håndterer både uri fra lastet opp bilder og resource id fra drawable.
@Entity(tableName="img_table")
public class ImgItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int imgResId;
    private String navn;
    private String uri;

    public ImgItem(int imgResId, String navn, String uri) {
        this.imgResId = imgResId;
        this.navn = navn;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
