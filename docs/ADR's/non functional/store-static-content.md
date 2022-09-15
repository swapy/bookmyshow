# Store static content


## Status

PROPOSED

## Context
Store static content and localizations.

## Assumptions

## Decision
a. Store all static content for website and mobile screens in cms  
b. localized data can be stored in cms. Metadata can be localized with all indian language support.  
c. use strapi cms.  

## Proposed solution
1. Strapi cms to store static data.
2. Amazon S3 for images, videos and trailers.
3. Amazon Cloudfront as CDN to be integrated with cms and S3.
4. Integrate cms with s3 for object retrieval.
   
## Consequences

Show data gets populated for whole week for all theatres.




