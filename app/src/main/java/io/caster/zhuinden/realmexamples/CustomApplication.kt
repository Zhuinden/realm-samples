package io.caster.zhuinden.realmexamples

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import java.util.*

/**
 * Created by Zhuinden on 2018.03.30..
 */
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .initialData(object : Realm.Transaction {
                override fun execute(realm: Realm) {
                    val john = realm.createObject<Person>("John").apply {
                        name = "John Doe"
                        age = 26
                        birthday = Calendar.getInstance().apply {
                            set(Calendar.DAY_OF_MONTH, 1) // ensure valid date
                            set(Calendar.YEAR, 1992)
                            set(Calendar.MONTH, Calendar.JANUARY)
                            set(Calendar.DAY_OF_MONTH, 2)
                        }.time
                        phoneNumbers?.run {
                            add("+36 1 123 4567")
                            add("+36 1 123 4567")
                        }
                    }

                    val mary = realm.createObject<Person>("Mary").apply {
                        name = "Mary Sue"
                        age = 27
                        birthday = Calendar.getInstance().apply {
                            set(Calendar.DAY_OF_MONTH, 1) // ensure valid date
                            set(Calendar.YEAR, 1990)
                            set(Calendar.MONTH, Calendar.NOVEMBER)
                            set(Calendar.DAY_OF_MONTH, 4)
                        }.time
                        phoneNumbers?.run {
                            add("+36 1 653 4236")
                        }
                    }

                    mary.spouse = john
                    john.spouse = mary

                    val johnsDad = realm.createObject<Person>("JohnsDad").apply {
                        name = "John's Dad"
                        age = 52
                        birthday = Calendar.getInstance().apply {
                            set(Calendar.DAY_OF_MONTH, 1) // ensure valid date
                            set(Calendar.YEAR, 1966)
                            set(Calendar.MONTH, Calendar.FEBRUARY)
                            set(Calendar.DAY_OF_MONTH, 24)
                        }.time
                    }

                    john.parents?.add(johnsDad)
                }
            })
            .build())
    }
}