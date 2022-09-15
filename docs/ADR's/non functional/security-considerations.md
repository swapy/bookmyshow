# Security considerations for the platform


## Status

PROPOSED

## Context
We would like to have correct security measures for application

## Assumptions
  
## Requirements
1. Mitigate OWASP top 10.
2. Proper authentication and authorization for requests that need to be secured
3. Service to service communication security



## Proposed solution
### IAM(AWS cognito)
 - Authentication and authorization
 - Required actions support
 - Add roles, group and users to create authorization matrix

### Istio ingress gateway as entrypoint
- It will be entrypoint for EKS
- Will also list internal and external endpoints for exposure.

### Spring cloud gateway as front controller.
- Add SCG as front controller, intercepting all requests.
- Dynamic routing(based on device, city and so on)
- Pre filtering for request/response
- Check token for validity

### Istio as service mesh
- Will use istio as service mesh to secure s2s comms with mtls
- Will add fault tolerance with istio and also traffic maangement capabilities
- Istio egress to contact external integration services.   


### SAST(Static application security testing)
- SAST will check application for OWASP top 10 issues and any other vulnerabilities
- Tool like checkmarx, owasp dependency checker, blackduck scan.
- Docker image scan for vulnerabilites.
### DAST(Dynamic application security testing)
- DAST testing simulates the actions of a malicious actor trying to break into your application remotely
- Requires complete running application


### Application hardening
- Expose only required things. eg: in actuator expose only endpoints needed: eg: health, metrics, prometheus and also ensure authentication is set.
- Swagger endpoints can be optionally enabled with authentication.

## OWASP top 10
###  Broken Access Control
Mitigation: 
- Use principle of least privilege
- Except for public resources, deny by default

### Cryptographic Failures
- using mtls for s2s and ingress/egress requests. Messages will not be passed in clear text.
- request bodies can be encrypted to prevent from tampering.

### Injection
- use prepared statements for sql and likewise for data jpa.
- sanitize data before consumption.

### Insecure Design
- use proper design eg: improper storage of credentails(non hashed and salted)

### Security Misconfiguration
- exposed security keys for environment.
- exposed environment variables or config which was not meant to exposed.

Will mitigate by hardening application
to use secrets or key vault to prevent misconfigurations.

### Vulnerable and Outdated Components
- will use owasp dependency analyser and scan outdated, and insecure dependencies.

- upgrade platform after rigrous security checks only.

### Identification and Authentication Failures
- add weak password check mechanisms
- password rotation every 60 days
- blacklist rainbow table passwords
- password length with password criteria
- MFA to secure system


### Security Logging and Monitoring Failures
- Will ensure to hide sensitive fields in logs.
- audit high value transactions.

###  Server-Side Request Forgery (SSRF)
- Sanitize and validate all client-supplied input data
- Log all accepted and blocked network flows on firewalls
- Segment remote resource access functionality in separate networks to reduce the impact of SSRF


## Consequences





