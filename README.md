
# Spring Boot Machine Outlier Detection

This Spring Boot application provides an API for detecting outliers in a collection of machines based on their ages.

## Summary

The application allows users to submit a collection of machines with their respective ages. It then analyzes this data and determines which machines are outliers based on their age, in comparison to the others in the collection. An outlier in this context is a machine whose age does not fall close enough to the other machines' ages.

## Getting Started

### Prerequisites

- Java 11 or newer
- Maven

### Running the Application

1. Navigate to the project root directory.
2. Run the following command to start the application:
```bash
mvn spring-boot:run
```
This will start the application on the default port `8080`.

## API Endpoints

### POST /machines

This endpoint analyzes a list of machines and returns the ones that are considered outliers.

#### Request Body:

A list of machines in the form:

```json
[
    {
        "machineId": "machine_identifier",
        "age": "time_period"
    }
]
```

- `machine_identifier`: A unique identifier for the machine (String).
- `time_period`: A string representing the machine's age. Accepted formats are "X days", "X weeks", "X months", and "X years".

#### Example:

```json
[
    {
        "machineId": "1",
        "age": "1 year"
    },
    {
        "machineId": "2",
        "age": "7 years"
    }
]
```

#### Response:

The API will return a list of machines that are considered outliers.

### Example with `curl`:

To analyze machines and find outliers:

```bash
curl -X POST -H "Content-Type: application/json" -d '[{"machineId": "1", "age": "1 year"}, {"machineId": "2", "age": "7 years"}]' http://localhost:8080/machines
```

## Conclusion

This application provides a simple and efficient way to identify outlier machines in a given dataset based on their ages. It can be integrated into larger systems or used as a standalone service.
