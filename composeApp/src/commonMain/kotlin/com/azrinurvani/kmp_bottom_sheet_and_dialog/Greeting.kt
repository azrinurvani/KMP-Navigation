package com.azrinurvani.kmp_bottom_sheet_and_dialog

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}