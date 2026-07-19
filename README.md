# 🎬 Movie Ticket Booking System

A BookMyShow-inspired Movie Ticket Booking Backend developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL**. The application provides complete movie ticket booking functionality including movie management, theater management, show scheduling, seat allocation, booking creation, and payment processing.

---

## 🚀 Features

### 👤 User Management
- Create and manage users
- Retrieve user details

### 🎥 Movie Management
- Add new movies
- View movie details
- List all available movies

### 🏢 Theater Management
- Create theaters
- Manage theater information

### 🎭 Screen Management
- Create screens inside theaters
- Configure seating capacity

### 💺 Seat Management
- Create seats for screens
- Support different seat categories
- Configure seat pricing

### 🎬 Show Management
- Schedule movie shows
- Associate movies with screens
- View available shows

### 🎟️ Show Seat Management
- Auto-generate seats for every show
- Track seat availability
- Manage booking status

### 📖 Booking Management
- Book movie tickets
- Select multiple seats
- Generate booking number
- View booking details
- Cancel bookings

### 💳 Payment Management
- Process payments
- Generate transaction IDs
- Handle refunds during cancellation

---

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

### Database
- MySQL

### Testing
- Postman

### Version Control
- Git
- GitHub

---

## 🏗️ Architecture

```text
Controller Layer
       ↓
Service Layer
       ↓
Repository Layer
       ↓
MySQL Database
```

---

## 📂 Project Structure

```text
src/main/java
│
├── controller
│   ├── UserController
│   ├── MovieController
│   ├── TheaterController
│   ├── ScreenController
│   ├── SeatController
│   ├── ShowController
│   ├── ShowSeatController
│   ├── BookingController
│   └── PaymentController
│
├── service
│   ├── UserService
│   ├── MovieService
│   ├── TheaterService
│   ├── ScreenService
│   ├── SeatService
│   ├── ShowService
│   ├── ShowSeatService
│   ├── BookingService
│   └── PaymentService
│
├── repository
├── entity
├── dto
├── exception
└── config
```

---

## 🗄️ Database Flow

```text
Theater
   ↓
Screen
   ↓
Seat
   ↓
Movie
   ↓
Show
   ↓
ShowSeat
   ↓
Booking
   ↓
Payment
```

---

## 🎟️ Booking Workflow

```text
User
  ↓
Select Movie
  ↓
Select Show
  ↓
Fetch Available Seats
  ↓
Select Seats
  ↓
Create Booking
  ↓
Validate Seat Availability
  ↓
Lock Seats
  ↓
Process Payment
  ↓
Confirm Booking
  ↓
Generate Ticket
```

### Seat Status Flow

```text
AVAILABLE
    ↓
LOCKED
    ↓
BOOKED
```

### Booking Cancellation Flow

```text
CONFIRMED
      ↓
CANCELLED
```

### Refund Flow

```text
BOOKED
   ↓
AVAILABLE
```

```text
SUCCESS
   ↓
REFUNDED
```

---

## 📌 REST APIs

### Movies

| Method | Endpoint |
|----------|----------|
| POST | `/api/movies` |
| GET | `/api/movies` |
| GET | `/api/movies/{id}` |

### Users

| Method | Endpoint |
|----------|----------|
| POST | `/api/users` |
| GET | `/api/users` |
| GET | `/api/users/{id}` |

### Theaters

| Method | Endpoint |
|----------|----------|
| POST | `/api/theaters` |
| GET | `/api/theaters` |
| GET | `/api/theaters/{id}` |

### Screens

| Method | Endpoint |
|----------|----------|
| POST | `/api/screens` |
| GET | `/api/screens` |
| GET | `/api/screens/{id}` |

### Seats

| Method | Endpoint |
|----------|----------|
| POST | `/api/seats` |
| GET | `/api/seats` |
| GET | `/api/seats/{id}` |
| GET | `/api/seats/screen/{screenId}` |

### Shows

| Method | Endpoint |
|----------|----------|
| POST | `/api/shows` |
| GET | `/api/shows` |
| GET | `/api/shows/{id}` |

### Show Seats

| Method | Endpoint |
|----------|----------|
| GET | `/api/show-seats/show/{showId}` |
| GET | `/api/show-seats/show/{showId}/available` |

### Bookings

| Method | Endpoint |
|----------|----------|
| POST | `/api/bookings` |
| GET | `/api/bookings/{id}` |

### Payments

| Method | Endpoint |
|----------|----------|
| POST | `/api/payments` |
| GET | `/api/payments` |
| GET | `/api/payments/{id}` |

---

## 🔥 Key Concepts Implemented

- RESTful API Development
- DTO Pattern
- Service Layer Pattern
- Repository Pattern
- Dependency Injection
- Exception Handling
- Transaction Management
- Entity Relationship Mapping
- Seat Availability Tracking
- Booking Lifecycle Management
- Payment Processing Workflow

---

## 🔮 Future Enhancements

- JWT Authentication & Authorization
- Spring Security
- Redis-based Seat Locking
- Email Notifications
- Payment Gateway Integration
- Docker Support
- Kafka Integration
- Swagger/OpenAPI Documentation
- CI/CD Pipeline

---

## 👨‍💻 Author

**Pankaj Verma**
