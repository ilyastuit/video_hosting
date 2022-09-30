package com.ilyastuit;

import com.ilyastuit.infrastructure.spring.config.DataSourceConfig;
import com.ilyastuit.infrastructure.spring.config.PropertyConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceConfig.class, PropertyConfig.class})
public class BaseTest {


//    @Configuration
//    @PropertySource("classpath:/application-test.properties")
//    public static class SpringConfig {}
}
