package org.br.corbaSupport.service;


/**
* ServiceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Service.idl
* Monday, November 25, 2013 2:09:00 PM BRST
*/

public interface ServiceOperations 
{
  int updateSensorValues (String sensor_name, String tag_name, String tag_values);
  int registerClient (String client_name);
} // interface ServiceOperations