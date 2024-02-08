package com.westeros.tools.safeinvoker;

import com.westeros.tools.safeinvoker.repeaters.IRepeater;
import com.westeros.tools.safeinvoker.repeaters.Repeater;
import com.westeros.tools.safeinvoker.repeaters.RepeaterExceptionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SafeInvokerConfiguration {

    /**
     * Niech Spring w swoim kontenerze DI
     * ma zarejestrowany singleton klasy RepeaterExceptionRegistry
     */
    @Bean
    public RepeaterExceptionRegistry getRegistry(){
        return RepeaterExceptionRegistry.getInstance();
    }

    /**
     * Niech Spring w swoim kontenerze DI
     * ma zarejestrowany obiekt Repeatera
     *  ->   prototype mówi tyle,
     *      że za każdym razem jak Obiekt tego typu będzie potrzebny,
     *      to Spring utworzy jego nową instancję
     */
    @Bean
    @Scope("prototype")
    public Repeater repeater(RepeaterExceptionRegistry registry){
        return new Repeater(registry);
    }

    /**
     * Niech Spring w swoim kontenerze DI
     * ma zarejestrowany obiekt SafeInvoker
     *  ->   prototype mówi tyle,
     *      że za każdym razem jak Obiekt tego typu będzie potrzebny,
     *      to Spring utworzy jego nową instancję
     */
    @Bean
    @Scope("prototype")
    public SafeInvoker safeInvoker(IRepeater repeater){
        return new SafeInvoker(repeater);
    }
}
