package com.example.caching_test.Controllers;


import com.example.caching_test.Services.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private static Logger logger = LoggerFactory.getLogger(CacheController.class);
    @Autowired
    CalculationService calculationService;

    @RequestMapping(value = "/l1lrueviction/{number}", method = RequestMethod.GET)
    public String levelOneLRUEviction(@PathVariable Long number) throws Exception {

        logger.info("call calculationService to calculate square {} -LRU Eviction-Level2", number);
        return String.format("{\"square\": %s}", calculationService.squareLRU(number));

    }

    @RequestMapping(value = "/l2lrueviction/{number}", method = RequestMethod.GET)
    public String levelTwoLRUEviction(@PathVariable Long number) throws Exception {

        logger.info("call calculationService to calculate square {} -LRU Eviction-Level1", number);
        return String.format("{\"square\": %s}", calculationService.squareLRU(number));

    }

    @RequestMapping(value = "/l1lfueviction/{radius}", method = RequestMethod.GET)
    public String levelOneLFUEviction(@PathVariable Integer radius) throws Exception {

        logger.info("call calculationService for area calculation of circle to radius {} -LFU Eviction-Level1", radius);
        return String.format("{\"area of circle\": %s}", calculationService.areaOfCircleLFU(radius));

    }

    @RequestMapping(value = "/l2lfueviction/{radius}", method = RequestMethod.GET)
    public String levelTwoLFUEviction(@PathVariable Integer radius) throws Exception {

        logger.info("call calculationService for area calculation of circle to radius {} -LFU Eviction-Level2", radius);
        return String.format("{\"area of circle\": %s}", calculationService.areaOfCircleLRU(radius));

    }
}
