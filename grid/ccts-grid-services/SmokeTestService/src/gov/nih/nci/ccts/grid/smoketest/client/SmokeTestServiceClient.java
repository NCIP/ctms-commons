package gov.nih.nci.ccts.grid.smoketest.client;

import gov.nih.nci.ccts.grid.smoketest.common.SmokeTestServiceI;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;

import org.apache.axis.client.Stub;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;
import org.globus.gsi.GlobusCredential;

/**
 * This class is autogenerated, DO NOT EDIT GENERATED GRID SERVICE ACCESS METHODS.
 * 
 * This client is generated automatically by Introduce to provide a clean unwrapped API to the
 * service.
 * 
 * On construction the class instance will contact the remote service and retrieve it's security
 * metadata description which it will use to configure the Stub specifically for each method call.
 * 
 * @created by Introduce Toolkit version 1.2
 */
public class SmokeTestServiceClient extends SmokeTestServiceClientBase implements SmokeTestServiceI {

    public SmokeTestServiceClient(String url) throws MalformedURIException, RemoteException {
        this(url, null);
    }

    public SmokeTestServiceClient(String url, GlobusCredential proxy) throws MalformedURIException,
                    RemoteException {
        super(url, proxy);
    }

    public SmokeTestServiceClient(EndpointReferenceType epr) throws MalformedURIException,
                    RemoteException {
        this(epr, null);
    }

    public SmokeTestServiceClient(EndpointReferenceType epr, GlobusCredential proxy)
                    throws MalformedURIException, RemoteException {
        super(epr, proxy);
    }

    public static void usage() {
        System.out.println(SmokeTestServiceClient.class.getName() + " -url <service url>");
    }

    public static void main(String[] args) {
        System.out.println("Running the Grid Service Client");
        try {
            if (!(args.length < 2)) {
                SmokeTestServiceClient client = null;
                String url="";
                if (args[0].equals("-url")) {
                    url = args[1];
                    client = new SmokeTestServiceClient(url);
                }
                else {
                    usage();
                    System.exit(1);
                }
                if (args.length <= 4 && args[2].equals("-proxy")) {
                    String proxyFilePath = args[3];
                    client = new SmokeTestServiceClient(url, new GlobusCredential(new FileInputStream(
                                    new File(proxyFilePath))));
                    // place client calls here if you want to use this main as a
                    // test....
                }
                client.ping();
            }
            else {
                usage();
                System.exit(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public org.oasis.wsrf.properties.GetMultipleResourcePropertiesResponse getMultipleResourceProperties(
                    org.oasis.wsrf.properties.GetMultipleResourceProperties_Element params)
                    throws RemoteException {
        synchronized (portTypeMutex) {
            configureStubSecurity((Stub) portType, "getMultipleResourceProperties");
            return portType.getMultipleResourceProperties(params);
        }
    }

    public org.oasis.wsrf.properties.GetResourcePropertyResponse getResourceProperty(
                    javax.xml.namespace.QName params) throws RemoteException {
        synchronized (portTypeMutex) {
            configureStubSecurity((Stub) portType, "getResourceProperty");
            return portType.getResourceProperty(params);
        }
    }

    public org.oasis.wsrf.properties.QueryResourcePropertiesResponse queryResourceProperties(
                    org.oasis.wsrf.properties.QueryResourceProperties_Element params)
                    throws RemoteException {
        synchronized (portTypeMutex) {
            configureStubSecurity((Stub) portType, "queryResourceProperties");
            return portType.queryResourceProperties(params);
        }
    }

    public void ping() throws RemoteException {
        synchronized (portTypeMutex) {
            configureStubSecurity((Stub) portType, "ping");
            gov.nih.nci.ccts.grid.smoketest.stubs.PingRequest params = new gov.nih.nci.ccts.grid.smoketest.stubs.PingRequest();
            gov.nih.nci.ccts.grid.smoketest.stubs.PingResponse boxedResult = portType.ping(params);
        }
    }

}
