# Store shows data with partitions.


## Status

PROPOSED

## Context
Store data of shows based on context
a. per city basis
b. per state basis
c. geography basis


## Assumptions

## Decision
We will use Amazon aurora postgres to store data.
Shows data can be partitioned either by:

1. city
2. state
3. wood based(bollywood, kollywood, tollywood)
4. by current, past and upcoming shows

## Proposed solution

Current solution can be to use partitioning show data by state.
   
## Consequences

1. Partioning strategy can affect performance and query lookup.
2. Data push and retrieval can be faster based on numbers and some research on buiness factors and tech factors.


