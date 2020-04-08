package com.imed.medcare.ui.history_list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.imed.medcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryListEmptyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_placeholder_body)
    TextView tvPlaceHolderBody;

    public HistoryListEmptyViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(boolean isFromAttachment) {

        if(isFromAttachment){
            tvPlaceHolderBody.setText("No tienes historial de apego\npara mostrar.");
        }else {
            tvPlaceHolderBody.setText("No tienes cuestionarios anteriores\npara mostrar.");
        }
    }
}
