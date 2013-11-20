package com.energylayer.service;

import com.energylayer.entity.Data;

/**
 * @author: rkotelnikov
 */
public interface DataService {

    void processData(Data data);

    Integer getData(int deviceId);

    Integer[] getAggregatedData(int deviceId, int number);
}
