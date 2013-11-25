package org.br.corbaSupport.service;


/**
* ServicePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Service.idl
* Monday, November 25, 2013 6:45:23 PM BRST
*/

@SuppressWarnings("unchecked")
public abstract class ServicePOA extends org.omg.PortableServer.Servant
 implements ServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  @SuppressWarnings("rawtypes")
private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("updateSensorValues", new java.lang.Integer (0));
    _methods.put ("registerClient", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Service/updateSensorValues
       {
         String sensor_name = in.read_string ();
         String tag_name = in.read_string ();
         String tag_values = in.read_string ();
         int $result = (int)0;
         $result = this.updateSensorValues (sensor_name, tag_name, tag_values);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // Service/registerClient
       {
         String client_name = in.read_string ();
         String first_tag = in.read_string ();
         String second_tag = in.read_string ();
         int $result = (int)0;
         $result = this.registerClient (client_name, first_tag, second_tag);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Service:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Service _this() 
  {
    return ServiceHelper.narrow(
    super._this_object());
  }

  public Service _this(org.omg.CORBA.ORB orb) 
  {
    return ServiceHelper.narrow(
    super._this_object(orb));
  }


} // class ServicePOA
