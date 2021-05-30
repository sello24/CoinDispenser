package com.sello.coindispenser.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sello.coindispenser.CoindispenserApplication;
import com.sello.coindispenser.configurations.mySqlConfiguration;


@SpringBootTest(classes = {CoindispenserApplication.class, mySqlConfiguration.class, })
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class TestBase {

}
