// IStudentServiceCallback.aidl
package com.example.aidllibrary.controller;

import com.example.aidllibrary.entity.StudentResponse;
import com.example.aidllibrary.entity.FailureResponse;

// Declare any non-default types here with import statements

interface IStudentServiceCallback {

/**
    * Called upon get all student request process.
    */
    void onGetAllStudentResponse(in StudentResponse response);

/**
    * Called upon insert student request process.
    */
    void onInsertStudentResponse(in StudentResponse response);

/**
    * Called upon update student request process.
    */
    void onUpdateStudentResponse(in StudentResponse response);

/**
    * Called upon delete student request process.
    */
    void onDeleteStudentResponse(in StudentResponse response);

 /**
     * Called upon any failure occurs with request process.
     */
    void onFailureResponse(in FailureResponse failureResponse);
}