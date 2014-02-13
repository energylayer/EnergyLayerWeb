package com.energylayer.dao.impl;

import com.energylayer.dao.AbstractDao;
import com.energylayer.dao.DataDao;
import com.energylayer.entity.Data;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: rkotelnikov
 */
@Repository
public class DataDaoImpl extends AbstractDao<Data, Long> implements DataDao {

    public DataDaoImpl() {
        super(Data.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Integer[] find(int deviceId, int sensorNumber, int count) {
        List<Integer> list = criteria()
                .setProjection(Projections.projectionList().add(Projections.property("data"+sensorNumber)))
                .add(Restrictions.eq("deviceId", deviceId))
                .getExecutableCriteria(getSession())
                .setMaxResults(count)
                .addOrder(Order.desc("id"))
                .list();
        return list.toArray(new Integer[list.size()]);
    }
}
