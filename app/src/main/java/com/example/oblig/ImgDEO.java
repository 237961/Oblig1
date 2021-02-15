package com.example.oblig;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface ImgDEO {

    @Insert
    void insertImgitem(ImgItem img);

    @Delete
    void deleteImgItem(ImgItem img);

    @Query("SELECT * FROM img_table")
    List<ImgItem> getAllitems();
}
