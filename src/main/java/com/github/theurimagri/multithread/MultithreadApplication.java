package com.github.theurimagri.multithread;

import com.github.theurimagri.multithread.config.ApplicationConfiguration;
import com.github.theurimagri.multithread.config.DatabaseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {DatabaseConfiguration.class, ApplicationConfiguration.class})
public class MultithreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultithreadApplication.class, args);
    }
}
