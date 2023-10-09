package com.zao.outlier.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MachineValidatorControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testValidateMachines_HappyPath() throws Exception {
        String requestBody = "[{\n" +
                "        \"machineId\": \"machine-1\",\n" +
                "        \"age\": \"20 months\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"machineId\": \"machine-2\",\n" +
                "        \"age\": \"2 years\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"machineId\": \"machine-3\",\n" +
                "        \"age\": \"24 months\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"machineId\": \"machine-4\",\n" +
                "        \"age\": \"6 year\"\n" +
                "    }]";

        mockMvc.perform(post("/machines")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\n" +
                        "        \"machineId\": \"machine-1\",\n" +
                        "        \"age\": \"20 months\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"machineId\": \"machine-4\",\n" +
                        "        \"age\": \"6 year\"\n" +
                        "    }]"));
    }

}
