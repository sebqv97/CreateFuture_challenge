package com.createfuture.takehome.utils

fun String.removeLastOccurrenceOf(char:String): String {
    val lastCommaIndex = lastIndexOf(char)
    return if (lastCommaIndex != -1) {
        substring(0, lastCommaIndex) + substring(lastCommaIndex + 1)
    } else {
        this // Return the original string if no comma is found
    }
}

fun String.substringUntilString(targetString: String, inclusive: Boolean): String {
    val index = indexOf(targetString)
    val additionalChars = (targetString.length.takeIf { inclusive }) ?: 0
    return if (index != -1) {
        substring(0, index + additionalChars)
    } else {
        this
    }
}
