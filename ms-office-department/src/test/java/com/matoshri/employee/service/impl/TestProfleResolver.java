package com.matoshri.employee.service.impl;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.test.context.ActiveProfilesResolver;

public class TestProfleResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> testClass) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "test");
        return new String[] {"test"};
    }
}
