package com.energylayer.utils.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

/**
 * @author: rkotelnikov
 */
@Component
public class MonitorManagerImpl implements MonitorManager {

    @Override
    public String heapUsage() {
        return FileUtils.byteCountToDisplaySize(Runtime.getRuntime().totalMemory());
    }
}
