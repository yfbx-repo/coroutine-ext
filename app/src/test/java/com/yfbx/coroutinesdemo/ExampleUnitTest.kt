package com.yfbx.coroutinesdemo

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun test1() {

        GlobalScope.launch {
            val start = System.currentTimeMillis()
            println(Thread.currentThread().name)
            task1()
            task2()
            val end = System.currentTimeMillis()
            println("任务耗时:${end - start}毫秒")
        }

    }

    @Test
    fun test2() {
        val start = System.currentTimeMillis()
        GlobalScope.launch {
            val task1 = async { task1() }
            val task2 = async { task2() }
            task1.await()
            task2.await()

        }
        val end = System.currentTimeMillis()
        println("任务耗时:${end - start}毫秒")
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

}
