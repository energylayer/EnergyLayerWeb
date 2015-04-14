package com.energylayer.web.controller;

import com.energylayer.entity.Device;
import com.energylayer.service.DeviceService;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import java.util.List;

/**
 * @author rkotelnikov
 */
@Controller
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void addDevice(@RequestParam String deviceId, HttpSession session) {
        if(StringUtils.isBlank(deviceId)){
            throw new ValidationException("Invalid device ID: " + deviceId);
        }
        Integer userId =(Integer)session.getAttribute("userId");
        deviceService.save(deviceId, userId);
    }

    @RequestMapping(value = "/deviceIds", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getDeviceIds(HttpSession session) {
        Integer userId = (Integer)session.getAttribute("userId");
        return Lists.transform(deviceService.findDevices(userId), new Function<Device, Integer>() {
            @Override
            public Integer apply(Device device) {
                return device.getId();
            }
        });
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String validationExceptionHandler(ValidationException e){
        return e.getMessage();
    }
}
