Things to add


## []: # Path: README.md

List of things to add to code
- [ ] Add a way to add a new user
- [ ] Add a global properties file
- [ ] Add logger with logging
- [ ] Investigate having updated animals list written back to json (see below)
- [ ] Add a kind for each animal....a new field in the json file
- [ ] Add modifiedDate



To save changes to the JSON file, you can use the following steps:

Create a method in the AnimalRepository class to save the animals to the JSON file. This method can take a list of animals as input and write it to the JSON file using the ObjectMapper class.
Call the saveAnimalsToFile() method whenever you want to save changes to the JSON file. For example, you could call it after saving a new animal or updating an existing animal.