package com.resilient.payments.demo.config;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.server.JobActivator;
import org.jobrunr.storage.StorageProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRunrConfig {


    /**
     * Constructor to initialize JobRunr with StorageProvider and ApplicationContext
     * @param storageProvider
     * @param applicationContext
     */
    public JobRunrConfig(StorageProvider storageProvider,ApplicationContext applicationContext) {
        JobRunr.configure()
                .useJobActivator(applicationContext::getBean)
                .useStorageProvider(storageProvider)
                .useBackgroundJobServer(2)
                .useDashboard()
                .initialize();

    }


}