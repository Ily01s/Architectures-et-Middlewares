import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import hello.HelloWorld;
import java.rmi.RemoteException;

public class HelloClient {
    public static void main(String[] args) throws RemoteException {
        try {
            // JNDI Context Configuration
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

            // Initialize Context
            Context ctx = new InitialContext(env);

            // Perform JNDI Lookup
            HelloWorld hello = (HelloWorld) ctx.lookup("ejb:/HelloWorldEJB-1.0-SNAPSHOT/HelloWorld!hello.HelloWorld");

            // Call Remote Method
            System.out.println(hello.sayHello());
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
