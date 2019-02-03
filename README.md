# car-api

A web service that provides filtering of cars by colour.

## Requirements

The car-api is known to work with:

* JDK 1.8
* Maven 3.6

## Data source

cars.json, located in the root folder, provides the source data for the service.

## Running locally

1. Download or clone the Git repository
2. Navigate to the the car-api root folder
3. Run the application using the command: mvn spring-boot:run

Navigate to <http://localhost:8080/cars?colour=red> to access the site.
Replace "red" in the url with the desired colour.
