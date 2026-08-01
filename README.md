# Smart Expense Tracker API

## Overview

Smart Expense Tracker API is a Java 8 and Spring Boot REST API application for managing personal expenses.

The application allows users to add, view, filter, search, calculate totals, and delete expenses. It also includes a responsive web dashboard for interacting with expense data.

Expense data is stored in memory while the application is running.

---

## Features

### Expense Management

* Add a new expense
* View all expenses
* Delete an expense
* Filter expenses by category
* Search expenses by title

### Expense Analytics

* Calculate the overall expense total
* Calculate the total for a specific category
* View totals grouped by category
* Monthly expense summary including:

  * Total expense amount
  * Expense count
  * Category-wise totals

### API Documentation

* Swagger UI
* OpenAPI JSON Documentation

---

## Tech Stack

* Java 8
* Spring Boot
* Spring Web
* Springdoc OpenAPI
* Maven
* JUnit

---

## Project Structure

```text
Smart Expense Tracker API/
│
├── src/
├── .gitignore
├── AI_NOTES.md
├── README.md
└── pom.xml
```

---

## Prerequisites

Before running the project, make sure the following are installed:

* Java 8 or later
* Apache Maven

---

## Installation

Clone the repository:

```bash
git clone https://github.com/rajakaviyas/Smart-Expense-Tracker-API.git
```

Navigate to the project folder:

```bash
cd "Smart Expense Tracker API"
```

Install the project dependencies:

```bash
mvn clean install
```

---

## Run the Application

Start the Spring Boot application:

```bash
mvn spring-boot:run
```


The application starts at:

```text
http://localhost:8080
```

Open the above URL in a browser to access the web dashboard.

---

## Run Tests

Run the test suite using:

```bash
mvn test
```

Expected result:

```text
Tests run: 2
Failures: 0
```

---

## API Endpoints

| Method | Endpoint                                          | Purpose                           |
| ------ | ------------------------------------------------- | --------------------------------- |
| POST   | `/api/expenses`                                   | Add a new expense                 |
| GET    | `/api/expenses`                                   | List all expenses                 |
| GET    | `/api/expenses?category=Food`                     | Filter expenses by category       |
| GET    | `/api/expenses?search=grocer`                     | Search expenses by title          |
| GET    | `/api/expenses/total`                             | Get overall expense total         |
| GET    | `/api/expenses/total?category=Food`               | Get total for a specific category |
| GET    | `/api/expenses/totals-by-category`                | Get totals grouped by category    |
| GET    | `/api/expenses/summary/monthly?year=2026&month=7` | Get monthly expense summary       |
| DELETE | `/api/expenses/{id}`                              | Delete an expense                 |

---

## Swagger / OpenAPI Documentation

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

---

## Example Request

### Add an Expense

**Request**

```http
POST /api/expenses
Content-Type: application/json
```

**Request Body**

```json
{
  "title": "Groceries",
  "amount": 42.50,
  "category": "Food",
  "date": "2026-07-31"
}
```

### Validation Rules

* `title` is required.
* `amount` is required and must be greater than zero.
* `category` is required.
* `date` is required and must follow the `yyyy-MM-dd` format.

---

## Optional Features Implemented

* Expense Search
* Monthly Summary Endpoint
* Swagger/OpenAPI Documentation

---

## Future Improvements

* Persistent database storage
* User authentication and authorization
* Docker support
* Cloud deployment

