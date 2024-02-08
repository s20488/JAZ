package com.westeros.tools.schedulers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfiguration {


    @Bean
    public Scheduler scheduler(){
        return Scheduler.getInstance();
    }

}
