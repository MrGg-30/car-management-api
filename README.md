# Car Management System

## Task Name
Develop a Spring Boot Application for Car Management System

## Description
This project is a Spring Boot application developed using Java and Kotlin that provides a management system for cars. It utilizes Spring MVC, Spring Data, and GraphQL, allowing users to perform various operations related to cars. The application also integrates with a PostgreSQL database.

## Technologies Used
- **Programming Languages:** Kotlin, Java
- **Frameworks:** Spring Boot, Spring MVC, Spring Data, GraphQL
- **Database:** PostgreSQL
- **Build Tool:** Gradle KTS

## GraphQL Operations
The application supports the following GraphQL operations:
1. **Queries**
   - `getAllCars`: Retrieve all cars.
   - `getCarById(id: ID!)`: Retrieve a specific car by its ID.

2. **Mutations**
   - `addCar(input: CarInput!)`: Add a new car.
   - `updateCar(id: ID!, input: CarInput!)`: Update the details of an existing car.
   - `deleteCar(id: ID!)`: Delete a car record.

3. **Subscriptions**
   - `carUpdates`: Livestream new/updated cars (using GraphQL Subscription).

## Car Model
Each car in the database contains the following fields:
- **ID**: Unique identifier
- **Brand**: e.g., "Toyota"
- **Model**: e.g., "Rav4"
- **Color**
- **Year of Production**
- **Price**

## Setup and Running the Application

### Configuration
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/car-management-api.git
   cd car-management-api
2. Build and run the Docker containers:
  docker-compose up --build
3. Access the application at http://localhost:8090/graphiql?path=/graphql
4. Use GraphiQL to interact with the GraphQL API.
