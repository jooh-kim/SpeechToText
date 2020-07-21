package com.example.speechtotext;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class ItemRepository {

    private Item_Dao mItemDao;
    private LiveData<List<Item_Entity>> mAllItems;

    ItemRepository(Application application){
        ItemRoomDatabase db = ItemRoomDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAllItems();
    }
    LiveData<List<Item_Entity>> getAllItems(){
        return mAllItems;
    }

    void insert(Item_Entity item){
        ItemRoomDatabase.databaseWriteExecutor.execute(() -> {
            mItemDao.insert(item);
        });
    }
}
