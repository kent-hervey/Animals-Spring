Things to add

List of things to add to code
- [ ] Add all the unit tests
- [ ] change addAnimal in Repository it adds index to maximum of indexes...not just one more than size
- [ ] Add a global properties file  Configurations https://mkyong.com/spring-boot/spring-boot-configurationproperties-example/ 
- [ ] Add logger with logging..simple implementation complete 11/12/2023
- [ ] Investigate having updated animals list written back to the json file (see below)
- [ ] Add a kind for each animal..a new field in the json file ... each animal will show its kind and its personal name
- [ ] Add modifiedDate
- [ ] Add handler methods/endpoints that yield metadata such as number of animals, average age...etc
- [ ] Add a new endpoint that returns a list of animals that meet certain criteria
- [ ] Create an independent Java console project that will cosume the endpoints of this project.  User can select a number from the menu then enter data, then will see returned information
- 
- 

To save changes to the JSON file, you can use the following steps:

Create a method in the AnimalRepository class to save the animals to the JSON file. This method can take a list of animals as input and write it to the JSON file using the ObjectMapper class.
Call the saveAnimalsToFile() method whenever you want to save changes to the JSON file. For example, you could call it after saving a new animal or updating an existing animal.