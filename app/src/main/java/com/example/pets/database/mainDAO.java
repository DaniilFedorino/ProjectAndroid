package com.example.pets.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import android.net.Uri;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pets.models.Notes;

import java.util.List;

@Dao
public interface mainDAO {

    @Insert (onConflict = REPLACE)
    void insert(Notes notes);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Notes> getAll();

    @Query("UPDATE notes SET title=:title,breed=:breed,age=:age,passportData=:passportData,vaccinations=:vaccinations,image=:image WHERE ID=:id")
    void update(int id, String title, String breed, String age, String passportData, String vaccinations, byte[] image);

    @Delete
    void delete(Notes notes);

    @Query("UPDATE notes SET pinned=:pin WHERE ID=:id")
    void pin(int id,boolean pin);
}
