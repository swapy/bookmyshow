# identity access and management


## Status

PROPOSED

## Context
Understand and finalize IAM solution for current buisness requirement

## Assumptions

Personas to manage  
    - Managing customers  
    - Managing internal employees  
      - These are internal employees to bookmy which manage access control for customers and theatre users.  
    - Theatre user management  
      -  Theatre users are users who will be logged in for system to system api calls.  
  
## Decision
We will store data of 3 different personas in 3 different realms.

1. customer realm
2. employee realm
3. theatre users realm


## Proposed solution
1. Use managed IAM like AWS cognito
2. Required actions post registration like verify email, verify sms can be done in IAM
3. Password policies like password expiry to be set and user(theatre and employee) to be forced to reset pass every 60 days.
4. Google sign in integration can also be added to onboard customers.
   
## Consequences





