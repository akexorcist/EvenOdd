package com.akexorcist.evenodd

open class SingleEvent<out T>(private val content: T) {
    var isConsumed = false
        private set // Allow external read but not write

    fun consume(): T? {
        return if (isConsumed) {
            null
        } else {
            isConsumed = true
            content
        }
    }

    fun peek(): T = content

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SingleEvent<*>

        if (content != other.content) return false
        if (isConsumed != other.isConsumed) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content?.hashCode() ?: 0
        result = 31 * result + isConsumed.hashCode()
        return result
    }
}
