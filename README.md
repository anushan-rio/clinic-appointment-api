Here’s a clean, professional **README.md** tailored exactly to your assignment document requirements.

---

# 🏥 Clinic Appointment & Notification API

A RESTful API built using **Spring Boot** and **MongoDB** to manage Patients, Practitioners, and Appointments with automated notification handling.

---

## 📌 Objective

This project implements a clinic management system that allows:

* Managing Patients and Practitioners
* Booking and managing Appointments
* Triggering Notifications on appointment events

It demonstrates:

* REST API development using Spring Boot
* MongoDB integration
* Object-Oriented Programming (OOP) concepts
* Design Pattern implementation

---

## ⚙️ Tech Stack

* Java 17+
* Spring Boot 3.x
* Spring Web
* Spring Data MongoDB
* Jakarta Bean Validation
* Maven
* Swagger (OpenAPI) *(optional but implemented)*
* MongoDB (Local)

---

## 🧱 Architecture

The project follows a clean layered architecture:

```
Controller → Service → Repository → Database
             ↓
            DTOs
```

* **Controller** → Handles HTTP requests
* **Service** → Business logic & rules
* **Repository** → MongoDB interaction
* **DTOs** → Request/Response separation
* **Model** → Database entities

---

## 🧍 OOP Design

Implemented using **Inheritance**:

```
Person
 ├── Patient
 └── Practitioner
```

* Promotes reusability
* Demonstrates polymorphism (used in notification handling)

---

## 📦 Entities

### Patient

* id
* fullName *(min 3 chars)*
* nationalId *(unique)*
* dateOfBirth
* email (optional)
* phone (optional)

### Practitioner

* id
* fullName
* registrationNo *(unique)*
* specialty

### Appointment

* id
* patientId
* practitionerId
* startTime
* endTime
* status *(BOOKED, CANCELLED, COMPLETED)*
* notes
* createdAt, updatedAt

---

## ⚠️ Business Rules

### Appointment Conflict Rule

A practitioner cannot have overlapping appointments:

```
(newStart < existingEnd) AND (newEnd > existingStart)
```

Applies only when status = BOOKED.

---

### Status Rules

* Only **BOOKED** appointments can be cancelled
* Completed appointments are not editable *(assumption implemented)*

---

## 🔔 Design Pattern Used

### ✅ Strategy Pattern + Factory Pattern

Implemented for Notification handling.

#### Structure:

* `NotificationStrategy` (interface)
* Implementations:

  * EmailNotificationStrategy
  * SMSNotificationStrategy
  * ConsoleNotificationStrategy
* `NotificationStrategyFactory` selects strategy

#### Why?

* Easily extend notification channels
* Promotes Open/Closed Principle
* Clean separation of logic

---

## 🔔 Notification Behavior

| Event     | Message               |
| --------- | --------------------- |
| Created   | Appointment booked    |
| Cancelled | Appointment cancelled |
| Updated   | Appointment updated   |

(Currently implemented using console logging)

---

## 📡 API Endpoints

### 👤 Patients

* `POST /api/patients` → Create Patient
* `GET /api/patients/{id}` → Get Patient
* `GET /api/patients?nationalId=...&name=...` → Search

---

### 🩺 Practitioners

* `POST /api/practitioners` → Create Practitioner
* `GET /api/practitioners/{id}` → Get Practitioner
* `GET /api/practitioners` → List

---

### 📅 Appointments

* `POST /api/appointments` → Create Appointment
* `GET /api/appointments/{id}` → Get Appointment
* `GET /api/appointments?practitionerId=&date=&page=&size=` → List
* `PATCH /api/appointments/{id}/cancel` → Cancel
* `PATCH /api/appointments/{id}` → Update Notes

---

## 🧪 Sample Request

### Create Appointment

```json
{
  "patientId": "PATIENT_ID_1",
  "practitionerId": "PRACTITIONER_ID_1",
  "startTime": "2026-04-28T10:00:00",
  "endTime": "2026-04-28T11:00:00",
  "notes": "Regular checkup"
}
```

---

## ❌ Error Handling

Standard error format:

```json
{
  "timestamp": "2026-04-24T10:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "details": [
    "fullName must be at least 3 characters"
  ],
  "path": "/api/appointments"
}
```

Handled:

* 400 → Validation errors
* 404 → Not found
* 409 → Appointment conflict

---

## 🧪 Testing

* ✅ Unit tests for appointment conflict rule
* ✅ Service test for appointment creation

Priority given to business logic validation.

---

## 🚀 How to Run

### 1. Clone Repository

```bash
git clone https://github.com/your-username/clinic-appointment-api.git
cd clinic-appointment-api
```

---

### 2. Configure MongoDB

Make sure MongoDB is running locally:

```bash
mongodb://localhost:27017/clinic_db
```

---

### 3. Update `application.properties`

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/clinic_db
spring.data.mongodb.database=clinic_db
```

---

### 4. Run Application

Using Maven:

```bash
mvn spring-boot:run
```

---

### 5. Access Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📬 Postman Collection

Import the provided Postman collection JSON into Postman to test APIs.

---

## 🎯 Assumptions & Trade-offs

* Completed appointments are not editable
* Notifications are console-based (no external service)
* Pagination is implemented only for appointments
* No authentication implemented (optional feature skipped)

---

## ⭐ Bonus Features Implemented

* Swagger/OpenAPI
* Validation with Jakarta Bean Validation
* Clean layered architecture

---

## 📊 Evaluation Coverage

This project satisfies:

* ✅ Core functionality (CRUD + conflict rule)
* ✅ Clean architecture
* ✅ OOP implementation
* ✅ Design pattern usage
* ✅ Validation & error handling
* ✅ Testing

---
