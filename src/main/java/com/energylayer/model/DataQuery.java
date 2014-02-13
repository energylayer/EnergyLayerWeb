package com.energylayer.model;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;


/**
 * @author: rkotelnikov
 */
public class DataQuery {

    @NotNull(errorCode = "device.id.not.null")
    private int deviceId;
    @NotEmpty(errorCode = "data.not.empty")
    @Length(errorCode = "data.length", min = 36, max = 36)
    private String data;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
