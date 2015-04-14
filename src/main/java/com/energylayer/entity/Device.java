package com.energylayer.entity;

import javax.persistence.*;

/**
 * @author rkotelnikov
 */
@javax.persistence.Entity
@Table(name = "device")
public class Device extends AbstractEntity<Integer> {

    @Column(name = "deviceId")
    private String deviceId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    private User user;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
