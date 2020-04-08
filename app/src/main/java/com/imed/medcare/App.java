package com.imed.medcare;

import android.app.Application;
import android.content.Context;

import com.orhanobut.hawk.Hawk;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by felipe on 19-02-18.
 */

public class App extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        context = getApplicationContext();
        initHawk();
    }

    private void initRealm() {

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(configuration);
    }
    private void initHawk() {
        Hawk.init(this).build();
    }
}
