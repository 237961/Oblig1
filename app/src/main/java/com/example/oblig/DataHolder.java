package com.example.oblig;

public class DataHolder {

    //Singleton klasse for datahåndterin
    ImgItem item1 = new ImgItem(R.drawable.image1, "Finn");
    ImgItem item2 = new ImgItem(R.drawable.image2, "Kevin");
    ImgItem item3 = new ImgItem(R.drawable.image3, "Carl");
    private ImgItem data[] = {item1, item2, item3};

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {
        return holder;
    }

    public ImgItem[] getData() {
        return data;
    }

    public void setData(ImgItem[] data) {
        this.data = data;
    }
}
