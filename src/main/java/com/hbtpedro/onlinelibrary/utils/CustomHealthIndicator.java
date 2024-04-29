package com.hbtpedro.onlinelibrary.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Health health() {
        boolean isDbUp = isDatabaseUp();
        boolean isMemoryHealthy = isMemoryUsageHealthy();

        if (isDbUp && isMemoryHealthy) {
            return Health.up().build();
        } else {
            return Health.down()
                    .withDetail("Database", isDbUp ? "Available" : "Not Available")
                    .withDetail("Memory", isMemoryHealthy ? "Healthy" : "High Usage")
                    .build();
        }
    }

    private boolean isDatabaseUp() {
        try {

            jdbcTemplate.execute("SELECT 1");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean isMemoryUsageHealthy() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        double usedMemoryPercentage = (double) memoryBean.getHeapMemoryUsage().getUsed()
                / memoryBean.getHeapMemoryUsage().getMax() * 100;

        return usedMemoryPercentage <= 80;
    }
}

