package io.caster.zhuinden.realmexamples

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.Index
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Zhuinden on 2017.12.09..
 */
open class Person : RealmObject() {
    @PrimaryKey
    var id: String? = null

    @Index
    var name: String? = null

    @Index
    var age: Int = 0 // implicitly @Required

    var birthday: Date? = null

    var image: ByteArray? = null

    var phoneNumbers: RealmList<String>? = null

    var spouse: Person? = null

    @LinkingObjects("spouse")
    val spouseOf: RealmResults<Person>? = null

    var parents: RealmList<Person>? = null

    @LinkingObjects("parents")
    val children: RealmResults<Person>? = null
}