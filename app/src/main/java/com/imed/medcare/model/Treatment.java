package com.imed.medcare.model;

import com.imed.medcare.network.response.TreatmentResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ramiro on 23-05-2018.
 */

public class Treatment extends RealmObject{

    @PrimaryKey
    private int id;
    private String treatmentName;
    private RealmList<Medicine> medicines;
    private RealmList<Document> documentRealmList;
    private Professional professional;
    private String createdAt;

    public Treatment(){}
    public Treatment(int id){
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }


    public Treatment(TreatmentResponse.DataBean treatmentResponse) {
        this.id = treatmentResponse.getId();
        this.treatmentName = treatmentResponse.getTreatment_type().getName();
        this.medicines =  getMedicinesTreatament(treatmentResponse.getPrescriptions());
        this.documentRealmList = getDocTreatament(treatmentResponse.getId(),treatmentResponse.getDocs());
        this.professional = new Professional(treatmentResponse.getPatient().getMembership().getProfessional(), treatmentResponse.getPatient().getMembership().getDepartment().getBranch().getName());
        this.createdAt = treatmentResponse.getStart();
    }

    private RealmList<Medicine> getMedicinesTreatament(List<TreatmentResponse.DataBean.PrescriptionsBean> prescriptions) {

        RealmList<Medicine> medicineRealmList = new RealmList<>();
        if(prescriptions !=null) {
            for (TreatmentResponse.DataBean.PrescriptionsBean prescriptionsBean : prescriptions) {
                medicineRealmList.add(new Medicine(prescriptionsBean));
            }
        }
        return medicineRealmList;
    }

    private RealmList<Document> getDocTreatament(int idTreatment,List<TreatmentResponse.DataBean.DocsBean> docs) {
        RealmList<Document> fileTreatmentRealmList = new RealmList<>();
        if(docs != null) {
            for (TreatmentResponse.DataBean.DocsBean docsBean : docs) {
                fileTreatmentRealmList.add(new Document(docsBean.getId(), docsBean.getPath(), docsBean.getName()));
            }
        }
        return fileTreatmentRealmList;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public int getId() {
        return id;
    }
    public RealmList<Document> getDocumentRealmList() {
        return documentRealmList;
    }

    public RealmList<Medicine> getMedicines() {
        return medicines;
    }



    public Professional getProfessional() {
        return professional;
    }

    public static void saveToRealm(final TreatmentResponse response, Realm realm) {

        for(final TreatmentResponse.DataBean dataBean:response.getData()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(mapTreatmentData(dataBean));
                }
            });
        }
    }

    private static Treatment mapTreatmentData(TreatmentResponse.DataBean data) {
        return new Treatment(data);
    }
}
