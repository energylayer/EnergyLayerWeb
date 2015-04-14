package com.energylayer.builder;

import com.energylayer.entity.Device;
import com.energylayer.entity.User;

/**
 * @author rkotelnikov
 */
public class DeviceBuilder implements Builder<Device> {

    private String deviceId;

    private User user;

    public static DeviceBuilder newInstance() {
        return new DeviceBuilder();
    }

    @Override
    public Device build() {
        Device device = new Device();
        device.setDeviceId(deviceId);
        device.setUser(user);
        return device;
    }

    public DeviceBuilder withDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public DeviceBuilder withUser(User user) {
        this.user = user;
        return this;
    }
}
