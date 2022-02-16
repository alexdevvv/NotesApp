package com.example.notesapp.data.extension

fun randomID(): String = List(16) {
    (('a'..'z') + ('A'..'Z') + ('0'..'9')).random()
}.joinToString("")