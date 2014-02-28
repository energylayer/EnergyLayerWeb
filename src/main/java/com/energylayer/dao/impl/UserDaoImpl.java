package com.energylayer.dao.impl;

import com.energylayer.dao.AbstractDao;
import com.energylayer.dao.UserDao;
import com.energylayer.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author: rkotelnikov
 */
@Repository
public class UserDaoImpl extends AbstractDao<User, Integer> implements UserDao {

    protected UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findByName(String username) {
        return unique(criteria().add(Restrictions.eq("name", username)));
    }

}
