package com.imed.medcare.model;

import com.imed.medcare.network.response.TreatmentResponse;
import com.imed.medcare.utils.MedcareUtils;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.imed.medcare.network.RestClient.BASE_URL;

public class Prescription extends RealmObject {

    @PrimaryKey
    private int id;
    private String dateStart;
    private String dateEnd;
    private Long dateStartGetTime;
    private Long dateEndGetTime;
    private Integer type;
    private RealmList<Schedule> scheduleRealmList;
    private String name;
    private String doseName;
    private Integer grams;
    private String image;
    private Integer priority;

    public Prescription(){}

    private Prescription(TreatmentResponse.DataBean.PrescriptionsBean dataBean){
        id = dataBean.getId();
        dateStart  = dataBean.getStart();
        dateEnd = dataBean.getEnd();
        dateStartGetTime = getTime(dataBean.getStart());
        dateEndGetTime = getTime(dataBean.getEnd());
        type = dataBean.getType();
        priority = dataBean.getPriority();
        name = dataBean.getMedicament().getName();
        grams = dataBean.getMedicament().getGrams();
        doseName = dataBean.getMedicament_format().getName();
        image = BASE_URL+dataBean.getMedicament_format().getImg();
        scheduleRealmList = newScheduleList(dataBean.getSchedules());
    }

    private Long getTime(String dateString) {

        if(dateString!=null) {
            Date date = MedcareUtils.YearMonthDayDate(dateString.split(" ")[0]);
            if(date !=null){
                return date.getTime();
            }else {
                return -1L;
            }
        }
        return -1L;
    }

    private RealmList<Schedule> newScheduleList(List<TreatmentResponse.DataBean.PrescriptionsBean.SchedulesBean> schedulesBeanList) {
        RealmList<Schedule> scheduleRealmList = new RealmList<>();

        for(TreatmentResponse.DataBean.PrescriptionsBean.SchedulesBean schedulesBean : schedulesBeanList){
            scheduleRealmList.add(new Schedule(schedulesBean));
        }
        return scheduleRealmList;
    }


    public static void saveToRealm(final List<TreatmentResponse.DataBean.PrescriptionsBean> prescriptionsBeanList, Realm realm) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for(TreatmentResponse.DataBean.PrescriptionsBean patientBean : prescriptionsBeanList) {
                    realm.insertOrUpdate(mapPrescriptionData(patientBean));
                }
            }
        });

    }

    private static Prescription mapPrescriptionData(TreatmentResponse.DataBean.PrescriptionsBean patientBean) {
        return new Prescription(patientBean);
    }
    public int getId() {
        return id;
    }

    public Integer getPriority() { return priority; }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public Long getdateStartGetTime() {
        return dateStartGetTime;
    }

    public Long getdateEndGetTime() {
        return dateEndGetTime;
    }

    public Integer getType() {
        return type;
    }

    public RealmList<Schedule> getScheduleRealmList() {
        return scheduleRealmList;
    }

    public String getName() {
        return name;
    }

    public Integer getGrams() {
        return grams;
    }

    public String getImage() {
        return image;
    }

    public String getDoseName() {
        return doseName;
    }

}
