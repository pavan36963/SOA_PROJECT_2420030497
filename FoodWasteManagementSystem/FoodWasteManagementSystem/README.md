# Food Waste Management and Tracking System

A beginner-friendly Spring Boot REST API project for an SOA Programming demonstration.

## Included features
- Create, view, update, and delete food records
- Record location, food name, prepared quantity (kg), and consumed quantity (kg)
- Calculate wasted quantity and waste percentage
- Filter food records by location
- Generate an overall waste summary report
- Request validation and simple API error responses
- H2 in-memory database by default, so the project runs without MySQL setup
- Optional MySQL configuration in `src/main/resources/application.properties`

## Technology
- Java 17
- Spring Boot 3.3.5
- Spring Web / REST
- Spring Data JPA
- H2 (default) or MySQL
- Maven
- Postman for API testing

## Import into Eclipse
1. Extract the ZIP.
2. Open Eclipse with Spring Tools installed.
3. Select **File → Import → Maven → Existing Maven Projects**.
4. Browse to the extracted `FoodWasteManagementSystem` folder.
5. Select `pom.xml`, then click **Finish**.
6. Wait for Maven dependencies to download.
7. Right-click `FoodWasteManagementApplication.java` → **Run As → Spring Boot App** (or Java Application).
8. The server should start at `http://localhost:8080`.

Java 17 or newer and an internet connection for the first Maven dependency download are recommended.

## Postman API examples
Base URL: `http://localhost:8080`

### 1. Create a food record
- Method: `POST`
- URL: `http://localhost:8080/api/foods`
- Header: `Content-Type: application/json`
- Body → raw → JSON:

```json
{
  "locationName": "College Canteen",
  "foodName": "Rice",
  "preparedQuantityKg": 100,
  "consumedQuantityKg": 80
}
```

Expected result: `201 Created`, with wasted quantity `20.0 kg` and waste percentage `20.0`.

### 2. Get all food records
- Method: `GET`
- URL: `http://localhost:8080/api/foods`

### 3. Get a single record
- Method: `GET`
- URL: `http://localhost:8080/api/foods/1`

### 4. Get records for a location
- Method: `GET`
- URL: `http://localhost:8080/api/foods/location/College%20Canteen`

### 5. Update a record
- Method: `PUT`
- URL: `http://localhost:8080/api/foods/1`
- Body → raw → JSON:

```json
{
  "locationName": "College Canteen",
  "foodName": "Rice",
  "preparedQuantityKg": 100,
  "consumedQuantityKg": 75
}
```

### 6. Delete a record
- Method: `DELETE`
- URL: `http://localhost:8080/api/foods/1`

### 7. Overall waste report
- Method: `GET`
- URL: `http://localhost:8080/api/reports/summary`

### 8. Report for one record
- Method: `GET`
- URL: `http://localhost:8080/api/reports/record/1`

## Notes about SOA
This starter implementation groups the responsibilities into food-record endpoints, waste calculation logic, and reporting endpoints within one Spring Boot application. These are separate modules/responsibilities, **not yet independently deployed microservices**. If your rubric requires independently deployable services, they should be split into separate Spring Boot applications.

## Important
This is a starter project and has not been run in your local Eclipse environment. Import it, allow Maven to resolve dependencies, then run and test the endpoints in Postman. Verify all endpoints before presenting them as implemented.
