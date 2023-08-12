package com.example.withth.integration;

import com.example.withth.WithThApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = WithThApplication.class)
@AutoConfigureMockMvc
class EmployeeIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_ok(){
        Assertions.assertTrue(true);
    }
}
