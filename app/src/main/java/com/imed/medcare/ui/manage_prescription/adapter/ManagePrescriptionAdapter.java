package com.imed.medcare.ui.manage_prescription.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.Pillbox;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class ManagePrescriptionAdapter extends RecyclerView.Adapter<ManagePrescriptionAdapter.ViewHolder> {

    private RealmList<Pillbox> pillboxArrayList;
    private int layout;
    private onItemListener listener;

    public ManagePrescriptionAdapter(RealmList<Pillbox> pillboxArrayList, int layout, onItemListener listener) {
        this.pillboxArrayList = pillboxArrayList;
        this.listener = listener;
        this.layout = layout;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(pillboxArrayList.get(position), position, listener);
    }

    @Override
    public int getItemCount() {
        return pillboxArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content_medicine)
        LinearLayout contentMedicine;
        @BindView(R.id.medicine_image)
        ImageView medicineImage;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.medicine_time)
        TextView medicineTime;
        @BindView(R.id.img_close)
        ImageView imgClose;
        @BindView(R.id.dose_data)
        LinearLayout contentDose;
        @BindView(R.id.dose)
        TextView dose;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Pillbox pillbox, final int position, final onItemListener listener) {

            imgClose.setVisibility(View.VISIBLE);
            contentDose.setVisibility(View.VISIBLE);
            name.setText(pillbox.getName());
            dose.setText(pillbox.getDose()+" "+pillbox.getDoseName());
            medicineTime.setText(pillbox.getTime().split(":")[0] + ":" + pillbox.getTime().split(":")[1]);
            MedcareUtils.glideImage(medicineImage, pillbox.getUrlImage(), R.drawable.dummy_avatar, App.getContext());

            contentMedicine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.delete(pillbox, position);
                    notifyItemRemoved(position);
                }
            });
        }


    }

    public interface onItemListener {
        void delete(Pillbox pillbox, int position);
    }
}
