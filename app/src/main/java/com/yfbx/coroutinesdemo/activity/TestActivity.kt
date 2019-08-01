package com.yfbx.coroutinesdemo.activity

import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.coroutines.*

/**
 * @Author: Edward
 * @Date: 2019-08-01
 * @Description:
 */
class TestActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val TAG = "TestActivity"

    /**
     * 非阻塞 6-28ms
     */
    private fun test1() {
        println("任务开始")
        launch {
            val start = System.currentTimeMillis()
            withContext(Dispatchers.IO) { task1() }
            withContext(Dispatchers.IO) { task2() }
            val end = System.currentTimeMillis()
            log("任务耗时:${end - start}毫秒")
        }
        println("任务结束")
    }

    /**
     * 非阻塞 3-13ms
     */
    private fun test2() {
        println("任务开始")
        launch {
            val start = System.currentTimeMillis()
            val task1 = async { task1() }
            val task2 = async { task2() }
            task1.await()
            task2.await()
            val end = System.currentTimeMillis()
            log("任务耗时:${end - start}毫秒")
        }
        println("任务结束")
    }


    /**
     * 任务1
     */
    private fun task1(): String {
        for (i in 1..1000) {
            print(i)
        }
        return "任务1执行完成"
    }

    /**
     * 任务2
     */
    private fun task2(): String {
        for (i in 1..3000) {
            print(i)
        }
        return "任务2执行完成"
    }


    private fun log(msg: String) {
        Log.e(TAG, msg)
    }
}