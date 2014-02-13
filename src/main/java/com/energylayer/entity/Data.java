package com.energylayer.entity;

import javax.persistence.*;

/**
 * @author: rkotelnikov
 */
@javax.persistence.Entity
@Table(name = "DATA")
public class Data extends AbstractEntity<Long> {

    @Column(name = "DEVICEID")
    private int deviceId;

    @Column(name="DATA1")
    private int data1;

    @Column(name="DATA2")
    private int data2;

    @Column(name="DATA3")
    private int data3;

    @Column(name="DATA4")
    private int data4;

    @Column(name="DATA5")
    private int data5;

    @Column(name="DATA6")
    private int data6;

    @Column(name="DATA7")
    private int data7;

    @Column(name="DATA8")
    private int data8;


    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getData1() {
        return data1;
    }

    public void setData1(int data1) {
        this.data1 = data1;
    }

    public int getData2() {
        return data2;
    }

    public void setData2(int data2) {
        this.data2 = data2;
    }

    public int getData3() {
        return data3;
    }

    public void setData3(int data3) {
        this.data3 = data3;
    }

    public int getData4() {
        return data4;
    }

    public void setData4(int data4) {
        this.data4 = data4;
    }

    public int getData5() {
        return data5;
    }

    public void setData5(int data5) {
        this.data5 = data5;
    }

    public int getData6() {
        return data6;
    }

    public void setData6(int data6) {
        this.data6 = data6;
    }

    public int getData7() {
        return data7;
    }

    public void setData7(int data7) {
        this.data7 = data7;
    }

    public int getData8() {
        return data8;
    }

    public void setData8(int data8) {
        this.data8 = data8;
    }
}
