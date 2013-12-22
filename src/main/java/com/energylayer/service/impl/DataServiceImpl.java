package com.energylayer.service.impl;

import com.energylayer.dao.DataDao;
import com.energylayer.entity.Data;
import com.energylayer.service.DataService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: rkotelnikov
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDao dataDao;

    @Value("${cache.array.length}")
    private int cacheArrayLength;

    private Integer[] emptyArray;

    private final ConcurrentMap<Integer, Integer[]> dataMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        emptyArray = new Integer[cacheArrayLength];
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public void processData(Data data) {
        if(!dataMap.containsKey(data.getDeviceId())){
            synchronized (dataMap) {
                dataMap.putIfAbsent(data.getDeviceId(), new Integer[cacheArrayLength]);
            }
        }
        saveData(data);
    }

    @Override
    public Integer getData(int deviceId) {
        Integer[] dataArray = dataMap.get(deviceId);
        return getLastNotNull(dataArray);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED, readOnly = true)
    public Integer[] getAggregatedData(int deviceId, int number) {
        return dataDao.find(deviceId, number);
    }

    private Integer getLastNotNull(Integer[] array) {
        if(array == null){
            return null;
        }
        Integer value = null;
        for (Integer integer : array) {
            if (integer == null){
                break;
            }
            value = integer;
        }
        return value;
    }

    private void saveData(Data data) {
        Integer[] dataArray = dataMap.get(data.getDeviceId());
        synchronized (dataArray) {
            addToArray(dataArray, data.getData());
            if(dataArray[dataArray.length - 1] != null){
                flushData(data.getDeviceId(), dataArray);
                eraseArray(dataArray);
            }
        }
    }

    private void addToArray(Integer[] dataArray, int value) {
        for (int i = 0; i < dataArray.length; i++) {
            Integer integer = dataArray[i];
            if (integer == null){
                dataArray[i] = value;
                return;
            }
        }
    }

    private void eraseArray(Integer[] dataArray) {
        System.arraycopy(emptyArray, 0, dataArray, 0, dataArray.length);
    }

    public void flushData(Integer deviceId, Integer[] dataArray) {
        Data data = new Data();
        data.setDeviceId(deviceId);
        data.setData(mean(dataArray));
        dataDao.saveOrUpdate(data);
    }

    private int mean(Integer[] dataArray) {
        int mean = 0;
        for (Integer integer : dataArray) {
            mean+=integer;
        }
        return mean/dataArray.length;
    }

    public void setDataDao(DataDao dataDao) {
        this.dataDao = dataDao;
    }

    public void setCacheArrayLength(int cacheArrayLength) {
        this.cacheArrayLength = cacheArrayLength;
    }
}
