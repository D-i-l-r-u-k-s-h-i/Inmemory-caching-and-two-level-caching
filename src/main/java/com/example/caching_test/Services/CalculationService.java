package com.example.caching_test.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculationService {
    private static Logger logger = LoggerFactory.getLogger(CalculationService.class);

//    level 2 cache
    @Cacheable(
            value = "squareCacheLRU",
            key = "#number",
//            condition = "#number>10",
            sync = true)
    public BigDecimal squareLRU(Long number) {
        BigDecimal square = BigDecimal.valueOf(number)
                .multiply(BigDecimal.valueOf(number));
        logger.info("square of {} is {} after calculation -LRU Eviction-Level2", number, square);
        return square;
    }

    @Cacheable(
            value = "squareCacheLFU",
            key = "#number",
//            condition = "#number>10",
            sync = true)
    public BigDecimal squareLFU(Long number) {
        BigDecimal square = BigDecimal.valueOf(number)
                .multiply(BigDecimal.valueOf(number));
        logger.info("square of {} is {} after calculation -LFU Eviction-Level2", number, square);
        return square;
    }

//    level 1(in-memory) cache
    @Cacheable(value = "areaOfCircleCacheLFU",
            key = "#radius",
//            condition = "#radius > 5",
            sync = true)
    public double areaOfCircleLFU(int radius) {
        double area =Math.PI * Math.pow(radius, 2);
        logger.info("the area of a circle with a radius of {} is {} after calculation -LFU Eviction-Level1", radius,area);
        return area;
    }

    @Cacheable(value = "areaOfCircleCacheLRU",
            key = "#radius",
//            condition = "#radius > 5",
            sync = true)
    public double areaOfCircleLRU(int radius) {
        double area =Math.PI * Math.pow(radius, 2);
        logger.info("the area of a circle with a radius of {} is {} after calculation -LRU Eviction-Level1", radius,area);
        return area;
    }
}
