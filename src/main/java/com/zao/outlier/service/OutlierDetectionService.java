package com.zao.outlier.service;

import com.zao.outlier.dto.Machine;

import java.util.List;

public interface OutlierDetectionService {
    List<Machine> findOutliers(List<Machine> machines);

    int parseAgeToDays(String age);

    double calculateStandardDeviation(List<Integer> ages, double mean);
}
