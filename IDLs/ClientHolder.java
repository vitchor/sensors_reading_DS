
/**
* ClientHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Client.idl
* Tuesday, November 26, 2013 11:12:28 AM BRST
*/

public final class ClientHolder implements org.omg.CORBA.portable.Streamable
{
  public Client value = null;

  public ClientHolder ()
  {
  }

  public ClientHolder (Client initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ClientHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ClientHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ClientHelper.type ();
  }

}
