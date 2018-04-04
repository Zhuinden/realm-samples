package io.caster.zhuinden.realmexamples

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.caster.zhuinden.realmexamples.models.Person
import io.caster.zhuinden.realmexamples.models.PersonFields
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    private lateinit var persons: RealmResults<Person>

    private val realmChangeListener = RealmChangeListener<RealmResults<Person>> { results ->
        // this will be called when async query is complete, or a write happens to Person in the Realm
        personAdapter.updateData(results)
    }

    private val personAdapter = PersonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = personAdapter

        realm = Realm.getDefaultInstance()
        persons = realm.where<Person>().findAllAsync()
        persons.addChangeListener(realmChangeListener)

        //doAsyncWrites()

        val john = realm.where<Person>().equalTo(PersonFields.NAME, "John Doe").findFirst()

        Log.i("MainActivity", "LOG")
    }

    override fun onDestroy() {
        super.onDestroy()
        persons.removeAllChangeListeners()
        realm.close()
    }

    // simulate async writes
    private val handler: Handler = Handler(Looper.getMainLooper())

    private val executor: Executor = Executors.newSingleThreadExecutor()

    private fun writeAsync(person: Person, delay: Long) {
        handler.postDelayed({
            executor.execute({
                Realm.getDefaultInstance().use { r ->
                    r.executeTransaction { realm ->
                        realm.insertOrUpdate(person)
                    }
                }
            })
        }, delay)
    }

    private fun doAsyncWrites() {
        writeAsync(Person().apply {
            name = "Anonymous"
            birthday = Date()
        }, 1500)

        writeAsync(Person().apply {
            name = "Anonymous 2"
            birthday = Date()
        }, 3000)

        writeAsync(Person().apply {
            name = "Anonymous 3"
            birthday = Date()
        }, 4500)
    }
}
