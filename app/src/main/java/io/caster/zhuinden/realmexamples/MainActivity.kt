package io.caster.zhuinden.realmexamples

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
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

    private lateinit var personAdapter: PersonAdapter

    private val changeListener = RealmChangeListener<RealmResults<Person>> { results ->
        // the results was loaded, or has been written to (from any thread)
        personAdapter.updateData(results)

        val john = results.where().equalTo(PersonFields.NAME, "John Doe").findFirst()!!
        val mary = results.where().equalTo(PersonFields.NAME, "Mary Sue").findFirst()!!
        val johnsDad = results.where().equalTo(PersonFields.NAME, "John's Dad").findFirst()!!

        Log.i("MainActivity", "DEBUG")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getDefaultInstance()

        persons = realm.where<Person>().findAllAsync()
        persons.addChangeListener(changeListener)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = PersonAdapter().also {
            personAdapter = it
        }

        writeAsync(1500) {
            insertOrUpdate(Person().apply {
                id = "Someone"
                name = "Anonymous"
                age = 99
                birthday = Date()
            })
        }

        writeAsync(4000) {
            insertOrUpdate(Person().apply {
                id = "Another Person"
                name = "Anonymous 2"
                age = 87
                birthday = Date()
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        persons.removeAllChangeListeners()
        realm.close()
    }

    // background writes
    private val executor: Executor = Executors.newSingleThreadExecutor()

    private val handler: Handler = Handler(Looper.getMainLooper())

    private fun writeAsync(delay: Long, transaction: Realm.() -> Unit) {
        handler.postDelayed({
            executor.execute {
                Realm.getDefaultInstance().use { realm ->
                    realm.executeTransaction { r ->
                        transaction(r)
                    }
                }
            }
        }, delay)
    }
}
