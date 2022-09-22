package com.tec.petscard

import android.app.Application

class PetsCardApplication: Application() {
    companion object{
        lateinit var reqResAPI: ReqResAPI
    }

    override fun onCreate() {
        super.onCreate()
        //Volley
        reqResAPI = ReqResAPI.getInstance(this)
    }
}