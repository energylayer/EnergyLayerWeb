package com.energylayer.service;

import com.energylayer.entity.Device;

import java.util.List;

/**
 * @author rkotelnikov
 */
public interface DeviceService {

    void save(String deviceId, Integer userId);

    List<Device> findDevices(Integer userId);
}
