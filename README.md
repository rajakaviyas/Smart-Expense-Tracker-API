# Smart Expense Tracker API

Java 8 / Spring Boot REST API for managing personal expenses. Data is held in memory while the application is running.

The application also includes a responsive web dashboard. After starting the app, open [http://localhost:8080](http://localhost:8080) in a browser to add, filter, total, and delete expenses.

## Run

Install Maven, then run:

```powershell
mvn spring-boot:run
```

The service starts at `http://localhost:8080`.

## Endpoints

| Method | Endpoint | Purpose |
| --- | --- | --- |
| POST | `/api/expenses` | Add an expense |
| GET | `/api/expenses` | List all expenses |
| GET | `/api/expenses?category=Food` | Filter by category |
| GET | `/api/expenses?search=grocer` | Search expenses by title |
| GET | `/api/expenses/total` | Overall total |
| GET | `/api/expenses/total?category=Food` | Total for one category |
| GET | `/api/expenses/totals-by-category` | Totals grouped by category |
| GET | `/api/expenses/summary/monthly?year=2026&month=7` | Total, count, and category totals for a month |
| DELETE | `/api/expenses/{id}` | Delete an expense |

## API documentation

Swagger UI is available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html). The OpenAPI JSON document is available at `/v3/api-docs`.

### Add an expense

```http
POST /api/expenses
Content-Type: application/json

{
  "title": "Groceries",
  "amount": 42.50,
  "category": "Food",
  "date": "2026-07-31"
}
```

`title`, `amount`, `category`, and `date` are required. Amount must be greater than zero; dates use `yyyy-MM-dd`.

## Docker

Build and run the complete web application (frontend and API) in a container:

```powershell
docker build -t smart-expense-tracker .
docker run --rm -p 8080:8080 smart-expense-tracker
```

Open `http://localhost:8080` after the container starts.
