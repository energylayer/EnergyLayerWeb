package com.energylayer.service.impl;

import com.energylayer.dao.DataDao;
import com.energylayer.entity.Data;
import com.energylayer.model.DataQuery;
import com.energylayer.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: rkotelnikov
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDao dataDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly = false)
    public void processData(DataQuery dataQuery) {
        Data data = getDataFromQuery(dataQuery);
        dataDao.saveOrUpdate(data);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public Integer[] getData(int deviceId, int sensorNumber, int count) {
        return dataDao.find(deviceId, sensorNumber, count);
    }

    private Data getDataFromQuery(DataQuery dataQuery) {
        String dataStr = dataQuery.getData().substring(4,36);
        Data data = new Data();
        data.setDeviceId(dataQuery.getDeviceId());
        data.setData1(Integer.parseInt(dataStr.substring(0,4), 16));
        data.setData2(Integer.parseInt(dataStr.substring(4,8), 16));
        data.setData3(Integer.parseInt(dataStr.substring(8,12), 16));
        data.setData4(Integer.parseInt(dataStr.substring(12,16), 16));
        data.setData5(Integer.parseInt(dataStr.substring(16,20), 16));
        data.setData6(Integer.parseInt(dataStr.substring(20,24), 16));
        data.setData7(Integer.parseInt(dataStr.substring(24,28), 16));
        data.setData8(Integer.parseInt(dataStr.substring(28,32), 16));
        return data;
    }
}
