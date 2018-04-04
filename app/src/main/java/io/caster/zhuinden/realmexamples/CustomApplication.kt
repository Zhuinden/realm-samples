package io.caster.zhuinden.realmexamples

import android.app.Application
import io.caster.zhuinden.realmexamples.models.Person
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import java.util.*

/**
 * Created by Zhuinden on 2018.04.05..
 */
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .initialData { realm ->
                val john = realm.createObject<Person>("John Doe").apply {
                    birthday = Calendar.getInstance().apply {
                        set(Calendar.DAY_OF_MONTH, 1) // ensure valid date
                        set(Calendar.YEAR, 1992)
                        set(Calendar.MONTH, Calendar.FEBRUARY)
                        set(Calendar.DAY_OF_MONTH, 22)
                    }.time

                    phoneNumbers!!.run {
                        add("+36 1 123 4567")
                        add("+36 1 345 4432")
                    }
                }

                val mary = realm.createObject<Person>("Mary Sue").apply {
                    birthday = Calendar.getInstance().apply {
                        set(Calendar.DAY_OF_MONTH, 1) // ensure valid date
                        set(Calendar.YEAR, 1991)
                        set(Calendar.MONTH, Calendar.OCTOBER)
                        set(Calendar.DAY_OF_MONTH, 4)
                    }.time

                    phoneNumbers!!.run {
                        add("+36 1 413 1412")
                    }
                }

                val johnsDad = realm.createObject<Person>("John's Dad").apply {
                    birthday = Calendar.getInstance().apply {
                        set(Calendar.DAY_OF_MONTH, 1) // ensure valid date
                        set(Calendar.YEAR, 1966)
                        set(Calendar.MONTH, Calendar.NOVEMBER)
                        set(Calendar.DAY_OF_MONTH, 2)
                    }.time
                }

                john.spouse = mary
                mary.spouse = john

                johnsDad.children!!.add(john)
            }
            .build())
    }
}