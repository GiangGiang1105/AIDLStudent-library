package com.example.aidllibrary.entity

@Suppress("UNUSED")
@Retention(AnnotationRetention.SOURCE)
annotation class RequestCode {
    companion object {
        const val GET_STUDENTS = 0
        const val INSERT_REQ = 1
        const val UPDATE_REQ = 2
        const val DELETE_REQ = 3
    }
}
