package com.imed.medcare.ui.home.pillbox.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imed.medcare.R;
import com.imed.medcare.model.PillboxHeader;
import com.imed.medcare.ui.home.pillbox.PillboxContract;
import com.imed.medcare.ui.home.pillbox.PillboxFragment;
import com.imed.medcare.ui.home.pillbox.viewholders.EmptyMedicineViewholder;
import com.imed.medcare.ui.home.pillbox.viewholders.GroupingMedicineViewholder;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ramiro on 15-05-2018.
 */

public class PillboxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_EMPTY = 1;

    private ArrayList<PillboxHeader> data;

    private PillboxContract.View pillboxListener;
    private Activity activity;
    private Date updatedDate;


    public PillboxAdapter(ArrayList<PillboxHeader> results, PillboxFragment pillboxFragment, Date updatedDate, Activity activity) {
        this.data = results;
        this.pillboxListener = pillboxFragment;
        this.activity = activity;
        this.updatedDate =updatedDate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {

            case TYPE_EMPTY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pillbox_empty, parent, false);
                return new EmptyMedicineViewholder(view);

            case TYPE_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_group_medicine, parent, false);
                return new GroupingMedicineViewholder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof EmptyMedicineViewholder) {
            Log.d("MESSAGE","BIND EMPTY");
            //empty is showed
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean expanded = data.get(position).isShow();
                data.get(position).setShow(!expanded);
                notifyItemChanged(position);
            }
        });
        if (holder instanceof GroupingMedicineViewholder) {
            ((GroupingMedicineViewholder) holder).bind(data,data.get(position),pillboxListener,updatedDate, activity);
        }
    }

    @Override
    public int getItemCount() {
        if(data.size() == 0)
            return 1;
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(data.size() == 0) {
            return TYPE_EMPTY;
        }
        return TYPE_HEADER;
    }

    public void removeItem(int itemPosition) {
        data.remove(itemPosition);
        notifyItemRemoved(itemPosition);
        notifyItemRangeChanged(itemPosition, data.size());

        verifyNewList();
    }

    /*
    This method is for remove the Grouping text when there are no elements
    in the group
     */
    private void verifyNewList() {
        for(int i=0; i<data.size(); i++){
            if(data.get(i) !=null){
                if(i == data.size()-1){
                    data.remove(i);
                    notifyItemRemoved(i);
                    notifyItemRangeChanged(i, data.size());
                }
                else{
                    if(data.get(i+1) !=null) {
                        data.remove(i);
                        notifyItemRemoved(i);
                        notifyItemRangeChanged(i, data.size());
                    }
                }
            }
        }
    }
}
