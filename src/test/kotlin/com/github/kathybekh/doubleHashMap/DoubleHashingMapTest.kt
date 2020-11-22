package com.github.kathybekh.doubleHashMap

import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DoubleHashingMapTest {

    private val testMapStoS = DoubleHashingMap<String, String>()


    @Test
    fun isEmpty() {
        testMapStoS["first"] = "one"
        val bool = testMapStoS.size == 1 && testMapStoS.containsKey("first") && testMapStoS.containsValue("one")
        assertTrue(bool)
    }

    @Test
    fun a(){

    }
}