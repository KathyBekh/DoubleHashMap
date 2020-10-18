package com.github.kathybekh.doubleHashMap

import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import org.junit.Test
import org.junit.jupiter.api.Assertions

class DoubleHashingMapTest {

    private val testMapStoS = DoubleHashingMap<String, String>()
    val testMapItoS = DoubleHashingMap<Int, String>()
    val testMapItoD = DoubleHashingMap<Int, Double>()

    @Test
    fun isEmpty() {
        Assertions.assertEquals(testMapStoS, true)
    }
}