package com.example;

import com.example.safe.SafeInvoker;
import com.example.safe.SafeInvokerConfiguration;
import com.example.safe.repeaters.IRepeaterExceptionRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class SafeInvokerApplication implements CommandLineRunner {

    private SafeInvoker safeInvoker;
    private IRepeaterExceptionRegistry registry;

    public SafeInvokerApplication(SafeInvoker safeInvoker, IRepeaterExceptionRegistry registry) {
        this.safeInvoker = safeInvoker;
        this.registry = registry;
    }

    public static void main(String[] args) {
        SpringApplication.run(SafeInvokerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        registry.add(Exception.class, 3, 1000);

        safeInvoker.SafeInvoke(()->sometimesItWorksFine())
                .onUnhandledException(ex->ex.printStackTrace());
    }

    static void sometimesItWorksFine() throws Exception {
        var random = new Random().nextInt(11);
        if(random<1) {
            System.out.println("DZIAŁAM !!");
            return;
        }
        throw new Exception();
    }
}
