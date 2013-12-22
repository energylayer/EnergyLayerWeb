package com.energylayer.service.impl;

import com.energylayer.dao.DataDao;
import com.energylayer.entity.Data;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: rkotelnikov
 */

public class DataServiceImplTest {

    private DataServiceImpl dataServiceImpl;
    private static int cacheArrayLength = 10;
    private static int numberOfRequests = 100;
    private static int numberOfSensors = 10;
    private static int numberOfThreads = 10;
    Integer[][] testArr;
    Integer[][] resultArr;

    @Before
    public void init() {
        dataServiceImpl = new DataServiceImpl();
        dataServiceImpl.setDataDao(new DataDaoMock());
        dataServiceImpl.setCacheArrayLength(cacheArrayLength);
        dataServiceImpl.init();

        testArr = new Integer[numberOfSensors][numberOfRequests];
        for (Integer[] ints : testArr) {
            for (int j = 0; j < ints.length; j++) {
                ints[j] = (int) (Math.random() * 100);
            }
        }
        
        resultArr = new Integer[numberOfSensors][numberOfRequests/cacheArrayLength];
        for (int i = 0; i < testArr.length; i++) {
            Integer[] ints = testArr[i];
            int sum = 0;
            for (int j = 0; j < ints.length; j++) {
                sum = sum + ints[j];
                if((j+1)%10 == 0){
                    resultArr[i][(j+1)/10 - 1] = sum / cacheArrayLength;
                    sum = 0;
                }
            }
        }
    }

    @Test
    public void testProcess() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfSensors; i++) {
            executorService.execute(new TestTask(i + 1));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        for (int i = 0; i < numberOfSensors; i++) {
            Assert.assertTrue(Arrays.equals(resultArr[i], dataServiceImpl.getAggregatedData(i + 1, cacheArrayLength)));
        }
    }

    private class TestTask implements Runnable{

        int taskNumber;

        private TestTask(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void run() {
            for (int i = 0; i < numberOfRequests; i++) {
                int val = testArr[taskNumber-1][i];
                Data data = new Data();
                data.setDeviceId(taskNumber);
                data.setData(val);
                dataServiceImpl.processData(data);
            }
        }
    }

    private static class DataDaoMock implements DataDao {

        private static Multimap<Integer, Data> DATA_MULTIMAP = ArrayListMultimap.create();

        @Override
        public Integer[] find(int deviceId, int count) {
            Collection<Data> dataCollection = DATA_MULTIMAP.get(deviceId);
            if (CollectionUtils.isEmpty(dataCollection)) {
                return new Integer[0];
            }
            List<Data> dataArray = new ArrayList<>(dataCollection);
            Integer[] result = new Integer[dataCollection.size() < numberOfRequests/cacheArrayLength ? dataCollection.size() : numberOfRequests/cacheArrayLength];
            for (int i = 0; i < result.length; i++) {
                result[i] = dataArray.get(i).getData();

            }
            return result;
        }

        @Override
        public Data findByID(Long id) {
            throw new UnsupportedOperationException();
        }

        @Override
        public List<Data> findAll() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int countAll() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void saveOrUpdate(Data entity) {
            DATA_MULTIMAP.put(entity.getDeviceId(), entity);
        }

        @Override
        public void delete(Long id) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void delete(Data entity) {
            throw new UnsupportedOperationException();
        }
    }
}
