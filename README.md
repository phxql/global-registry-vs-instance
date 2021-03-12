# Global MeterRegistry vs. instance MeterRegistry

1. Start the application
1. Run `curl -s localhost:8080/actuator/prometheus | grep ^bug`

You get something like:

```
bug_instance_total{tag="1",} 1.0
bug_instance_total{tag="2",} 1.0
bug_instance_total{tag="3",} 1.0
bug_instance_total{tag="4",} 1.0
bug_instance_total{tag="5",} 1.0
bug_instance_total{tag="6",} 1.0
bug_instance_total{tag="7",} 1.0
bug_instance_total{tag="8",} 1.0
bug_instance_total{tag="9",} 1.0
bug_instance_total{tag="0",} 1.0
bug_static_total{tag="0",} 10.0
```

The code which has created these metrics is in `Runner`. The tags are attached via a `MeterFilter`, see `CustomMeterFilter`.

I expected no difference in using `Metrics.counter("bug.static").increment()` vs. `meterRegistry.counter("bug.instance").increment();`, but the metrics registered via `Metrics` has missing tags.
