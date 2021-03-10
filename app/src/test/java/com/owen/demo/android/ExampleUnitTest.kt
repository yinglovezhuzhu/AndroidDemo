package com.owen.demo.android

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val color: Int = 0x112233

        val b = color and 0x0000FF
        val g = (color shr 8) and 0x0000ff
        val r = (color shr 16) and 0x0000ff

        val b1 = b and 0x0f
        val b2 = (b shr 4) and 0x0f

        assertEquals(4, 2 + 2)
    }
}