package io.caster.zhuinden.realmexamples

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList

/**
 * Created by Zhuinden on 2017.12.08..
 */
class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .initialData { realm ->
                    with(realm) {
                        val john = copyToRealmOrUpdate(Person().apply {
                            this.id = "1"
                            this.name = "John Doe"
                            this.age = 26
                            this.phoneNumbers = RealmList<String>().apply {
                                add("+36201234567")
                                add("+36707654321")
                            }
                        })

                        val mary = copyToRealmOrUpdate(Person().apply {
                            this.id = "2"
                            this.name = "Mary Sue"
                            this.age = 24
                            this.phoneNumbers = RealmList<String>().apply {
                                add("+36304215678")
                            }
                        })

                        val johnsDad = copyToRealmOrUpdate(Person().apply {
                            this.id = "3"
                            this.name = "John's Dad"
                            this.age = 54
                        })

                        val johnsMom = copyToRealmOrUpdate(Person().apply {
                            this.id = "4"
                            this.name = "John's Mom"
                            this.age = 51
                        })

                        val marysDad = copyToRealmOrUpdate(Person().apply {
                            this.id = "5"
                            this.name = "Mary's Dad"
                            this.age = 48
                        })

                        val marysMom = copyToRealmOrUpdate(Person().apply {
                            this.id = "6"
                            this.name = "Mary's Mom"
                            this.age = 46
                        })

                        val johnsSon = copyToRealmOrUpdate(Person().apply {
                            this.id = "7"
                            this.name = "John's and Mary's Son"
                            this.age = 3
                        })

                        john.parents?.add(johnsDad)
                        john.parents?.add(johnsMom)
                        mary.parents?.add(marysDad)
                        mary.parents?.add(marysMom)
                        johnsSon.parents?.add(john)
                        johnsSon.parents?.add(mary)

                        john.spouse = mary
                        mary.spouse = john
                        johnsDad.spouse = johnsMom
                        johnsMom.spouse = johnsDad
                        marysDad.spouse = marysMom
                        marysMom.spouse = marysDad
                    }
                }
                .build())
    }
}