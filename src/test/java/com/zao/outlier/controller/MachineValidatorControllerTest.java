package com.zao.outlier.controller;

import com.zao.outlier.dto.Machine;
import com.zao.outlier.service.OutlierDetectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MachineValidatorControllerTest {

    @InjectMocks
    private MachineValidatorController controller;

    @Mock
    private OutlierDetectionService outlierDetectionService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testValidateMachines_HappyPath() {
        Machine machine1 = new Machine("1", "1 year");
        Machine machine2 = new Machine("2", "5 years");
        List<Machine> machines = Arrays.asList(machine1, machine2);

        when(outlierDetectionService.findOutliers(anyList())).thenReturn(Arrays.asList(machine2));

        ResponseEntity<List<Machine>> response = controller.validateMachines(machines);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertTrue(response.getBody().contains(machine2));
    }

    @Test
    public void testValidateMachines_ServiceException() {
        Machine machine = new Machine("1", "1 year");
        List<Machine> machines = Arrays.asList(machine);

        when(outlierDetectionService.findOutliers(anyList())).thenThrow(new RuntimeException("Service exception"));

        ResponseEntity<List<Machine>> response = controller.validateMachines(machines);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
