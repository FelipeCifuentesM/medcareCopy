package com.imed.medcare.ui.response_personal_questions.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.imed.medcare.R;
import com.imed.medcare.model.PersonalOptions;

import io.realm.RealmList;

import static com.imed.medcare.utils.MedcareUtils.dateFormatDocument;

public class ResponsePersonalQuestionsAdapter extends RecyclerView.Adapter<ResponsePersonalQuestionsAdapter.ProfileHolder> {

    private Context context;
    private int buttonItem;
    private int inputItem;
    private RealmList<PersonalOptions> optionsPersonals;
    private String currentName;
    private onItemListener listener;
    private int type;

    public ResponsePersonalQuestionsAdapter(RealmList<PersonalOptions> optionsPersonals, String currentName, int type, int buttonItem, int inputItem, onItemListener listener) {
        this.inputItem = inputItem;
        this.buttonItem = buttonItem;
        this.listener = listener;
        this.type = type;
        this.currentName = currentName;
        this.optionsPersonals = optionsPersonals;
    }


    @Override
    public ResponsePersonalQuestionsAdapter.ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;

        if (1 == type || 4 == type) {
            view = LayoutInflater.from(parent.getContext()).inflate(inputItem, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(buttonItem, parent, false);
        }

        ResponsePersonalQuestionsAdapter.ProfileHolder viewHolder = new ResponsePersonalQuestionsAdapter.ProfileHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        if (optionsPersonals.size() > 0) {
            holder.bind(optionsPersonals.get(position), getItemViewType(position), currentName,listener);
        } else {
            holder.bind(null, getItemViewType(position), currentName,listener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getItemCount() {
        if (optionsPersonals.size() != 0) {
            return optionsPersonals.size();
        } else {
            return 1;
        }
    }



    public class ProfileHolder extends RecyclerView.ViewHolder {
        public ProfileHolder(View itemView) {
            super(itemView);
        }

        void bind(final PersonalOptions optionsPersonal, int type, String currentName,final onItemListener listener) {

            if (2 == type || 3 == type) {
                CardView button = itemView.findViewById(R.id.button);
                TextView textView = itemView.findViewById(R.id.text);
                textView.setText(optionsPersonal.getName());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onButtonListener(String.valueOf(optionsPersonal.getId()), optionsPersonal.getName());
                    }
                });
                if (optionsPersonal.isSelected()) {
                    button.setCardBackgroundColor(context.getResources().getColor(R.color.primary_text_color));
                    textView.setTextColor(context.getResources().getColor(R.color.white));
                }else {
                    button.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                    textView.setTextColor(context.getResources().getColor(R.color.marine_blue));
                }

            } else {
                TextView measure = itemView.findViewById(R.id.weigth);
                TextInputLayout textInputLayout = itemView.findViewById(R.id.text_input_layout);
                final TextInputEditText input = itemView.findViewById(R.id.input);
                TextInputLayout textInputLayoutDate = itemView.findViewById(R.id.text_input_layout_date);
                final TextInputEditText textInputEditDate = itemView.findViewById(R.id.input_date);
                textInputEditDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.setCalendar(textInputEditDate);
                    }
                });
                measure.setVisibility(View.GONE);
                if (type == 1) {
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    textInputLayoutDate.setVisibility(View.GONE);
                    input.setText(currentName);
                } else {
                    textInputLayout.setVisibility(View.GONE);
                    if(currentName != null && !currentName.isEmpty()) {
                        String newDate = currentName.split("-")[2] + "-" + currentName.split("-")[1] + "-" + currentName.split("-")[0];
                        textInputEditDate.setText(dateFormatDocument(newDate + " 00:00:00"));
                    }
                }

                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        listener.onTextChangeListener(input.getText().toString(), 1);
                    }
                });

                textInputEditDate.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        listener.onTextChangeListener(textInputEditDate.getText().toString(),4);
                    }
                });
            }
        }
    }

    public interface onItemListener {
        void onButtonListener(String choiceId, String value);

        void onTextChangeListener(String value,Integer type);

        void setCalendar(TextInputEditText inputDate);
    }
}
