package com.resilient.payments.demo.config;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.storage.StorageProvider;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRunrConfig {


    public JobRunrConfig(StorageProvider storageProvider) {
        JobRunr.configure()
                .useStorageProvider(storageProvider)
                .useBackgroundJobServer(2)
                .useDashboard()
                .initialize();

    }



}