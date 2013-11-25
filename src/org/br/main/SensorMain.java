package org.br.main;

import org.br.corbaSupport.service.Service;
import org.br.corbaSupport.service.ServiceHelper;
import org.br.servant.SensorServant;
import org.br.util.Names;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class SensorMain {

	public static void main(String args[]) {
		try {

			System.out.println("Initializing Sensor...");

			// Create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// Get a reference from rootpoa & Activate the POAManager
			POA rootpoa = POAHelper.narrow(orb
					.resolve_initial_references(Names.ROOT_POA));
			rootpoa.the_POAManager().activate();

			// get the root naming context
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references(Names.NAME_SERVICE);
			NamingContext namingContext = NamingContextHelper.narrow(objRef);

			NameComponent[] serviceName = { new NameComponent(
					Names.SERVICE_NAME_CONTEXT, "Object") };

			Service service = ServiceHelper.narrow(namingContext
					.resolve(serviceName));

			//String tag = args[0]; // First argument should be the type (T, P, H)
			//String name = args[1]; // Second argument should be the name
			String tag = "T";
			String name = "Sala";
			
			SensorServant sensorServant = new SensorServant(name, tag, service);
			
			org.omg.CORBA.Object sensorServantCORBA = rootpoa
					.servant_to_reference(sensorServant);

			// Bind the reference to the Naming Service
			NameComponent[] servicePath = { new NameComponent(
					Names.SENSOR_NAME_CONTEXT + tag + "_" + name, "Object") };
			namingContext.rebind(servicePath, sensorServantCORBA);

			System.out.println("Sensor initialized");

			// Run orb to keep it alive and safe from the garbage collector
			orb.run();

		} catch (Exception e) {
			System.out.println("Error " + e);
			e.printStackTrace(System.out);
		}
	}
}