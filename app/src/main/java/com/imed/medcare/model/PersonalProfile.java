package com.imed.medcare.model;

import android.util.Log;

import com.imed.medcare.network.response.PersonalProfileResponse;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PersonalProfile extends RealmObject {
    @PrimaryKey
    private Integer id;
    private String name;
    private RealmList<CurrentValue> currenValue = new RealmList<>();
    private String currentName;
    private Integer type;
    private String key;
    private RealmList<PersonalOptions> personalOptionsRealmList;

    public PersonalProfile(){}

    public RealmList<PersonalOptions> getPersonalOptionsRealmList() {
        return personalOptionsRealmList;
    }

    public PersonalProfile(PersonalProfileResponse.DataBean profilePersonalResponse){
        id = profilePersonalResponse.getItem().getId();
        name = profilePersonalResponse.getItem().getName();
        Log.i("PersonalProfile", name);
        for(int i =0 ;profilePersonalResponse.getItem().getCurrent_value().size()>i;i++){
            Log.i("PersonalProfile", String.valueOf(profilePersonalResponse.getItem().getCurrent_value().get(i)));
            currenValue.add(new CurrentValue(profilePersonalResponse.getItem().getCurrent_value().get(i)));
        }
        type = profilePersonalResponse.getItem().getType();
        key = profilePersonalResponse.getItem().getKey();
        currentName = profilePersonalResponse.getItem().getCurrent_name();
        personalOptionsRealmList = getPersonalOptions(profilePersonalResponse.getItem().getOptions(), currenValue);
    }

    public PersonalProfile(PersonalProfile personalProfile){
        id = personalProfile.getId();
        name = personalProfile.getName();
        currenValue.addAll(personalProfile.getCurrenValue());
        type = personalProfile.getType();
        key = personalProfile.getKey();
        currentName = personalProfile.getCurrentName();
        personalOptionsRealmList = getPersonalOptions(personalProfile.getPersonalOptionsRealmList(), currenValue);
    }

    private RealmList<PersonalOptions> getPersonalOptions(RealmList<PersonalOptions> optionsBeanList, RealmList<CurrentValue> currenValue ) {
        RealmList<PersonalOptions> personalOptionsRealmList = new RealmList<>();
        if(optionsBeanList != null) {
            for (PersonalOptions optionsBean : optionsBeanList) {
                personalOptionsRealmList.add(new PersonalOptions(optionsBean.getId(), optionsBean.getName(), currenValue));
            }
        }
        return personalOptionsRealmList;
    }

    private RealmList<PersonalOptions> getPersonalOptions(List<PersonalProfileResponse.DataBean.ItemBean.OptionsBean> optionsBeanList, RealmList<CurrentValue> currenValue ) {
        RealmList<PersonalOptions> personalOptionsRealmList = new RealmList<>();
        if(optionsBeanList != null) {
            for (PersonalProfileResponse.DataBean.ItemBean.OptionsBean optionsBean : optionsBeanList) {
                personalOptionsRealmList.add(new PersonalOptions(optionsBean.getId(), optionsBean.getName(), currenValue));
            }
        }
        return personalOptionsRealmList;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public RealmList<CurrentValue> getCurrenValue() {
        return currenValue;
    }

    public void setCurrenValue(RealmList<CurrentValue> currenValue) {
        this.currenValue = currenValue;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public String getCurrentName() {
        return currentName;
    }

    public Integer getType() {
        return type;
    }

    public String getKey() {
        return key;
    }


    public static void saveToRealm(final PersonalProfileResponse response, Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                for(int i=0; i<response.getData().size(); i++) {

                    realm.insertOrUpdate(mapProfilePersonal(response.getData().get(i)));
                }
            }
        });
    }

    private static PersonalProfile mapProfilePersonal(PersonalProfileResponse.DataBean data) {
        return new PersonalProfile(data);
    }
}
