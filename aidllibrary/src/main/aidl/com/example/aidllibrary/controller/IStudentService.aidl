// IStudentService.aidl
package com.example.aidllibrary.controller;
// Declare any non-default types here with import statements
import com.example.aidllibrary.controller.IStudentServiceCallback;
import com.example.aidllibrary.entity.Student;

/**
 * Example of defining an interface for calling on to a remote service
 * (running in another process).
 *
 * @author Nguyen Ngoc Ha Giang
 * @version 1.0, 04/09/2021
 * @since 1.0
 */
interface IStudentService {

/**
    * Often you want to allow a service to call back to its clients.
    * This shows how to do so, by registering a callback interface with
    * the service.
    */
    void  registerCallback(IStudentServiceCallback callback);

 /**
    * Remove a previously registered callback interface.
    */
    void unregisterCallback(IStudentServiceCallback callback);

 /**
    * Called upon get all student request process.
    */
    void getAllStudentRequest();

/**
    * Called upon insert student request process.
    */
    void insertStudentRequest(in Student student);

/**
    * Called upon update student request process.
    */
    void updateStudentRequest(in Student student);

/**
    * Called upon delete student request process.
    */
    void deleteStudentRequest(in Student student);
}