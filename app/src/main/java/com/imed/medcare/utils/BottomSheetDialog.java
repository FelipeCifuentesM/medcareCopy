package com.imed.medcare.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.imed.medcare.R;
import com.imed.medcare.ui.all_documents.AllDocumentsContract;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private AllDocumentsContract.View mListener;

    public BottomSheetDialog(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mListener = (AllDocumentsContract.View) getActivity();

        View view = inflater.inflate(R.layout.item_add_name_document, container,
                false);

        final TextInputEditText nameDocument = view.findViewById(R.id.input_name_document);
        Button done = view.findViewById(R.id.button2);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                if(nameDocument.getText().toString().isEmpty()){
                    name = "Sin nombre";
                }else {
                    name = nameDocument.getText().toString();
                }
                mListener.setNewDocument(name);
            }
        });

        Button cancel = view.findViewById(R.id.button3);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeBottomSheet();
            }
        });



//        Activity activity =getActivity();
//        if(activity != null) {
//            new KeyboardUtil(activity, activity.findViewById(R.id.item_add_name_document));
//        }

        return view;
    }

}

