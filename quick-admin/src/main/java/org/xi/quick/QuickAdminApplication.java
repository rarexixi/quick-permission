package org.xi.quick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
public class QuickAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickAdminApplication.class, args);
    }
}
