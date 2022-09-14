## Spring profiles

We support following profiles:
### default
This profile loads just base [application.yml](src/main/resources/application.yml)  
All common code to all profiles resides in this file [common.yml](src/main/resources/app-config/common.yml)

Any changes to these files will reflect in all files so be careful.

For default profile, we have disabled following:
1. swagger endpoints - have set swagger paths for child profiles
2. actuator - have set endpoints for child profiles
3. logging  - all default to info

Profiles are free to override and selectively enable these features.

### local
This profile is for local development. All urls point to local setup.
In local setup, any external service is also locally spin up.
eg: data api tool will be hosted on localhost.

### vpn
This profile is for local development with vpn enabled. All urls point to local setup.
In vpn setup, any external service is consumed from aws cluster via vpn rather than spinning things locally.
eg: data api tool will be pointing to mb2 service.

1. we have [application-local.yml](src/main/resources/app-config/application-local.yml)
2. we have [application-local-vpn.yml](src/main/resources/app-config/application-local.yml)

local vpn yaml just hosts external dependency service endpoints, else it has hard dependency on local profile for remaining config.

we combine these two to make new config group named `vpn`
If you check `application.yml` you will see

```yaml
spring:
  config:
    name: dt-customers-service
    import: app-config/common.yml
  profiles:
    group:
      vpn: local,local-vpn
    active: vpn
```
This mentions the profile group vpn is activated with both `local and local vpn` combined
### dev

This profile is edited and maintained in EKS `values.yml`

### uat

This profile is edited and maintained in EKS `values.yml`



# Api documentation
 Api documentation can be found at  
 http://localhost:9091/dt-customers-service/v1/api-docs

and swagger docs are at  
http://localhost:9091/dt-customers-service/swagger-ui/index.html

# Testing
## To run only unit tests 
`mvn clean install` or `mvn test`

## To run integration tests as well
`mvn verify`

# Sonar scan

To do local sonar scan you can do following

`mvn clean verify sonar:sonar \
-Dsonar.projectKey=customer-service \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=sqp_dc0422b33e13f6ff91a4b874ce0484ab7d3e6a30`