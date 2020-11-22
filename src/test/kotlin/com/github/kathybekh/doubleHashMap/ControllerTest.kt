package com.github.kathybekh.doubleHashMap

import com.github.kathybekh.doubleHashMap.controller.DoubleHashingMapController
import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import org.junit.Assert
import org.junit.jupiter.api.Test
import java.io.File

class ControllerTest {



    @Test
    fun loadFileWithDuplicates() {
        val testController = DoubleHashingMapController()
        testController.readFromFile(File("input/dictionary.txt"))

        val expectedContent = DoubleHashingMap<String, String>()
        expectedContent.putAll(
            mapOf("Evolved" to "when a type of plant or animal evolves, its physical form changes over a long period of time.",
                "Undergraduate" to "a student who is studying for a first degree at a college or university. A student who already has a first degree is a graduate.",
                "Tremendously" to "a student who is studying for a first degree at a college or university. A student who already has a first degree is a graduate.",
                "Increasingly" to "more and more over a period of time.",
                "Crystallised" to "crystallized fruits or sweet foods are covered with sugar crystals."))

        Assert.assertEquals(testController.entries(), expectedContent.entries)
    }

    @Test
    fun loadFileWithUncorrectedLines() {
        val testM = DoubleHashingMapController()
        testM.readFromFile(File("input/dic1.txt"))

        val expectedContent = DoubleHashingMap<String, String>()
        expectedContent.putAll(mapOf("Quantitative" to "despite what has just been said. The more usual word is nevertheless.",
        "Nonetheless" to "to stop someone or something from making progress or developing.",
                "Hindered" to "a bar or gate that stops people or vehicles from entering a place.",
                "Ignorance" to "able to influence the way other people think or behave.",
                "Laid" to "exact and accurate."))


        Assert.assertEquals(testM.entries(), expectedContent.entries)
    }
}