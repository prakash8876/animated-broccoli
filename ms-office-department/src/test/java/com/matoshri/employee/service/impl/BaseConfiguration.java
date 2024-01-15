package com.matoshri.employee.service.impl;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles(resolver = TestProfleResolver.class)
@SpringBootTest(classes = TestConfiguration.class)
//@RunWith
@TestPropertySource({"classpath:application-test.yml"})
public class BaseConfiguration {

}
