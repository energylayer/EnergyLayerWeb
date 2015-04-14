package com.energylayer.service.impl;

import com.energylayer.builder.DeviceBuilder;
import com.energylayer.dao.DeviceDao;
import com.energylayer.dao.UserDao;
import com.energylayer.entity.Device;
import com.energylayer.entity.User;
import com.energylayer.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

/**
 * @author rkotelnikov
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = false, isolation = READ_UNCOMMITTED, propagation = REQUIRED)
    public void save(String deviceId, Integer userId) {
        User user = userDao.findById(userId);
        Device device = DeviceBuilder.newInstance().withDeviceId(deviceId).withUser(user).build();
        deviceDao.saveOrUpdate(device);
    }

    @Override
    @Transactional(readOnly = true, isolation = READ_UNCOMMITTED, propagation = REQUIRED)
    public List<Device> findDevices(Integer userId) {
        return deviceDao.find(userId);
    }
}
