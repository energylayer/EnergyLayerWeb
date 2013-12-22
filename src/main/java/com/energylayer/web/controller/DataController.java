package com.energylayer.web.controller;

import com.energylayer.entity.Data;
import com.energylayer.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author: rkotelnikov
 */
@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/data/post", method = {GET, POST})
    @ResponseStatus(value = OK)
    public void getFromSensor(@ModelAttribute Data data){
        System.out.println("/data/post/" + data.getDeviceId());
        dataService.processData(data);
    }

    @RequestMapping(value = "/data/get/{deviceId}", method = GET)
    @ResponseBody
    public Integer getData(@PathVariable("deviceId") int deviceId){
        System.out.println("/data/get/" + deviceId);
        return dataService.getData(deviceId);
    }

    @RequestMapping(value = "/data/aggregated/get/{deviceId}", method = GET)
    @ResponseBody
    public Integer[] getAggregatedData(@PathVariable("deviceId") int deviceId,
                                       @RequestParam(value = "count", required = false, defaultValue = "10") int number){
        System.out.println("/data/aggregated/get/" + deviceId);
        return dataService.getAggregatedData(deviceId, number);
    }
}
