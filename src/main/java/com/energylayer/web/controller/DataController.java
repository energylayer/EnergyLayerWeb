package com.energylayer.web.controller;

import com.energylayer.service.DataService;
import com.energylayer.model.DataQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author: rkotelnikov
 */
@RestController
@RequestMapping(value = "/data")
public class DataController {

    private static final Logger log = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private DataService dataService;

    @RequestMapping(value = {"/post", "/post/{deviceId}/{data}"}, method = {GET, POST})
    @ResponseStatus(value = OK)
    public void getFromSensor(@Valid @ModelAttribute DataQuery dataQuery){
        log.info("/data/post/{}/{}", dataQuery.getDeviceId(), dataQuery.getData());
        dataService.processData(dataQuery);
    }

    @RequestMapping(value = "/get/{deviceId}/{sensorNumber}", method = GET)
    public @ResponseBody Integer[] getData(@PathVariable("deviceId") int deviceId,
                             @PathVariable("sensorNumber") int sensorNumber){
        Integer[] result = dataService.getData(deviceId, sensorNumber, 1);
        log.info("/data/get/{}/{}", deviceId, Arrays.toString(result));
        return result;
    }

    @RequestMapping(value = "/aggregated/get/{deviceId}/sensorNumber", method = GET)
    public @ResponseBody Integer[] getAggregatedData(@PathVariable("deviceId") int deviceId,
                                       @PathVariable("sensorNumber") int sensorNumber,
                                       @RequestParam(value = "count", required = false, defaultValue = "10") int number){
        log.info("/data/aggregated/get/{}", deviceId);
        return dataService.getData(deviceId, sensorNumber, number);
    }
}
