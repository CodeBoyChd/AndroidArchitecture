package com.sample.room.activity

import android.support.v7.app.AppCompatActivity
import com.sample.room.utility.NetworkUtil

open class BaseActivity : AppCompatActivity() {

    fun isNetworkConnected(): Boolean = NetworkUtil.isNetworkConnected(this)
}