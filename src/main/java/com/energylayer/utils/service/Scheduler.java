package com.energylayer.utils.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: rkotelnikov
 */
@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private MonitorManager monitorManager;

    @Scheduled(fixedRate = 5_000)
    public void heapUsageLogging(){
        log.info(monitorManager.heapUsage());
    }

}
