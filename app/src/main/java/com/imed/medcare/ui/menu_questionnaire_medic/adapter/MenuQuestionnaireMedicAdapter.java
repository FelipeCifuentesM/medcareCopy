package com.imed.medcare.ui.menu_questionnaire_medic.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.MedicPollQuestion;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.utils.MedcareUtils;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmResults;

import static com.imed.medcare.network.RestClient.BASE_URL;


public class MenuQuestionnaireMedicAdapter extends RecyclerView.Adapter<MenuQuestionnaireMedicAdapter.ViewHolder>{

    private int layout;
    private int layout2;
    private RealmResults<MedicPollQuestion> medicPollQuestionArrayList;
    private Treatment treatment;

    public MenuQuestionnaireMedicAdapter(RealmResults<MedicPollQuestion> medicPollQuestionArrayList, Treatment treatment, int layout, int layout2){
        this.layout = layout;
        this.layout2 = layout2;
        this.medicPollQuestionArrayList = medicPollQuestionArrayList;
        this.treatment = treatment;
    }

    @NonNull
    @Override
    public MenuQuestionnaireMedicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==0) {
            view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(layout2, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuQuestionnaireMedicAdapter.ViewHolder holder, int position) {
        holder.bind(medicPollQuestionArrayList.get(position), getItemViewType(position), treatment);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return medicPollQuestionArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        void bind(MedicPollQuestion medicPollQuestion, int type, Treatment treatment) {
            TextView question;
            TextView answer;
            if (type == 0) {
                question = itemView.findViewById(R.id.textView7);
                answer = itemView.findViewById(R.id.textView8);
            } else {
                CircleImageView avatar = itemView.findViewById(R.id.imageView);
                MedcareUtils.glideImage(avatar,BASE_URL+"professional/avatar/"+treatment.getProfessional().getAvatar(),R.drawable.dummy_avatar, App.getContext());
                question = itemView.findViewById(R.id.textView10);
                answer = itemView.findViewById(R.id.textView11);
            }
            question.setText(medicPollQuestion.getDescription());
            if(medicPollQuestion.getAnswerPolls().size()>0) {
                StringBuilder answerString = new StringBuilder();
                for(int i=0; medicPollQuestion.getAnswerPolls().size() > i; i++){
                    if(i != (medicPollQuestion.getAnswerPolls().size() -1)) {
                        answerString.append(medicPollQuestion.getAnswerPolls().get(i).getValue()).append(", ");
                    }else {
                        answerString.append(medicPollQuestion.getAnswerPolls().get(i).getValue());
                    }
                }

                answer.setText(answerString);
                answer.setTextColor(App.getContext().getResources().getColor(R.color.primary_text_color));
            }else{
                answer.setText("Sin contestar");
                answer.setTextColor(App.getContext().getResources().getColor(R.color.button_text_primary_disable_color));
            }
        }
    }
}
