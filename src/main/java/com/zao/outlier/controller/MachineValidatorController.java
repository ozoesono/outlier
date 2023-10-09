package com.zao.outlier.controller;

import com.zao.outlier.dto.Machine;
import com.zao.outlier.service.OutlierDetectionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class MachineValidatorController {

    private OutlierDetectionService outlierDetectionService;

    @PostMapping("/machines")
    ResponseEntity<List<Machine>> validateMachines(@RequestBody List<Machine> machines) {
        log.info("Validating Machines: [{}]", machines);
        try {
            List<Machine> outliers = outlierDetectionService.findOutliers(machines);
            return ResponseEntity.ok(outliers);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
