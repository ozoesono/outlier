package com.zao.outlier.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.zao.outlier.util.OutlierHelper.calculateStandardDeviation;
import static com.zao.outlier.util.OutlierHelper.parseAgeToDays;
import static org.junit.jupiter.api.Assertions.*;

class OutlierHelperTest {

    @Test
    void testParseAgeToDays_Years() {
        int days = parseAgeToDays("2 years");
        assertEquals(730, days, "2 years should be 730 days.");
    }

    @Test
    void testParseAgeToDays_Months() {
        int days = parseAgeToDays("2 months");
        assertEquals(60, days, "2 months should be 60 days.");
    }

    @Test
    void testParseAgeToDays_Days() {
        int days = parseAgeToDays("15 days");
        assertEquals(15, days, "15 days should be 15 days.");
    }

    @Test
    void testParseAgeToDays_InvalidFormat() {
        assertThrows(RuntimeException.class, () -> {
            parseAgeToDays("2 lights");
        }, "Expected IllegalArgumentException for invalid format.");
    }

    @Test
    void testCalculateStandardDeviation() {
        List<Integer> ages = Arrays.asList(1, 2, 3, 4, 5);
        double stdDev = calculateStandardDeviation(ages, 3);
        assertEquals(1.4142, stdDev, 0.001, "Expected standard deviation to be close to 1.4142.");
    }
}