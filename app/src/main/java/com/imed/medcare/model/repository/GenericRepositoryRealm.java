package com.imed.medcare.model.repository;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

public class GenericRepositoryRealm <T  extends RealmObject>  implements GenericRepository<T>{

    private final Class<T> type;
    private Realm realm;

    public GenericRepositoryRealm(Class<T> type) {
        this.type = type;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void add(final T object) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(object);
            }
        });
    }

    @Override
    public T get(int id, String columnName) {
        return realm.where(type).equalTo(columnName, id).findFirst();
    }

    @Override
    public RealmResults<T> getAll() {
        return realm.where(type).findAll();
    }

    @Override
    public void clear() {
    }
    @Override
    public RealmResults<T> getAllFiltered(String columnName, int value, String sortedBy) {
        return realm.where(type).equalTo(columnName, value).findAll().sort(columnName);
    }

    @Override
    public RealmResults<T> getAllFilteredAndSorted(String columnName, int value, String sortedBy) {
        return realm.where(type).equalTo(columnName, value).findAllSorted(sortedBy, Sort.ASCENDING);
    }

    @Override
    public T getFirst() {
        return realm.where(type).findFirst();
    }

    @Override
    public void delete(T object) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        object.deleteFromRealm();
        realm.commitTransaction();
    }


    public static void closeSession() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
}
