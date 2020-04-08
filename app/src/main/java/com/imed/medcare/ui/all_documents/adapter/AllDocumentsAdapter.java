package com.imed.medcare.ui.all_documents.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.Document;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;


public class AllDocumentsAdapter extends RecyclerView.Adapter<AllDocumentsAdapter.ViewHolder> {
    private int layout;
    private RealmList<Document> documentRealmList;
    private onItemListener listener;

    public AllDocumentsAdapter(int layout, RealmList<Document> documentRealmList, onItemListener listener){
        this.layout = layout;
        this.documentRealmList = documentRealmList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AllDocumentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllDocumentsAdapter.ViewHolder holder, int position) {
        holder.bind(documentRealmList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return documentRealmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ic_place_holder)
        ImageView image;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.content)
        CardView content;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final Document document, final onItemListener listener) {
            tvTitle.setText(document.getName());
            MedcareUtils.glideImage(image, document.getPicture(), R.drawable.ic_file_placeholder, App.getContext());
            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.showDocument(document);
                }
            });
        }
    }

    public interface onItemListener{
        void showDocument(Document document);
    }


}
