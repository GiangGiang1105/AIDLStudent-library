package com.example.aidllibrary.controller

import android.content.Context
import android.util.Log
import com.example.aidllibrary.base.CallbackProvider
import com.example.aidllibrary.entity.FailureResponse
import com.example.aidllibrary.entity.Student
import com.example.aidllibrary.entity.StudentResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentServiceController(context: Context) : StudentServiceConnector.Callback,
    CallbackProvider<StudentServiceConnector.Callback> {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val serviceProvider = StudentServiceConnector.Builder(context)
        .setCallback(this)
        .build()

    override val callbacks: ArrayList<StudentServiceConnector.Callback> = ArrayList()

    override fun addCallback(callback: StudentServiceConnector.Callback) {
        super.addCallback(callback)
        if (callbacks.size > 0) {
            connect()
        }
    }

    override fun removeCallback(callback: StudentServiceConnector.Callback) {
        super.removeCallback(callback)
        if (callbacks.size == 0) {
            disconnect()
        }
    }

    private fun connect() {
        serviceProvider.connectService()
    }

    private fun disconnect() {
        serviceProvider.disconnectService()
    }

    fun getAllStudent() {
        serviceProvider.getAllStudentRequest()
    }

    fun insertStudent(student: Student) {
        serviceProvider.insertStudentRequest(student)
    }

    fun updateStudent(student: Student) {
        serviceProvider.updateStudentRequest(student)
    }

    fun deleteStudent(student: Student) {
        serviceProvider.deleteStudentRequest(student)
    }

    override fun onStudentServiceConnected() {
        Log.e("TAG", "onStudentServiceConnected: 1", )
        Log.e("TAG", "onStudentServiceConnected: 2 ${callbacks.size}", )
        coroutineScope.launch {
            callbacks.forEach {
                Log.e("TAG", "onStudentServiceConnected: 3 ${callbacks.size}", )
                it.onStudentServiceConnected()
            }
        }
    }

    override fun onGetAllStudentResponse(response: StudentResponse) {
        Log.e("TAG", "onGetAllStudentResponse: 1", )
        Log.e("TAG", "onGetAllStudentResponse: 3 ${callbacks.size}", )
        coroutineScope.launch {
            callbacks.forEach {
                Log.e("TAG", "onGetAllStudentResponse: 2", )
                it.onGetAllStudentResponse(response)
            }
        }
    }

    override fun onInsertStudentResponse(response: StudentResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onInsertStudentResponse(response)
            }
        }
    }

    override fun onUpdateStudentResponse(response: StudentResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onUpdateStudentResponse(response)
            }
        }
    }

    override fun onDeleteStudentResponse(response: StudentResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onDeleteStudentResponse(response)
            }
        }
    }

    override fun onFailureResponse(response: FailureResponse) {
        coroutineScope.launch {
            callbacks.forEach {
                it.onFailureResponse(response)
            }
        }
    }
}