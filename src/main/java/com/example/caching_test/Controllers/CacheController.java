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

    @RequestMapping(value = "/lrueviction/{number}", method = RequestMethod.GET)
    public String testLRUEviction(@PathVariable Long number) throws Exception {

        logger.info("call calculationService to calculate square {}", number);
        return String.format("{\"square\": %s}", calculationService.square(number));

    }

    @RequestMapping(value = "/lfueviction/{radius}", method = RequestMethod.GET)
    public String testLFUEviction(@PathVariable Integer radius) throws Exception {

        logger.info("call calculationService for area calculation of circle to radius {}", radius);
        return String.format("{\"area of circle\": %s}", calculationService.areaOfCircle(radius));

    }

    @RequestMapping(value = "/twolevel", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> testTwoLevelCache() throws Exception {


        return ResponseEntity.ok("some array");
    }
}
