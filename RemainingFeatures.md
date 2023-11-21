Things to add

List of things to add to code--most of these need scratch code already implemented
- [ ] Add all the unit tests
- [ ] Add a global properties file  Configurations https://mkyong.com/spring-boot/spring-boot-configurationproperties-example/
- [ ] change repository so if the new json file cannot be found, it will use originalSeedData.json in resources
- [ ] Add handler methods/endpoints that yield metadata such as number of animals, average age...etc
- [ ] Add a new endpoint that returns a list of animals that meet certain criteria
- [ ] change from DATE to LocalDateTime
- [ ] fix the controller, so it returns something helpful if the submitted kind is invalid aka Using a custom http response entity to return a custom error message 
- [ ] Add a custom exception handler to handle exceptions
- [ ] Add Integration tests with Spring Boot Test
- [ ] Possibly make a controller handler method that re-sets the Animals list to the persistant_animals.json file
- Work the TODOs in the code including
  - [ ] //TODO see if this could also work:  if (enumKind.equalsIgnoreCase(kind)
  - [X] In controller //TODO change Food to WithFeedType
  - [ ] See Repository for use of @Repository and interfaces
  - [ ] Animal repository method seems too long: saveAnimalsToFile()
  - [ ] In controller handle invalid kind
  - [ ] In controller handle invalid id coming in
  - [X] Add handler method to retun all valid kinds
- [X] change addAnimal in Repository it adds index to maximum of indexes...not just one more than size
- [X] Add logger with logging..simple implementation complete 11/12/2023
- [X] Investigate having updated animals list written back to the json file (see below) done 11/14/23
- [X] Add a kind for each animal..a new field in the json file ... each animal will show its kind and its personal name done 11/20/23
- [X] Add modifiedDate done 11/20/23
- [X] Create an independent Java console project that will consume the endpoints of this project.  User can select a number from the menu then enter data, then will see returned information done before 11/19/23
- [X] Add a DTO class done 11/20/23

