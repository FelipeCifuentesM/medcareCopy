package com.imed.medcare.ui.treatment.treatmentInfo.adapter;

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
import com.imed.medcare.model.Medicine;
import com.imed.medcare.utils.MedcareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class MedicineAdapter extends RecyclerView.Adapter <MedicineAdapter.ViewHolder> {

    private RealmList<Medicine> medicineList;
    private int layout;

    public MedicineAdapter (RealmList<Medicine> medicineList, int layout){
        this.medicineList = medicineList;
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
        holder.bind(medicineList.get(position));
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content_medicine)
        LinearLayout content;
        @BindView(R.id.medicine_name)
        TextView name;
        @BindView(R.id.medicine_image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(Medicine medicine) {

            name.setText(medicine.getName());
            content.setEnabled(false);
            MedcareUtils.glideImage(image,medicine.getImage(),R.drawable.ic_medic_profile,App.getContext());
        }
    }
}
