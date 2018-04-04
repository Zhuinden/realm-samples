package io.caster.zhuinden.realmexamples.models

import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by Zhuinden on 2018.04.05..
 */
@RealmClass
open class Cat : RealmModel {
    @PrimaryKey
    var name: String? = null

    @LinkingObjects("cats")
    val owners: RealmResults<Person>? = null
}