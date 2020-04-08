package com.imed.medcare.model.repository;

/**
 * Created by ismaelalvarez on 14-02-18.
 */

import io.realm.RealmObject;
import io.realm.RealmResults;


public interface GenericRepository<T extends RealmObject> {
    //Sync calls

    /**
     * Add a single object to realm
     *
     * @param object item of type T to be added
     */
    void add(T object);

    /**
     * Search one item of type T by field id
     *
     * @param id value that correspond to the id searched
     * @return T if the object was found, null in other case
     */
    T get(int id, String columnName);

    /**
     * Get all object of type T
     *
     * @return RealmResult list of all items that exists
     */
    RealmResults<T> getAll();

    /**
     * Get all object of type T filtered by a column value
     *
     * @param columnName Name of the column to be filtered
     * @param value      Value of the filter
     * @return List of all coincidences
     */
    RealmResults<T> getAllFiltered(String columnName, int value, String sortedBy);

    RealmResults<T> getAllFilteredAndSorted(String columnName, int value, String sortedBy);

    T getFirst();

    void delete(T object);

    void clear();
}
