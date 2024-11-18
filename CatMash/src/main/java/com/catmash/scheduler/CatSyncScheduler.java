package com.catmash.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.catmash.service.CatSyncService;

@Component
public class CatSyncScheduler {

    private final CatSyncService catSyncService;

    public CatSyncScheduler(CatSyncService catSyncService) {
        this.catSyncService = catSyncService;
    }

    /**
     * Periodically synchronizes the list of cats by invoking synchronizeCats() method.
     * Scheduled to run at a fixed rate of 1 hour
     */
    @Scheduled(fixedRate = 3600000)
    public void synchronizeCatsPeriodically() {
        catSyncService.synchronizeCats();
    }
}
