# Fetch shows from theatres


## Status

PROPOSED

## Context

Fetch shows from theatres.
Objective here is to get all shows from theatres for displaying on platform

## Assumptions
1. Theatres have their shows planned for whole week and can share complete schedule in 1 week advance.
2. Theatres expose rest endpoints to share show details.
3. Strategy to fetch shows can be   
    a. PULL shows from theatre software systems via theatre rest endpoints.
    OR   
    b. PUSH shows to a central system residing at bookmy
4. We will be using strategy a(pull) as theatres will not push data for this case.

5. All theatres will have standard uniform format to send show details. 

## Decision
a. PULL strategy to fetch data.

## Proposed solution
### Sol A : Push to db directly

a. Create a scheduled task which runs in wee hours of sunday.
b. Call rest endpoints for all theatres in city to fetch data.
c.Save data to database post each call.

### Sol B: Push to kafka then db, due to increased load

a. Create a scheduled task which runs in wee hours of sunday.  
b. Call rest endpoints for all theatres in city to fetch data.  
c.Push data as CreateShowEvent to kafka(Source). 
d. Consume data from kafka topic to database shows table(sink)

### Sol C: Push to kafka and use kafka as db

a. Create a scheduled task which runs in wee hours of sunday.  
b. Call rest endpoints for all theatres in city to fetch data.  
c.Push data as CreateShowEvent to kafka(Source). 
d. Use kafka streams to query show data.
## Consequences

Show data gets populated for whole week for all theatres.




