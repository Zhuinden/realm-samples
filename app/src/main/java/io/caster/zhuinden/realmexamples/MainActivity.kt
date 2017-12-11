package io.caster.zhuinden.realmexamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.realm.Realm
import io.realm.kotlin.where

class MainActivity : AppCompatActivity() {
    var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val realm = Realm.getDefaultInstance()
        this.realm = realm
        val persons = realm.where<Person>().findAll()
        val person1 = realm.where<Person>().equalTo(PersonFields.NAME, "John Doe").findFirst()
        val person2 = realm.where<Person>().equalTo(PersonFields.NAME, "Mary Sue").findFirst()
        val person3 = realm.where<Person>().equalTo(PersonFields.NAME, "John's Dad").findFirst()
        val person4 = realm.where<Person>().equalTo(PersonFields.NAME, "John's Mom").findFirst()
        val person5 = realm.where<Person>().equalTo(PersonFields.NAME, "Mary's Dad").findFirst()
        val person6 = realm.where<Person>().equalTo(PersonFields.NAME, "Mary's Mom").findFirst()
        val person7 = realm.where<Person>().equalTo(PersonFields.NAME, "John's and Mary's Son").findFirst()
        Log.i(this::class.java.name, "DEBUG")
    }

    override fun onDestroy() {
        super.onDestroy()
        realm?.close()
        realm = null
    }
}
