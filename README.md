# Smart Expense Tracker API

## Overview

Smart Expense Tracker API is a Java 8 and Spring Boot REST API application for managing personal expenses.

The application allows users to add, view, filter, search, calculate totals, and delete expenses. It also includes a responsive web dashboard for interacting with expense data.

Expense data is stored in memory while the application is running.

## Features

### Expense Management

* Add a new expense
* View all expenses
* Delete expenses
* Filter expenses by category
* Search expenses by title

### Expense Analytics

* Calculate overall expense total
* Calculate total for a specific category
* View totals grouped by category
* Monthly expense summary with:

  * Total expense amount
  * Expense count
  * Category-wise totals

### API Documentation

* Swagger UI documentation
* OpenAPI JSON documentation

## Tech Stack

* Java 8
* Spring Boot
* Spring Web
* Springdoc OpenAPI
* Maven
* JUnit Testing

## Project Structure

```
Smart Expense Tracker API/
│
├── README.md
├── AI_NOTES.md
├── src/
│
└── tests/
```

## Installation

Clone the repository:

```bash
git clone <repository-url>
```

Navigate into the project:

```bash
cd "Smart Expense Tracker API"
```

## Run the Application

Make sure Maven is installed.

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

Open the URL in a browser to access the dashboard.

## Run Tests

Run the test suite using:

```bash
mvn test
```

Test Result:

```
Tests run: 2
Failures: 0
```

## API Endpoints

| Method | Endpoint                                          | Purpose                        |
| ------ | ------------------------------------------------- | ------------------------------ |
| POST   | `/api/expenses`                                   | Add an expense                 |
| GET    | `/api/expenses`                                   | List all expenses              |
| GET    | `/api/expenses?category=Food`                     | Filter expenses by category    |
| GET    | `/api/expenses?search=grocer`                     | Search expenses by title       |
| GET    | `/api/expenses/total`                             | Get overall total              |
| GET    | `/api/expenses/total?category=Food`               | Get total for one category     |
| GET    | `/api/expenses/totals-by-category`                | Get totals grouped by category |
| GET    | `/api/expenses/summary/monthly?year=2026&month=7` | Monthly expense summary        |
| DELETE | `/api/expenses/{id}`                              | Delete an expense              |

## Swagger / OpenAPI Documentation

Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```
http://localhost:8080/v3/api-docs
```

## Add an Expense Example

Request:

```
POST /api/expenses
```

Content-Type:

```
application/json
```

Body:

```json
{
  "title": "Groceries",
  "amount": 42.50,
  "category": "Food",
  "date": "2026-07-31"
}
```

Validation:

* Title, amount, category, and date are required.
* Amount must be greater than zero.
* Date format should be `yyyy-MM-dd`.

## Optional Features Implemented

* Expense Search
* Monthly Summary Endpoint
* Swagger/OpenAPI Documentation

## Future Improvements

* Database integration
* User authentication
* Docker support
* Cloud deployment
