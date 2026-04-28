# 🏥 Clinic Appointment API

A RESTful backend system built using **Spring Boot** and **MongoDB** for managing patients, practitioners, and appointment scheduling with conflict detection and notification handling.

---

# 🚀 How to Run the Project

## Prerequisites

* Java 17+
* Maven
* MongoDB (running locally on default port)

## Steps

1. Clone the repository:

```
git clone https://github.com/your-username/clinic-appointment-api.git
```

2. Navigate to the project:

```
cd clinic-appointment-api
```

3. Run the application:

```
mvn spring-boot:run
```

4. Open Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

---

# 📌 API Modules

## 👤 Patient Module

* Create Patient
* Get Patient by ID
* Search Patients (by name or nationalId)

## 🩺 Practitioner Module

* Create Practitioner
* Get Practitioner by ID
* List all Practitioners

## 📅 Appointment Module

* Create Appointment
* Get Appointment by ID
* Cancel Appointment
* Update Notes
* List Appointments (with pagination + date filter)

---

# ⚙️ Business Rules

* Appointment **end time must be after start time**
* No **overlapping appointments** for the same practitioner
* Only **BOOKED appointments can be cancelled**
* Notifications triggered on:

  * Create → "Appointment booked..."
  * Cancel → "Appointment cancelled..."
  * Update → "Appointment updated..."

---

# 🔔 Design Pattern Used

## Strategy Pattern (Notification)

Implemented to support flexible notification handling.

### Structure:

* `NotificationStrategy` (interface)
* `ConsoleNotification` (implementation)

### Benefit:

Allows easy extension for:

* Email notifications
* SMS notifications

---

# ⚠️ Error Handling

Global exception handling implemented using `@RestControllerAdvice`.

| Status Code | Description                                     |
| ----------- | ----------------------------------------------- |
| 400         | Validation errors                               |
| 404         | Resource not found                              |
| 409         | Conflict (duplicate / overlapping appointments) |

---

# 🧪 Testing

Unit tests implemented for AppointmentService:

* ✅ Overlapping appointment → throws `ConflictException`
* ✅ Non-overlapping appointment → successfully created

---

# 📄 API Endpoints (Sample)

## Create Appointment

**POST** `/api/appointments`

```json
{
  "patientId": "PATIENT_ID",
  "practitionerId": "PRACTITIONER_ID",
  "startTime": "2026-04-28T10:00:00",
  "endTime": "2026-04-28T11:00:00",
  "notes": "General consultation"
}
```

---

## Get Appointments (Pagination + Filter)

**GET**

```
/api/appointments?practitionerId=P1&date=2026-04-28&page=0&size=5
```

---

# 🧠 Assumptions

* MongoDB is running locally
* Notification is implemented using console logging
* Authentication/authorization is not required
* Timezone handled based on system configuration

---

# 🛠 Tech Stack

* Java 17
* Spring Boot
* Spring Data MongoDB
* Jakarta Validation
* Swagger (OpenAPI)
* Maven

---

# 📁 Project Structure

```
controller/
service/
repository/
model/
dto/
mapper/
exception/
notification/
```

---

# ✅ Implementation Checklist

✔ Patient management
✔ Practitioner management
✔ Appointment scheduling
✔ Conflict detection
✔ Pagination & filtering
✔ Notification strategy pattern
✔ Validation (Jakarta)
✔ Global exception handling

---

# 🎯 Conclusion

This project demonstrates a clean layered architecture with proper use of:

* OOP principles
* Design patterns
* RESTful API design
* Error handling and validation

---
