package io.caster.zhuinden.realmexamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getDefaultInstance()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm?.close()
        realm = null
    }
}
