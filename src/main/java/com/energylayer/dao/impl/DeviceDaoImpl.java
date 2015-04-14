package com.energylayer.dao.impl;

import com.energylayer.dao.AbstractDao;
import com.energylayer.dao.DeviceDao;
import com.energylayer.entity.Device;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rkotelnikov
 */
@Repository
public class DeviceDaoImpl extends AbstractDao<Device, Integer> implements DeviceDao {

    protected DeviceDaoImpl() {
        super(Device.class);
    }

    @Override
    public List<Device> find(Integer userId) {
        return list(criteria()
                .createAlias("user", "u")
                .add(Restrictions.eq("u.id", userId)));
    }
}
