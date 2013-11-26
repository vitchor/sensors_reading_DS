package org.br.main;

import org.br.servant.ServiceServant;
import org.br.util.Names;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class ServiceMain {

	public static void main(String args[]) {
		try {

			System.out.println("Initializing Service...");

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

			// Create instance of a Service Servant
			ServiceServant serviceServant = new ServiceServant(namingContext, orb);

			// Register it with the ORB and get the reference to servant
			org.omg.CORBA.Object serviceServantCORBA = rootpoa
					.servant_to_reference(serviceServant);

			// Bind the reference to the Naming Service
			NameComponent[] servicePath = { new NameComponent(
					Names.SERVICE_NAME_CONTEXT, "Object") };
			namingContext.rebind(servicePath, serviceServantCORBA);

			System.out.println("Service initialized");

			// Run orb to keep it alive and safe from the garbage collector
			orb.run();

		} catch (Exception e) {
			System.err.println("Error: " + e);
			e.printStackTrace(System.out);
		}
	}
}
