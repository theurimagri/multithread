package com.github.theurimagri.multithread.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.github.theurimagri.multithread.entity",
        "com.github.theurimagri.multithread.repository"})
public class DatabaseConfiguration {

}
