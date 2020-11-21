package com.github.kathybekh.doubleHashMap

import com.github.kathybekh.doubleHashMap.controller.DoubleHashingMapController
import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import org.junit.Assert
import org.junit.Test
import java.io.File

class ControllerTest {



    @Test
    fun loadFileWithDuplicates() {
        val testController = DoubleHashingMapController()
        testController.readFromFile(File("test/resources/input/dictionary.txt"))

        val expectedContent = DoubleHashingMap<String, String>()
        expectedContent.putAll(
            mapOf("Evolved" to "when a type of plant or animal evolves, its physical form changes over a long period of time.",
                "Undergraduate" to "a student who is studying for a first degree at a college or university. A student who already has a first degree is a graduate.",
                "Tremendously" to "a student who is studying for a first degree at a college or university. A student who already has a first degree is a graduate.",
                "Increasingly" to "more and more over a period of time.",
                "Crystallised" to "crystallized fruits or sweet foods are covered with sugar crystals."))

        Assert.assertEquals(testController, expectedContent)
    }
}