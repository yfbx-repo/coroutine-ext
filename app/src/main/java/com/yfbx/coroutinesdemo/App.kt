package com.yfbx.coroutinesdemo

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * @Author: Edward
 * @Date: 2019-06-26
 * @Description:
 */
class App : Application(), Application.ActivityLifecycleCallbacks {

    companion object {
        lateinit var app: App
        private val stack = mutableListOf<Activity>()
        fun stackTop() = stack.first()
    }


    override fun onCreate() {
        super.onCreate()
        app = this
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        activity?.let { stack.add(0, it) }
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
        activity?.let { stack.remove(it) }
    }
}