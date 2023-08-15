# Flight Microservice

The Flight Microservice is a Spring Boot application that provides cargo and baggage data as well as flight information.

## Table of Contents

- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [Thing To Do](#things-to-do)
- [License](#license)

## Technologies

- Spring Boot 2.7.14
- Java 11

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java 11 is installed.
- Maven is installed.
- You have basic knowledge of Spring Boot and RESTful APIs.

## Getting Started

1. Clone this repository.
2. Navigate to the project root: `cd flights-ms`.
3. Build the project: `mvn clean install`.
4. Run the application: `java -jar target/flights-ms.jar`.

## Usage

The Flight Microservice provides endpoints to retrieve cargo and baggage data as well as flight information. You can interact with these endpoints using HTTP requests.

## Endpoints

- **GET /cargo**: Retrieve cargo data and baggage data.
- **GET /flight**: Retrieve flights data.

Example usage:

- GET /cargo/weight
- GET /flights/departing

## Configuration

You can configure the application by modifying the `application.properties` file located in the `src/main/resources` directory.

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:
1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`.
3. Make your changes and commit them: `git commit -m "Add your feature"`.
4. Push to the branch: `git push origin feature/your-feature-name`.
5. Create a pull request describing your changes.

## Things To Do

- [ ] I've seperated specification form implementation in both service and repository level so this app can be easily 
extendable; with this feature in the future development we can add mongodb support instead of reading data from json 
  file ,and we can simply have a different implementation for repository specification layer and then when we try to 
  autowire the repository we specify to use mongodb implementation instead.
- [ ] My code can use the benefits of unit tests and also load tests to make sure this code is future proof.
- [ ] Exception handling and logging needs refactoring.

## License

This project is licensed under the [MIT License](LICENSE).