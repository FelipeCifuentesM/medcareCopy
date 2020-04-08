package com.imed.medcare.ui.manage_prescription.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imed.medcare.R;
import com.imed.medcare.ui.manage_prescription.ManagePrescriptionContract;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;


/**
 * Created by Ramiro on 17-05-2018.
 */

public class SkipPrescriptionFragment extends BottomSheetDialogFragment {

    private Unbinder unbinder;
    public static ManagePrescriptionContract.View view;

    public static SkipPrescriptionFragment newInstance(ManagePrescriptionContract.View managePrescriptionView) {
        view = managePrescriptionView;
        return new SkipPrescriptionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_skip_medicine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Optional
    @OnClick({R.id.skip_item_1, R.id.skip_item_2, R.id.skip_item_3, R.id.skip_item_4, R.id.skip_item_5})
    public void pickOption(View v) {

        switch (v.getId()) {
            case R.id.skip_item_1:
                view.setReason("1");
                view.closeBottomSheet();
                break;
            case R.id.skip_item_2:
                view.setReason("2");
                view.closeBottomSheet();
                break;

            case R.id.skip_item_3:
                view.setReason("3");
                view.closeBottomSheet();

                break;
            case R.id.skip_item_4:
                view.setReason("4");
                view.closeBottomSheet();

                break;
            case R.id.skip_item_5:
                view.setReason("5");
                view.closeBottomSheet();
                break;
        }
    }

}
