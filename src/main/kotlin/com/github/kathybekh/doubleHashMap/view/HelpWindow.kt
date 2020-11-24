package com.github.kathybekh.doubleHashMap.view

import javafx.geometry.Insets
import javafx.scene.image.ImageView
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*


class HelpWindow : View() {
    override val root = BorderPane()

    init {
        title = "Double Hashing Map Rules"
        with(root) {
            background = Background(
                BackgroundFill(Color.WHITESMOKE , CornerRadii.EMPTY, Insets.EMPTY)
            )
            top {
                val t = label(
                    " Данная таблица является графическим представлением хеш-таблицы с двойным хешированием. \n" +
                            "Двойное хеширование (англ. double hashing) — метод борьбы с коллизиями, возникающими при открытой \n" +
                            "адресации, основанный на использовании двух хеш-функций для построения различных \n " +
                            "последовательностей исследования хеш-таблицы. " +
                            "Здесь вы можете посмотреть наглядно функционал хеш-таблицы. \n" +
                            "Слева отображается содержимое хеш-таблицы в виде 'index| key| value'(индекс| ключ| значение).\n" +
                            "Справа отображаются поля для ввода 'key'(ключ) и 'value'(значение), а также функциональные кнопки.\n" +
                            "Функции кнопок: \n" +
                            "ADD -  добавляет новую пару 'ключ-значение' в вашу хеш-таблицу. Добавление осуществляется при \n" +
                            "заполненых полях 'key' и 'value'. \n" +
                            "FIND - осуществляет поиск значения в таблице по ключу. Поиск осуществляется при заполненом поле \n" +
                            "'key'. Если ключ не найден или поле 'key' не заполнено показывается окно с соответствующим \n" +
                            "сообщением.\n" +
                            "DELETE - удаляет пару 'ключ - значение' из таблицы. Удаление производится при заполненом поле \n" +
                            "'key'. \n" +
                            "LOAD - позволяет заполнить таблицу данными из файла с расширение .txt. В таблицу добавляются\n" +
                            "пары с уникальными ключами. \n" +
                            "CLEAR - удаляет все значения из таблицы безвозратно.\n"
                )
                t.font = Font("Arial", 20.0)
                t.textFill = Color.web("#1a237e")
            }
        }

    }
}