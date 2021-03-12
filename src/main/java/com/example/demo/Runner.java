package com.example.demo;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class Runner implements ApplicationRunner {
    private final MeterRegistry meterRegistry;
    private final CustomMeterFilter customMeterFilter;

    Runner(MeterRegistry meterRegistry, CustomMeterFilter customMeterFilter) {
        this.meterRegistry = meterRegistry;
        this.customMeterFilter = customMeterFilter;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 10; i++) {
            customMeterFilter.setTag(i);

            meterRegistry.counter("bug.instance").increment();
            Metrics.counter("bug.static").increment();
        }
    }
}
