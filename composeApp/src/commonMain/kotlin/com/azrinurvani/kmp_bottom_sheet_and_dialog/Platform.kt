package com.azrinurvani.kmp_bottom_sheet_and_dialog

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform