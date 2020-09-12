package com.example.caching_test.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculationService {
    private static Logger logger = LoggerFactory.getLogger(CalculationService.class);

    @Cacheable(
            value = "squareCache",
            key = "#number",
            condition = "#number>10",
            sync = true)
    public BigDecimal square(Long number) {
        BigDecimal square = BigDecimal.valueOf(number)
                .multiply(BigDecimal.valueOf(number));
        logger.info("square of {} is {}", number, square);
        return square;
    }

    @Cacheable(value = "areaOfCircleCache", key = "#radius", condition = "#radius > 5")
    public double areaOfCircle(int radius) {
        logger.info("calculate the area of a circle with a radius of {}", radius);
        return Math.PI * Math.pow(radius, 2);
    }
}
