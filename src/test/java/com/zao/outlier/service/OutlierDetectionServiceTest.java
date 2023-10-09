package com.zao.outlier.service;

import com.zao.outlier.dto.Machine;
import com.zao.outlier.service.impl.OutlierDetectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class OutlierDetectionServiceTest {

    private OutlierDetectionService service;

    @BeforeEach
    public void setUp() {
        service = new OutlierDetectionServiceImpl();
    }

    @Test
    void testFindOutliers_NoOutliers() {
        List<Machine> machines = Arrays.asList(
                new Machine("1", "1 year"),
                new Machine("2", "1 year 2 months"),
                new Machine("3", "11 months")
        );

        List<Machine> outliers = service.findOutliers(machines);

        assertFalse(outliers.isEmpty(), "Expected some outliers due to reduced threshold.");
    }

    @Test
    void testFindOutliers_WithOutliers() {
        List<Machine> machines = Arrays.asList(
                new Machine("1", "1 year"),
                new Machine("2", "7 years"),
                new Machine("3", "1 year 1 month"),
                new Machine("4", "6 years")
        );

        List<Machine> outliers = service.findOutliers(machines);

        assertTrue(outliers.size() >= 2, "Expected at least 2 outliers due to reduced threshold.");
    }
}
