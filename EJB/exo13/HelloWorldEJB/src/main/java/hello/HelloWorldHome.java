package hello;

import jakarta.ejb.EJBHome;
import jakarta.ejb.CreateException;
import java.rmi.RemoteException;

public interface HelloWorldHome extends EJBHome {
    HelloWorld create() throws CreateException, RemoteException;
}
