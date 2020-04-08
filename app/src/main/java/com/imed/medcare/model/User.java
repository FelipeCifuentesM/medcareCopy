package com.imed.medcare.model;

import com.imed.medcare.network.response.ProfileResponse;
import com.imed.medcare.utils.MedcareUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Raimro on 01-03-2018.
 */

public class User extends RealmObject{

    @PrimaryKey
    private int id;
    private String rut;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String avatar;
    private String accessToken;
    private String refreshToken;
    private Integer attachmentStatus;
    private String message;
    private Boolean isAvailablePoll;
    private RealmList<UserMeasurement> userMeasurementRealmList;
    private RealmList<Professional> professionalRealmList;
    private boolean showPollAttachmentMessage;
    private String dealyMessage;
    private boolean messageReaded;
    private String messageDate;

    public User(){}

    public User(ProfileResponse data, String accessToken, String refreshToken,String messageDate, boolean messageReaded) {
        id = data.getData().get(0).getUser().getId();
        rut  = data.getData().get(0).getUser().getUsername();
        name = data.getData().get(0).getUser().getNames().getGivenName();
        lastName = data.getData().get(0).getUser().getNames().getFamilyName();
        email = data.getData().get(0).getUser().getEmail();
        phone = data.getData().get(0).getUser().getPhone();
        avatar = data.getData().get(0).getUser().getAvatar();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        message = data.getData().get(0).getAttachment().getMessage();
        isAvailablePoll = data.getData().get(0).getAttachment().isAvailablePoll();
        attachmentStatus= data.getData().get(0).getAttachment().getCurrentScore();
        userMeasurementRealmList = getUserMeasurementRealmlist(data.getData().get(0).getUser().getMeasurements());
        professionalRealmList = getProfesionalsRealmList(data.getData().get(0).getProfessionals());
        showPollAttachmentMessage = false;
        dealyMessage = data.getData().get(0).getUser().getMessage();
        this.messageDate = messageDate;
        this.messageReaded = messageReaded;
        checkMessage();
    }

    private void checkMessage() {

        if(!messageDate.equalsIgnoreCase(MedcareUtils.getDateToday())){
            this.messageDate = MedcareUtils.getDateToday();
            this.messageReaded = false;
        }

    }

    public boolean isMessageReaded() {
        return messageReaded;
    }
    public void setMessageReaded(boolean messageReaded) {
        this.messageReaded = messageReaded;
    }
    private RealmList<Professional> getProfesionalsRealmList(List<ProfileResponse.DataBean.ProfessionalsBean> professionals) {
        RealmList<Professional> professionalRealmList = new RealmList<>();
        for(ProfileResponse.DataBean.ProfessionalsBean professionalsBean :professionals){
            professionalRealmList.add(new Professional(professionalsBean));
        }
        return professionalRealmList;
    }

    private RealmList<UserMeasurement> getUserMeasurementRealmlist(List<ProfileResponse.DataBean.UserBean.MeasurementsBean> measurementList) {
        RealmList<UserMeasurement> userMeasurementRealmList = new RealmList<>();
        for(ProfileResponse.DataBean.UserBean.MeasurementsBean measurementsBean :measurementList){

            userMeasurementRealmList.add(new UserMeasurement(measurementsBean.getId(), measurementsBean.getName(),measurementsBean.getUnit(),measurementsBean.getUnit_description(),measurementsBean.getType(), measurementsBean.getTreatment_measurements()));
        }
        return userMeasurementRealmList;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageDate() {
        return messageDate;
    }


    public String getRut() {
        return rut;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Integer getAttachmentStatus() {
        return attachmentStatus;
    }

    public String getDealyMessage() {
        return dealyMessage;
    }


    public RealmList<UserMeasurement> getUserMeasurementRealmList() {
        return userMeasurementRealmList;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getAvailablePoll() {
        return isAvailablePoll;
    }

    public boolean isShowPollAttachmentMessage() {
        return showPollAttachmentMessage;
    }

    public void setShowPollAttachmentMessage(boolean showPollAttachmentMessage) {
        this.showPollAttachmentMessage = showPollAttachmentMessage;
    }

    public RealmList<Professional> getProfessionalRealmList() {
        return professionalRealmList;
    }

    public static void saveToRealm(final ProfileResponse response, final String accessToken, final String refreshToken, final String messageDate, final boolean messageReaded , Realm realm) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(mapUserData(response,accessToken,refreshToken,messageDate,messageReaded));
            }
        });
    }


    private static User mapUserData(ProfileResponse data,String accessToken,String refreshToken, String messageDate, boolean messageReaded) {
        return new User(data,accessToken,refreshToken,messageDate, messageReaded);
    }

}
