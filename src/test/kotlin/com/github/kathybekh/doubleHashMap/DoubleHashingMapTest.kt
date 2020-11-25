package com.github.kathybekh.doubleHashMap

import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import com.github.kathybekh.doubleHashMap.model.Entry
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

// Тесты покрывают код если они заходят в каждую функцию и в каждый if в этих функциях.

class DoubleHashingMapTest {

    private val testMapStoS = DoubleHashingMap<String, String>()
    private val testMapItoS = DoubleHashingMap<Int, String>()
    private val entryMap = mapOf(1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine",
        10 to "ten",
        11 to "eleven",
        12 to "twelve",
        13 to "thirty",
        14 to "forty",
        15 to "fifteen")

    @Test
    fun testPut() {
        testMapStoS["first"] = "one"
        val bool = testMapStoS.size == 1 && testMapStoS.containsKey("first") && testMapStoS.containsValue("one")
        assertTrue(bool)
        testMapStoS.clear()
    }

    @Test
    fun testResizeAndPutAll(){
        testMapItoS.putAll(entryMap)
        assertTrue(testMapItoS.size == 15)
    }


    @Test
    fun testFind() {
        testMapItoS.putAll(entryMap)
        val findElement = testMapItoS.find(3)
        assertEquals(findElement, Entry(3, "three"))
    }

    @Test
    fun testNotFind() {
        testMapItoS.putAll(entryMap)
        testMapItoS.remove(3)
        val findElement = testMapItoS.find(3)
        assertEquals(findElement, null)
    }

    @Test
    fun testFindNull() {
        testMapItoS.putAll(entryMap)
        val findElement = testMapItoS.find(21)
        assertEquals(findElement, null)
    }

    @Test
    fun testDelete() {
        testMapItoS.putAll(entryMap)
        testMapItoS.remove(4)
        assertTrue(testMapItoS.size == 14)
        assertFalse(Entry(4, "four") in testMapItoS.entries)
    }

    @Test
    fun testIsEmpty() {
        assertTrue(testMapStoS.isEmpty())
    }

    @Test
    fun testIsNotEmpty() {
        testMapItoS.putAll(entryMap)
        assertFalse(testMapItoS.isEmpty())
    }

    @Test
    fun doubleKey() {
        testMapItoS.putAll(entryMap)
        testMapItoS[5] = "new value"
        assertFalse(testMapItoS[5] == "five")
        assertTrue(testMapItoS[5] == "new value")
    }

}