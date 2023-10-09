package com.zao.outlier.service.impl;

import com.zao.outlier.dto.Machine;
import com.zao.outlier.service.OutlierDetectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OutlierDetectionServiceImpl implements OutlierDetectionService {

    // Adjust the threshold based on your data set - See unit tests for example usage
    @Value("${outlier.detection.threshold}")
    private double threshold;

    @Override
    public List<Machine> findOutliers(List<Machine> machines) {
        // Working in days
        List<Integer> ages = machines.stream()
                .map(machine -> parseAgeToDays(machine.getAge()))
                .collect(Collectors.toList());

        // Calculate mean and standard deviation
        double mean = ages.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        double stdDev = calculateStandardDeviation(ages, mean);

        // Find the outliers based on Z-Score
        List<Machine> outliers = new ArrayList<>();
        for (int i = 0; i < ages.size(); i++) {
            double zScore = (ages.get(i) - mean) / stdDev;
            if (Math.abs(zScore) > threshold) {
                outliers.add(machines.get(i));
            }
        }

        return outliers;
    }

    public int parseAgeToDays(String age) {
        if (age.contains("year")) {
            return Integer.parseInt(age.split(" ")[0]) * 365;
        } else if (age.contains("month")) {
            return Integer.parseInt(age.split(" ")[0]) * 30;
        } else if (age.contains("week")) {
            return Integer.parseInt(age.split(" ")[0]) * 7;
        } else if (age.contains("day")) {
            return Integer.parseInt(age.split(" ")[0]);
        } else {
            // Date in illegal format as only Day, Week, Month, and Year
            String error = String.format("The Machine age: [%s]  is in an invalid format. Accepts time formats are: day, week, month and year", age);
            log.error(error);
            throw new IllegalArgumentException(error);

        }
    }

    public double calculateStandardDeviation(List<Integer> ages, double mean) {
        double sum = 0.0;
        for (int age : ages) {
            sum += Math.pow(age - mean, 2);
        }
        return Math.sqrt(sum / ages.size());
    }
}
