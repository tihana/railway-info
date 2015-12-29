# Railway Info App
Railway Information About The Routes

### Requirements
- Java 1.8
- Git
- Maven

### Features
- Interactive command line interface
- Route search options:
    - Find the distance of the route
    - Find the number of trips between two towns with maximum or exact number of stops
    - Find the length of the shortest route (in terms of distance to travel) between two towns
    - Find the number of different routes between two towns

### Get started
Clone GitHub repo. Compile and run using Maven.
```sh
$ git clone [git-repo-url] railway-info
$ cd railway-info
$ mvn compile
$ mvn exec:java -Dexec.mainClass="org.tgereci.railway.Railway"
```