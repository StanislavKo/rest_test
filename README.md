# Arithmetic operations

Swagger API documentation: http://localhost:8080/v2/api-docs

# Configuration

src\main\resources\application.yaml and src\main\resources\application-dev.yaml contains configuration of the program.

- redis.host and redis.password
- logging.level.com.hsd

# Build

Java 8 and maven should be installed to build the program.

cd project_folder
mvn clean package

# Launch

cd project_folder
java -Dspring.profiles.active=dev -jar target/calculator-0.0.1-SNAPSHOT.jar

# Workflow

Check sequence_diagram.png

Entrypoint is ArithmeticController (RestController). Spring doesn't verify input parameters as ints, it's executed manually (for instance, some literal constants may be supported later).
cacheBean checks if operation was performed earlier and return a value or null.
If null is returned then calculation in ArithmeticsUtils is executed (including argument verification). 
Then result is stored by cacheBean. Values are expired in 7 days.
Then response object is created and returned to a client.

