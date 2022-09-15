# Observability stack for bookmy


## Status

PROPOSED

## Context
We would like to add observability to all microservices and setup

## Assumptions
  
## Requirements
1. Record application and system metrics
2. Log messages in central places, can be viewed in single dashboard.
3. Log correlation support to trace requrest logs accross services
4. Trace request spans to understand why a request took time.
5. Dashboard to view all monitoring parameters at single place.
6. Log shipping, storage to be done and dashboard to view logs



## Proposed solution
1. Use EFK stack as one solution to manage logs.
2. Logs will be enriched with MDC(mapped diagnostic context), pass client ip, device and metadata to each log message in logback appender configuration.
3. Grafana to monitor application metrics and health.
4. Spring boot actuator to propagate health, diskspace, prometheus and so on endpoints to understand application state.
5. Kiali as istio dashboard.
   
## Consequences





