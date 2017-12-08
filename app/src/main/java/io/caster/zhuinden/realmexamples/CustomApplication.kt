package io.caster.zhuinden.realmexamples

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Zhuinden on 2017.12.08..
 */
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build())
    }
}