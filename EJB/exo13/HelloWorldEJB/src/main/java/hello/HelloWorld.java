package hello;

import jakarta.ejb.Remote;
import java.rmi.RemoteException;

@Remote
public interface HelloWorld {
    String sayHello() throws RemoteException;
}
