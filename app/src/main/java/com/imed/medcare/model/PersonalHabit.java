package com.imed.medcare.model;

import com.imed.medcare.network.response.PersonalProfileResponse;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PersonalHabit extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String name;

    public PersonalHabit(){}

    public PersonalHabit(PersonalProfileResponse.DataBean profilePersonalResponse){
        id = profilePersonalResponse.getItem().getId();
        name = profilePersonalResponse.getItem().getName();
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
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

    private static PersonalHabit mapProfilePersonal(PersonalProfileResponse.DataBean data) {
        return new PersonalHabit(data);
    }

}
