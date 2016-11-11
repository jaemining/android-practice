package jaemin.android.com.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/*
    기본 자료형
    정수 - Long, Int, Short, Byte
    실수 - Double, Float
    문자 - Char
    논리 - Boolean
 */
class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        sayHello("Kotlin")
    }

    fun sayHello(str: String) {
        println("Hello " + str)
    }

    fun sum(a: Int, b: Int) : Int {

        return a + b
    }
}

open class MyClass {

}

interface MyInterface {

}