package com.energylayer.entity;

import javax.persistence.*;

/**
 * @author: rkotelnikov
 */
@javax.persistence.Entity
@Table(name = "DATA")
public class Data extends AbstractEntity {

    @Column(name = "deviceId")
    private int deviceId;

    @Column(name="DATA")
    private int data;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
