package com.energylayer.web.controller;

import com.energylayer.service.DataService;
import com.energylayer.model.DataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.lang.reflect.Array;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author: rkotelnikov
 */
@Controller
@RequestMapping(value = "/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @RequestMapping(value = {"/post", "/post/{deviceId}/{data}"}, method = {GET, POST})
    @ResponseStatus(value = OK)
    public void getFromSensor(@Valid @ModelAttribute DataQuery dataQuery){
        System.out.println("/data/post/" + dataQuery.getDeviceId() + "/" + dataQuery.getData());
        dataService.processData(dataQuery);
    }

    @RequestMapping(value = "/get/{deviceId}/{sensorNumber}", method = GET)
    @ResponseBody
    public Integer[] getData(@PathVariable("deviceId") int deviceId,
                             @PathVariable("sensorNumber") int sensorNumber){
        Integer[] result = dataService.getData(deviceId, sensorNumber, 1);
        System.out.println("/data/get/" + deviceId + "/" + result[0]);
        return result;
    }

    @RequestMapping(value = "/aggregated/get/{deviceId}/sensorNumber", method = GET)
    @ResponseBody
    public Integer[] getAggregatedData(@PathVariable("deviceId") int deviceId,
                                       @PathVariable("sensorNumber") int sensorNumber,
                                       @RequestParam(value = "count", required = false, defaultValue = "10") int number){
        System.out.println("/data/aggregated/get/" + deviceId);
        return dataService.getData(deviceId, sensorNumber, number);
    }
}
