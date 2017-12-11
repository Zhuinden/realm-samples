package io.caster.zhuinden.realmexamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.realm.Case
import io.realm.Realm
import io.realm.kotlin.where

class MainActivity : AppCompatActivity() {
    var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val realm = Realm.getDefaultInstance()
        this.realm = realm
        val persons = realm.where<Person>().lessThan(PersonFields.AGE, 40).findAll()
        persons.forEach {
            Log.i(this@MainActivity::class.java.name, "Person name: ${it.name}")
        }
        val others = realm.where<Person>()
                .beginGroup()
                .beginsWith(PersonFields.NAME, "John", Case.INSENSITIVE)
                .and()
                .greaterThan(PersonFields.AGE, 6)
                .endGroup()
                .or()
                .contains(PersonFields.NAME, "Mary", Case.INSENSITIVE)
                .findAll()
        others.forEach {
            Log.i(this@MainActivity::class.java.name, "Others person name: ${it.name}")
        }

        val linked = realm.where<Person>()
                .greaterThan(PersonFields.PARENTS.AGE, 7)
                .findAll()
        linked.forEach {
            Log.i(this@MainActivity::class.java.name, "Linked person name: ${it.name}")
        }

        val backlinked = realm.where<Person>()
                .lessThan("${PersonFields.CHILDREN}.${PersonFields.AGE}", 15)
                .findAll()
        backlinked.forEach {
            Log.i(this@MainActivity::class.java.name, "Backlinked person name: ${it.name}")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm?.close()
        realm = null
    }
}
