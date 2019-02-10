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
* Edge case testing e.g. item not found with error handling e.g. ItemNotFoundException
* Input validation
* JSON responses for all API return scenarios

Note - no interfaces needed for decoupling since Spring is handling 
the dependency injection (and only single implementations are being used)

The spec suggested spending 3 hours but that there is no maximum. I have spent around
5 - 6 hours with some gaps where I thought about the design


* Jon Hatfield 10/02/2019