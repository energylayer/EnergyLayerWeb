package com.energylayer.dao;

import com.energylayer.entity.Device;

import java.util.List;

/**
 * @author rkotelnikov
 */
public interface DeviceDao extends Dao<Device, Integer> {

    List<Device> find(Integer userId);

}
