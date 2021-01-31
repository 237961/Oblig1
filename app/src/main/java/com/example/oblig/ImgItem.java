package com.example.oblig;

import android.net.Uri;
import android.os.Parcel;

//klasse til å håndtere bilde objekt, håndterer både uri fra lastet opp bilder og resource id fra drawable.
public class ImgItem {

    private int imgResId;
    private String navn;
    private Uri uri;

    public ImgItem(int imgResId, String navn) {
        this.imgResId = imgResId;
        this.navn = navn;
        this.uri = null;
    }
    public ImgItem(Uri uri, String navn) {
        this.imgResId = 0;
        this.navn = navn;
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    protected ImgItem(Parcel in) {
        imgResId = in.readInt();
        navn = in.readString();
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
