package com.github.kathybekh.doubleHashMap.model

class Entry<K, V>(override val key: K, override var value: V) : MutableMap.MutableEntry<K, V> {

        override fun setValue(v: V): V {
            val oldValue = value
            value = v
            return oldValue
        }

    override fun toString(): String {
        return "$key=$value"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Entry<*, *>

        return key == other.key
    }

    override fun hashCode(): Int {
        var result = key?.hashCode() ?: 0
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }
}