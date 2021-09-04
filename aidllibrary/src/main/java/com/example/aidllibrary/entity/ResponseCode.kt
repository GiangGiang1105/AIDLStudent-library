package com.example.aidllibrary.entity

@Suppress("UNUSED")
@Retention(AnnotationRetention.SOURCE)
annotation class ResponseCode {

    companion object {
        const val ERROR_INSERT = -1
        const val ERROR_UPDATE = -2
        const val ERROR_DELETE = -3
        const val ERROR_SELECT_STUDENTS = -4
        const val OK = 0
    }
}
