
/**
* SensorHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Sensor.idl
* Monday, November 25, 2013 3:41:52 PM BRST
*/

public final class SensorHolder implements org.omg.CORBA.portable.Streamable
{
  public Sensor value = null;

  public SensorHolder ()
  {
  }

  public SensorHolder (Sensor initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = SensorHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    SensorHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return SensorHelper.type ();
  }

}
