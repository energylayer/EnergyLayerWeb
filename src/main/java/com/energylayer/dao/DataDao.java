package com.energylayer.dao;

import com.energylayer.entity.Data;

/**
 * @author: rkotelnikov
 */
public interface DataDao extends Dao<Data, Long>{

    Integer[] find(int deviceId, int count);
}
