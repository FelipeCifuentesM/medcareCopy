package com.imed.medcare.network.request;

import com.google.gson.annotations.SerializedName;
import com.imed.medcare.model.PersonalOptions;
import com.imed.medcare.model.PersonalProfile;

import java.util.ArrayList;

public class PersonalProfileRequest {
    @SerializedName("data")
    private ArrayList<Data> dataArrayList;

    public PersonalProfileRequest(PersonalProfile personalProfile){

        dataArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for(PersonalOptions personalOptions : personalProfile.getPersonalOptionsRealmList()) {
            if(personalOptions.isSelected()) {
                stringArrayList.add(String.valueOf(personalOptions.getId()));
            }
        }
        dataArrayList.add(new Data(personalProfile, stringArrayList));
    }


    public class Data{
        String key;
        Integer type;
        ArrayList<String> id_option = new ArrayList<>();
        Data(PersonalProfile personalProfile, ArrayList<String> stringArrayList){
            key = personalProfile.getKey();
            type = personalProfile.getType();
            if(type == 2 ||type == 3) {
                id_option.addAll(stringArrayList);
            }else {
                id_option = new ArrayList<>();
                id_option.add(personalProfile.getCurrentName());
            }
        }


    }
}


