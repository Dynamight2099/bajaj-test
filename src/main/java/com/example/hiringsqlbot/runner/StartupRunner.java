package com.example.hiringsqlbot.runner;

import com.example.hiringsqlbot.service.HiringFlowService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final HiringFlowService hiringFlowService;

    public StartupRunner(HiringFlowService hiringFlowService) {
        this.hiringFlowService = hiringFlowService;
    }

    @Override
    public void run(String... args) throws Exception {
        hiringFlowService.runFlow();   
    }
}
