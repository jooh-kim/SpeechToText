package com.example.speechtotext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>{

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private final TextView itemItemView;

        private ItemViewHolder(View itemView){
            super(itemView);
            itemItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Item_Entity> mItems; // Cached copy of Items

    ItemListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (mItems != null) {
            Item_Entity current = mItems.get(position);
            holder.itemItemView.setText(current.getItem() +" : "+ current.getLocation());
        } else {
            // Covers the case of data not being ready yet.
            holder.itemItemView.setText("No Word");
        }
    }

    void setWords(List<Item_Entity> items){
        mItems = items;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mItems != null)
            return mItems.size();
        else return 0;
    }
}
