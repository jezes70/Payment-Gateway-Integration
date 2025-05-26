
````markdown
# Payment Gateway Integration

A robust and extensible Payment Gateway Integration API built with Spring Boot.  
This application supports routing, failover, reconciliation, and integrates with multiple mock payment providers while supporting user authentication and role-based access control.

---

# ✨ Features

- JWT-based authentication (Login/Register)
- Role-based access control (`ADMIN`, `MERCHANT`, `CUSTOMER`)
- Payment routing based on transaction amount
- Country-based payment method preference
- Payment failover handling
- Payment status and refund APIs
- Reconciliation reporting with currency conversion
- Clean architecture and design principles (SOLID, DRY)
- Easily extendable provider logic using Strategy, Factory, and Chain-of-Responsibility patterns

---

# 📁 Project Structure

```plaintext
src/
├── config/              # Security, JWT, application configs
├── controller/          # REST API endpoints
├── dto/                 # Request and response objects
├── entity/              # JPA entities: User, Role, Payment, Refund, etc.
├── exception/           # Custom and global exception handling
├── factory/             # Payment provider factory
├── provider/            # Provider interfaces and implementations
├── repository/          # Spring Data repositories
├── service/             # Service interfaces and their implementations
├── strategy/            # Business logic strategies
├── PaymentGatewayIntegrationApplication.java
````

---

# 📐 Architecture Diagram

```plaintext
+-------------+       HTTP w/ JWT      +---------------------+      PostgreSQL
|   Clients   +----------------------> |  Spring Boot App     +---------------------> Store Payments, Users, etc.
| (API Users) |                        |  (payment-gateway)   |
+-------------+                        +----------+----------+
                                                 |
                   +-----------------------------+---------------------------+
                   |                             |                           |
                   v                             v                           v
         +------------------+        +---------------------+         +------------------+
         | Payment Routing  |        | Failover Strategy   |         | Country Preference|
         |   Strategy       |        | (Fallback Logic)    |         |   Strategy        |
         +------------------+        +---------------------+         +------------------+
                   |
                   v
         +------------------+     +------------------+     +------------------+
         | Provider A (3%)  |     | Provider B (2.5%) |     | Provider C (1.8%)|
         +------------------+     +------------------+     +------------------+
```

---

# 🚀 Getting Started

### Prerequisites

* Java 17+
* Maven 3.6+
* PostgreSQL running locally (or update `application.properties`)

### Default Port

The application runs on **port 6666** by default.

### Steps

```bash
git clone https://github.com/jezes70/payment-gateway-integration.git
cd payment-gateway-integration
mvn clean install
mvn spring-boot:run
```

Visit: `http://localhost:6666`

---

# 🔐 Authentication

* Register: `POST /api/auth/register`
* Login: `POST /api/auth/login`
* Auth Required: Use `Bearer <JWT_TOKEN>` in the Authorization header

---

# 📊 Payment Routing Logic

| Amount Range   | Primary Provider | Fee (%) |
| -------------- | ---------------- | ------- |
| < \$100        | Provider A       | 3.0%    |
| \$100 - \$1000 | Provider B       | 2.5%    |
| > \$1000       | Provider C       | 1.8%    |

### Country-based Method Preference

| Region | Preferred Methods             |
| ------ | ----------------------------- |
| US     | CREDIT\_CARD > PAYPAL         |
| EU     | BANK\_TRANSFER > CREDIT\_CARD |
| Asia   | MOBILE > CREDIT\_CARD         |

---

# 📄 API Endpoints

### Auth APIs

* `POST /api/auth/register` – Register user
* `POST /api/auth/login` – Login and receive JWT token

### Payment APIs

* `POST /api/payments` – Process payment
* `GET /api/payments/{id}` – Get payment status
* `POST /api/payments/{id}/refund` – Refund payment
* `GET /api/reconciliation/report` – Generate reconciliation report

---

# 👥 Roles and Permissions

| Role     | Capabilities                       |
| -------- | ---------------------------------- |
| ADMIN    | Full access                        |
| MERCHANT | Can manage their own transactions  |
| CUSTOMER | Can make payments, view own status |

---

# 🧪 Testing

```bash
mvn test
```

---

# 🧱 Tech Stack

* Spring Boot 3.x
* Spring Security + JWT
* PostgreSQL
* JPA (Hibernate)
* Maven
* Java 17
* Strategy, Factory, Chain of Responsibility Patterns

---

# 📦 Future Improvements

* Integration with real external providers (e.g., Stripe, PayPal)
* Admin dashboard with analytics
* Rate limiting and fraud detection
* Event-based workflows (Kafka)
* Caching & Realtime analytics via Redis

---
# 📃 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
