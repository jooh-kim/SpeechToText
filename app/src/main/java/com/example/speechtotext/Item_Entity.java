package com.example.speechtotext;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item_location_table")
public class Item_Entity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "item")
    private String mItem;

    @NonNull
    @ColumnInfo(name = "location")
    private String mLocation;

    public Item_Entity(@NonNull String item, @NonNull String location){
        this.mItem = item;
        this.mLocation = location;
    }

    @NonNull
    public String getItem() {
        return this.mItem;
    }

    @NonNull
    public String getLocation() {
        return this.mLocation;
    }
}
