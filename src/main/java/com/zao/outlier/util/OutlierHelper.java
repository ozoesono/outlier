package com.zao.outlier.util;

import java.util.List;


public class OutlierHelper {
    public static int parseAgeToDays(String age) {
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
            throw new IllegalArgumentException(String.format("The Machine age: [%s]  is in an invalid format. " +
                    "Accepts time formats are: day, week, month and year", age));
        }
    }

    public static double calculateStandardDeviation(List<Integer> ages, double mean) {
        double sum = 0.0;
        for (int age : ages) {
            sum += Math.pow(age - mean, 2);
        }
        return Math.sqrt(sum / ages.size());
    }
}
