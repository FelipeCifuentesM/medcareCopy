package com.imed.medcare.ui.response_personal_questions;

import com.imed.medcare.model.PersonalProfile;

public interface ResponsePersonalQuestionsContract{

    interface View{
        void manageLoader();
        void showResult(String message);
        void notifyDataSetChangedAdapter();
    }
    interface Presenter{
        PersonalProfile getData(int id);
        void setData(PersonalProfile data, int typeView);
        void setAnswer(String value, String choiseId, PersonalProfile personalProfile);
    }

    interface Interactor{
        PersonalProfile getData(int Id);
        void setData(PersonalProfile personalProfile,int typeView,onSetDataListener listener );
        void setAnswer(String value, String choiseId, PersonalProfile personalProfile,Interactor.onSetAnswerListener listener);

        interface onSetAnswerListener{
            void notifyDataSetChangedAdapter();
        }
        interface onSetDataListener{
            void showResult(String message);
        }
    }

}
