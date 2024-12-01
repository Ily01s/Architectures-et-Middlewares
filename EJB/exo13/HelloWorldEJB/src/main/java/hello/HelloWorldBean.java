package hello;

import jakarta.ejb.Stateless;

@Stateless
public class HelloWorldBean implements HelloWorld {
    @Override
    public String sayHello() {
        return "Hello, World!";
    }
}
