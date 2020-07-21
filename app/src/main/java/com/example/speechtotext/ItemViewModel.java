package com.example.speechtotext;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository mItemRepository;
    private LiveData<List<Item_Entity>> mAllItems;

    public ItemViewModel(Application application){
        super(application);

        mItemRepository = new ItemRepository(application);
        mAllItems = mItemRepository.getAllItems();

    }

    LiveData<List<Item_Entity>> getmAllItems(){
        return mAllItems;
    }

    public void insert(Item_Entity item){
        mItemRepository.insert(item);
    }

}
