## Microservices test

As per the spec I am choosing the option to submit a similar test. 
I believe it meets 4/5 of the bonus points, the missing one being 
Swagger. I have added some Swagger as a separate commit. See:

http://localhost:8080/swagger-ui.html

The requirement was to build a shop that 'scans' and 'voids' items.
It had to support 'pricing rules' which are logical groupings of
items with special prices. A time of 3 hours was suggested and the
advice was to use comments regarding further considerations for a 
complete production version. The original README contents is below:

## Intro

The shop is implemented as a Spring Boot RESTful API.

Nouns with appropriate HTTP verbs are used at the API level
instead of the 'scan' and 'void' verbs because this is arguably
the consensus for API design (although there is no fixed
convention yet).

An in-memory H2 database is used and items, pricing rules and 
discounts are a matter of adding data (see `DataPopulation.java`)

## Run and Build

Run `Application.java` in the IDE. Lombok plugin is required.
Tested and working in Intellij  
`gradlew clean build` will rebuild the project and run the tests  
See the example integration test for API usage

## Further considerations given more time (also see code comments)

* Stricter TDD approach starting with lowest level unit tests
* Code coverage tool e.g. Jacoco
* Edge case testing and error handling e.g. ItemNotFoundException
* Input validation
* JSON responses for all API return scenarios

Note - no interfaces needed for decoupling since Spring is handling 
the dependency injection (and only single implementations are being used)

The spec suggested spending 3 hours but that there is no maximum. I have spent around
5 - 6 hours with some gaps where I thought about the design


* Jon Hatfield 10/02/2019