package com.tonydash.client

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tonydash.aidl.bean.IPersonAidlInterface
import com.tonydash.aidl.bean.Person


class MainActivity : AppCompatActivity() {

    var tvName:TextView? = null
    var iPerson:IPersonAidlInterface? = null

    val connection:ServiceConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iPerson = IPersonAidlInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvName = findViewById(R.id.tvName)
        tvName?.setOnClickListener {
            val person = Person()
            person.name = "奔波儿灞"
            person.age = 500
            iPerson?.addPersonIn(person)
        }
        connectService()
    }

    private fun connectService() {
        val intent = Intent()
        //服务端包名
        intent.setPackage("com.tonydash.aidl")
        //服务端设置的 action
        intent.action = "com.tiga.action.aidl"
        bindService(intent, connection, BIND_AUTO_CREATE)
    }
}