package com.example.speechtotext;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Item_Dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Item_Entity item);

    @Query("DELETE FROM item_location_table")
    void deleteAll();

    @Query("SELECT * from item_location_table ORDER BY item")
    LiveData<List<Item_Entity>> getAllItems();
}
