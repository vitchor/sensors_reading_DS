package org.br.main;

import org.br.corbaSupport.service.Service;
import org.br.corbaSupport.service.ServiceHelper;
import org.br.servant.ClientServant;
import org.br.util.Names;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class ClientMain {
	public static void main(String args[]) {
		try {

			System.out.println("Initializing Client...");

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

			String clientName = args[0];
			String firstTag = args[1];
			String secondTag = args[2];
			
//			String clientName = "FUNCIONARIO";
//			String firstTag = "T";
//			String secondTag = "H";
			
			ClientServant clientServant = new ClientServant(clientName, firstTag, secondTag);

			org.omg.CORBA.Object clientServantCORBA = rootpoa
					.servant_to_reference(clientServant);
			
			final String clientNameServiceName = Names.CLIENT_NAME_CONTEXT + "_" + clientName;
			
			// Bind the reference to the Naming Service
			NameComponent[] servicePath = { new NameComponent(
					clientNameServiceName, "Object") };
			namingContext.rebind(servicePath, clientServantCORBA);
			
			NameComponent[] serviceName = { new NameComponent(
					Names.SERVICE_NAME_CONTEXT, "Object") };

			Service service = ServiceHelper.narrow(namingContext
					.resolve(serviceName));
			
			service.registerClient(clientNameServiceName, firstTag, secondTag);

			System.out.println("Client initialized");
			
			// Run orb to keep it alive and safe from the garbage collector
			orb.run();

		} catch (Exception e) {
			System.out.println("Error " + e);
			e.printStackTrace(System.out);
		}
	}
}
