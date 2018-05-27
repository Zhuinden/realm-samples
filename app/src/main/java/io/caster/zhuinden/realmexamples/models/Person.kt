package io.caster.zhuinden.realmexamples.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.Index
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Zhuinden on 2018.04.05..
 */
open class Person : RealmObject() {
    @PrimaryKey
    var name: String? = null

    @Index
    var birthday: Date? = null

    var phoneNumbers: RealmList<String>? = null

    var avatar: ByteArray? = null

    var spouse: Person? = null

    @LinkingObjects("spouse")
    val spouseOf: RealmResults<Person>? = null

    var children: RealmList<Person>? = null

    @LinkingObjects("children")
    val parents: RealmResults<Person>? = null

    var cats: RealmList<Cat>? = null
}