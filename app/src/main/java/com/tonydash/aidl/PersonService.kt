package com.tonydash.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.tonydash.aidl.bean.IPersonAidlInterface
import com.tonydash.aidl.bean.Person

class PersonService:Service() {

    private val personList = ArrayList<Person>();

    private val personBinder:IPersonAidlInterface.Stub =object : IPersonAidlInterface.Stub(){
        override fun addPersonIn(person: Person?) {
            Log.d("TAG","我是服务器，我收到一个客户端发过来的人：name = ${person?.name}")
        }

    }

    override fun onCreate() {
        super.onCreate()
        Log.d("PersonService","PersonService onCreate")
//        for (i in 1 .. 5){
//            personList.add(Person("name$i",i))
//        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return personBinder
    }
}