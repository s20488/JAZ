package com.westeros.tools.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerThread implements Runnable {

    private final Scheduler scheduler;

    @Override
    public void run() {
        while(true){
            scheduler
                    .getJobs()
                    .stream()
                    .forEach(job->job.execute());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
