package com.example.demo;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.stereotype.Component;

@Component
class CustomMeterFilter implements MeterFilter {
    private int tag;

    @Override
    public Meter.Id map(Meter.Id id) {
        return id.withTags(Tags.of("tag", Integer.toString(tag)));
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
