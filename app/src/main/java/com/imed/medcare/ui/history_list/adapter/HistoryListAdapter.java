package com.imed.medcare.ui.history_list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.imed.medcare.network.response.HistoryResponse;

import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HistoryResponse.DataBean> historyResponseList;
    private int layout;
    private onItemListener listener;
    private boolean isFromAttachment;
    private int placeholderLayout;

    public HistoryListAdapter(List<HistoryResponse.DataBean> historyResponseList, int layout, int placeholderLayout, boolean isFromAttachment, onItemListener listener) {
        this.historyResponseList = historyResponseList;
        this.layout = layout;
        this.listener = listener;
        this.isFromAttachment = isFromAttachment;
        this.placeholderLayout = placeholderLayout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        if (historyResponseList.size() > 0) {
            return new HistoryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
        } else {
            return new HistoryListEmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(placeholderLayout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (historyResponseList.size() > 0) {
            ((HistoryListViewHolder) holder).bind(historyResponseList.get(position), isFromAttachment, listener);
        } else {
            ((HistoryListEmptyViewHolder) holder).bind(isFromAttachment);
        }
    }

    @Override
    public int getItemCount() {
        if (historyResponseList.size() > 0) {
            return historyResponseList.size();
        } else {
            return 1;
        }
    }

    public interface onItemListener {
        void showHistory(int id, String title);
    }
}
