package com.energylayer.service;

import com.energylayer.entity.Data;
import com.energylayer.model.DataQuery;

/**
 * @author: rkotelnikov
 */
public interface DataService {

    void processData(DataQuery data);

    Integer[] getData(int deviceId, int sensorNumber, int count);
}
