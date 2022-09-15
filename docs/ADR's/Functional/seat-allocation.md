# Seat allocation for the show


## Status

PROPOSED

## Context
Propose seats to customer post his number of movie watcher selection

## Assumptions
1. Show is not housefull.
2. There are enough seats available for show.


## Decision
a. Divide all rows in the theatre room in 1 segment each.  
b. If enough space is there and user selects one segment, In segment contagious allocation can happen(can use bit manipulation for this).  
c. Seats filled are not clickable and disabled.  
d. Seats will be blocked for 5 mins either at theatre system (for OTC booking) or at bookmy level till user completes/fails booking.  
e. Seats will be released again for allocation post cooling period.  
f. post booking seats booked are marked disabled.  
g. before 5 mins of show, booking is closed.  

## Consequences
Sync of seat blocking should be done carefully.



