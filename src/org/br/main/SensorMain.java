package org.br.main;

import org.br.corbaSupport.service.Service;
import org.br.corbaSupport.service.ServiceHelper;
import org.br.servant.SensorServant;
import org.br.util.Names;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class SensorMain {

	public static void main(String args[]) {
		try {

			System.out.println("Initializing Sensor...");

			// Create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContext namingContext = NamingContextHelper.narrow(objRef);
			
			NameComponent[] pathSilo = { new NameComponent(Names.SERVICE_NAME_CONTEXT,
					"Object") };
			
			Service service = ServiceHelper.narrow(namingContext.resolve(pathSilo));
			
			String tag = args[0]; // First argument should be the type (T, P, H)
			String name = args[1]; // Second argument should be the name
			
			SensorServant sensorServant = new SensorServant(name, tag, service);
			
			
			System.out.println("Sensor initialized");
			
		} catch (Exception e) {
			System.out.println("Error " + e);
			e.printStackTrace(System.out);
		}
	}
}
