package com.example.aidllibrary.controller

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.example.aidllibrary.R
import com.example.aidllibrary.entity.FailureResponse
import com.example.aidllibrary.entity.Student
import com.example.aidllibrary.entity.StudentResponse

/**
 * @author Nguyen Ngoc Ha Giang
 * @version 1.0, 04/09/2021
 * @since 1.0
 */

class StudentServiceConnector private constructor(private val context: Context) {

    var serviceConnected = false
    private var studentService: IStudentService? = null
    var callback: Callback? = null

    private val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected")
            serviceConnected = true
            studentService = IStudentService.Stub.asInterface(service)
            try {
                studentService?.registerCallback(studentServiceCallback)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            callback?.onStudentServiceConnected()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected: ")
            serviceConnected = false
            studentService = null
        }

    }

    private val studentServiceCallback = object : IStudentServiceCallback.Stub() {

        override fun onGetAllStudentResponse(response: StudentResponse) {
            callback?.onGetAllStudentResponse(response)
        }

        override fun onInsertStudentResponse(response: StudentResponse) {
            callback?.onInsertStudentResponse(response)
        }

        override fun onUpdateStudentResponse(response: StudentResponse) {
            callback?.onUpdateStudentResponse(response)
        }

        override fun onDeleteStudentResponse(response: StudentResponse) {
            callback?.onDeleteStudentResponse(response)
        }

        override fun onFailureResponse(failureResponse: FailureResponse) {
            callback?.onFailureResponse(failureResponse)
        }

    }

    fun connectService() {
        if (serviceConnected) {
            Log.d(TAG, "connectService: service was already connected. Ignoring...")
            return
        }
        val intent = Intent()
        intent.apply {
            component =
                ComponentName.unflattenFromString(context.getString(R.string.component_student_service))
            action = context.getString(R.string.action_student_service)
        }
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun getAllStudentRequest() {
        if (!serviceConnected) {
            Log.d(TAG, "getAllStudentRequest: service is not connected.. Ignoring.......")
            return
        }
        try {
            studentService?.getAllStudentRequest()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun insertStudentRequest(student: Student) {
        if (!serviceConnected) {
            Log.d(TAG, "insertStudentRequest: service is not connected. Ignoring...")
            return
        }
        try {
            studentService?.insertStudentRequest(student)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun updateStudentRequest(student: Student) {
        if (!serviceConnected) {
            Log.d(TAG, "updateStudentRequest: service is not connected. Ignoring...")
            return
        }
        try {
            studentService?.updateStudentRequest(student)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun deleteStudentRequest(student: Student) {
        if (!serviceConnected) {
            Log.d(TAG, "deleteStudentRequest: service is not connected. Ignoring...")
            return
        }
        try {
            studentService?.deleteStudentRequest(student)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun disconnectService() {
        if (!serviceConnected) {
            Log.d(TAG, "disconnectService:  service is not connected. Ignoring...")
            return
        }
        try {
            studentService?.unregisterCallback(studentServiceCallback)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        context.unbindService(serviceConnection)
        serviceConnected = false
    }

    class Builder(context: Context) {

        private val connectorClient = StudentServiceConnector(context)

        fun setCallback(cb: Callback): Builder {
            connectorClient.callback = cb
            return this@Builder
        }

        fun build(): StudentServiceConnector = connectorClient
    }

    interface Callback {

        fun onStudentServiceConnected()

        fun onGetAllStudentResponse(response: StudentResponse)

        fun onInsertStudentResponse(response: StudentResponse)

        fun onUpdateStudentResponse(response: StudentResponse)

        fun onDeleteStudentResponse(response: StudentResponse)

        fun onFailureResponse(response: FailureResponse)
    }

    companion object {
        val TAG: String = StudentServiceConnector::class.java.name
    }
}