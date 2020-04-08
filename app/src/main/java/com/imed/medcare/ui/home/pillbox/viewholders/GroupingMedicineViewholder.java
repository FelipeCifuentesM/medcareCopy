package com.imed.medcare.ui.home.pillbox.viewholders;

import android.app.Activity;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.Pillbox;
import com.imed.medcare.model.PillboxHeader;
import com.imed.medcare.ui.home.pillbox.PillboxContract;
import com.imed.medcare.utils.MedcareUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

import static com.imed.medcare.utils.Constants.BOTTOM_SHEET_SKIP_MEDICINE;
import static com.imed.medcare.utils.Constants.BOTTOM_SHEET_TAKE_MEDICINE;
import static com.imed.medcare.utils.MedcareUtils.YearMonthDayDate;
import static com.imed.medcare.utils.MedcareUtils.YearMonthDayString;
import static com.imed.medcare.utils.MedcareUtils.dpToPixels;

/**
 * Created by Ramiro on 15-05-2018.
 */

public class GroupingMedicineViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_title)
    TextView title;
    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.medicine_content)
    LinearLayout medicineContent;
    @BindView(R.id.content_header)
    RelativeLayout contentHeader;
    @BindView(R.id.img_arrow_down)
    ImageView imgArrow;


    public GroupingMedicineViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(final ArrayList<PillboxHeader> pillboxArrayList, final PillboxHeader pillboxHeader, final PillboxContract.View pillboxListener, final Date updatedDate, final Activity activity) {

        title.setText(pillboxHeader.getName());
        switch (pillboxHeader.getName()) {
            case "Madrugada":
                imgHeader.setImageResource(R.drawable.ic_early_morning_pillbox);
                break;
            case "Mañana":
                imgHeader.setImageResource(R.drawable.ic_morning_pillbox);
                break;
            case "Tarde":
                imgHeader.setImageResource(R.drawable.ic_afternoon_pillbox);
                break;
            case "Noche":
                imgHeader.setImageResource(R.drawable.ic_night_pillbox);
                break;
        }


        int intMaxNoOfChild = 0;
        for (int index = 0; index < pillboxArrayList.size(); index++) {
            int intMaxSizeTemp = pillboxArrayList.get(index).getPillboxArrayList().size();
            if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp;
        }

        for (int indexView = 0; indexView < intMaxNoOfChild; indexView++) {
            View periodView;
            if (indexView < pillboxHeader.getPillboxArrayList().size() && pillboxHeader.getPillboxArrayList().get(indexView).isOk()) {
                periodView = LayoutInflater.from(App.getContext()).inflate(R.layout.item_medicine, this.medicineContent, false);
            } else {
                periodView = LayoutInflater.from(App.getContext()).inflate(R.layout.item_medicine_alert, this.medicineContent, false);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(dpToPixels(16), dpToPixels(4), dpToPixels(16), dpToPixels(4));

            medicineContent.addView(periodView, layoutParams);
        }


        int noOfChildTextViews = medicineContent.getChildCount();
        int noOfChild = pillboxHeader.getPillboxArrayList().size();
        if (noOfChild < noOfChildTextViews) {
            for (int index = noOfChild; index < noOfChildTextViews; index++) {
                View currentTextView = medicineContent.getChildAt(index);
                currentTextView.setVisibility(View.GONE);
            }
        }

        for (int textViewIndex = 0; textViewIndex < noOfChild; textViewIndex++) {
            View contentTimeMedicine = medicineContent.getChildAt(textViewIndex);
            if (textViewIndex <= pillboxHeader.getPillboxArrayList().size() && pillboxHeader.getPillboxArrayList().get(textViewIndex).isOk()) {

                LinearLayout contentMedicine = contentTimeMedicine.findViewById(R.id.content_medicine);
                TextView tvMedicine = contentTimeMedicine.findViewById(R.id.name);
                TextView tvTime = contentTimeMedicine.findViewById(R.id.medicine_time);
                ImageView imgMedicine = contentTimeMedicine.findViewById(R.id.medicine_image);
                FrameLayout delayAlert = contentTimeMedicine.findViewById(R.id.delay_alert);
                TextView delayTime = contentTimeMedicine.findViewById(R.id.delay_time);
                TextView tvDose = contentTimeMedicine.findViewById(R.id.dose);
                LinearLayout contentDose = contentTimeMedicine.findViewById(R.id.dose_data);
                tvMedicine.setText(pillboxHeader.getPillboxArrayList().get(textViewIndex).getName());
                MedcareUtils.glideImage(imgMedicine, pillboxHeader.getPillboxArrayList().get(textViewIndex).getUrlImage(), R.drawable.dummy_avatar, App.getContext());
                tvTime.setText(pillboxHeader.getPillboxArrayList().get(textViewIndex).getTime().split(":")[0] + ":" + pillboxHeader.getPillboxArrayList().get(textViewIndex).getTime().split(":")[1]);
                tvDose.setText(pillboxHeader.getPillboxArrayList().get(textViewIndex).getDose() +" "+pillboxHeader.getPillboxArrayList().get(textViewIndex).getDoseName());


                if(pillboxHeader.getPillboxArrayList().get(textViewIndex).getTaken() == null) {
                    Calendar actualDate = Calendar.getInstance();
                    boolean difference = YearMonthDayDate(YearMonthDayString(updatedDate)).getTime()<=YearMonthDayDate(YearMonthDayString(actualDate.getTime())).getTime();
                    contentMedicine.setBackgroundResource(R.drawable.card_out_line);
                    contentMedicine.setEnabled(difference);
                    delayAlert.setVisibility(View.GONE);
                    contentDose.setVisibility(View.VISIBLE);

                }else if(pillboxHeader.getPillboxArrayList().get(textViewIndex).getTaken() == 1){
                    ImageView icStatusMedicine = contentMedicine.findViewById(R.id.ic_status_medicine);
                    icStatusMedicine.setImageResource(R.drawable.ic_check_circle_green);
                    contentMedicine.setEnabled(false);
                    if(pillboxHeader.getPillboxArrayList().get(textViewIndex).getDifferenceTime() !=null){
                        delayAlert.setVisibility(View.VISIBLE);
                        contentMedicine.setBackgroundResource(R.drawable.card_out_line);
                        delayTime.setText(pillboxHeader.getPillboxArrayList().get(textViewIndex).getDifferenceTime());
                    }else {
                        delayAlert.setVisibility(View.GONE);
                        contentMedicine.setBackgroundResource(R.drawable.card_green);
                    }
                }else {
                    contentMedicine.setBackgroundResource(R.drawable.card_grey);
                    ImageView icStatusMedicine = contentMedicine.findViewById(R.id.ic_status_medicine);
                    icStatusMedicine.setImageResource(R.drawable.ic_cancel_circle_gray);
                    contentMedicine.setEnabled(false);
                    delayAlert.setVisibility(View.GONE);
                }
                final int finalTextViewIndex1 = textViewIndex;
                contentMedicine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.delete(Pillbox.class);
                    realm.commitTransaction();

                    String time = pillboxHeader.getPillboxArrayList().get(finalTextViewIndex1).getTime();
                    RealmList<Pillbox> pillboxRealmList = new RealmList<>();
                    for(int i=0;pillboxArrayList.size()>i;i++){
                        for(int j=0;pillboxArrayList.get(i).getPillboxArrayList().size()>j;j++){

                            if(time.equalsIgnoreCase(pillboxArrayList.get(i).getPillboxArrayList().get(j).getTime()) && pillboxArrayList.get(i).getPillboxArrayList().get(j).getTaken() == null){
                                pillboxRealmList.add(pillboxArrayList.get(i).getPillboxArrayList().get(j));
                            }
                        }
                    }
                    Pillbox.saveToRealm(pillboxRealmList,realm);
                    realm.close();
                    pillboxListener.showManagePrescription();
                    }
                });

            } else {
                final int finalTextViewIndex = textViewIndex;
                TextView tvMedicine = contentTimeMedicine.findViewById(R.id.name);
                final TextView tvTime = contentTimeMedicine.findViewById(R.id.medicine_time);
                ImageView imgMedicine = contentTimeMedicine.findViewById(R.id.medicine_image);
                TextView tvDelayTime = contentTimeMedicine.findViewById(R.id.delay_time);
                Button takeMedicine = contentTimeMedicine.findViewById(R.id.take_medicine);
                MaterialButton skipMedicine = contentTimeMedicine.findViewById(R.id.skip_medicine);


                takeMedicine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pillboxListener.showBottomSheet(BOTTOM_SHEET_TAKE_MEDICINE, pillboxHeader.getPillboxArrayList().get(finalTextViewIndex));
                    }
                });
                skipMedicine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pillboxListener.showBottomSheet(BOTTOM_SHEET_SKIP_MEDICINE, pillboxHeader.getPillboxArrayList().get(finalTextViewIndex));
                    }
                });
                tvMedicine.setText(pillboxHeader.getPillboxArrayList().get(textViewIndex).getName());
                MedcareUtils.glideImage(imgMedicine, pillboxHeader.getPillboxArrayList().get(textViewIndex).getUrlImage(), R.drawable.dummy_avatar, App.getContext());
                tvTime.setText(pillboxHeader.getPillboxArrayList().get(textViewIndex).getTime().split(":")[0] + ":" + pillboxHeader.getPillboxArrayList().get(textViewIndex).getTime().split(":")[1]);
                tvDelayTime.setText(pillboxHeader.getPillboxArrayList().get(textViewIndex).getDifferenceTime());
            }
        }

        if (pillboxHeader.isShow()) {
            imgArrow.setImageResource(R.drawable.ic_keyboard_arrow_down);
            medicineContent.setVisibility(View.VISIBLE);
        } else {
            imgArrow.setImageResource(R.drawable.ic_keyboard_arrow_up_black);
            medicineContent.setVisibility(View.GONE);
        }
//        contentHeader.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (medicineContent.getVisibility() == View.VISIBLE) {
//                    pillboxHeader.setShow(false);
//                    imgArrow.setImageResource(R.drawable.ic_keyboard_arrow_up_black);
//
//                } else {
//                    pillboxHeader.setShow(true);
//                    imgArrow.setImageResource(R.drawable.ic_keyboard_arrow_down);
//                }
//            }
//        });
    }


}
