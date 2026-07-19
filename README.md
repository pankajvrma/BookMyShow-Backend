🎬 Movie Ticket Booking System - Backend

A BookMyShow-inspired Movie Ticket Booking System built using Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL. The application provides end-to-end movie ticket booking functionality, including theater management, show scheduling, seat management, booking creation, and payment processing.

🚀 Features
User Management
Register and manage users
View user details
Movie Management
Add new movies
View movie details
List all movies
Theater & Screen Management
Create theaters
Create screens within theaters
Manage seating capacity
Seat Management
Create seats for screens
View seats by screen
Manage seat categories and pricing
Show Management
Schedule movie shows
Associate movies with screens
View available shows
Search shows by movie
Show Seat Management
Auto-generate seats for each show
Track seat availability
Manage seat booking status
Booking Management
Create bookings
Select multiple seats
Generate booking number
View booking details
Cancel bookings
Payment Management
Process payments
Generate transaction IDs
Handle payment status
Refund on booking cancellation
🏗️ Architecture

The application follows a layered architecture:

Controller Layer
       ↓
Service Layer
       ↓
Repository Layer
       ↓
MySQL Database
Design Pattern Used
DTO Pattern
Service Layer Pattern
Repository Pattern
Dependency Injection
Transaction Management
🛠️ Tech Stack
Backend
Java 17
Spring Boot
Spring Data JPA
Hibernate
Maven
Database
MySQL
Testing
Postman
Tools
IntelliJ IDEA
Git
GitHub
📂 Project Structure
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
│
├── entity
│
├── dto
│
├── exception
│
└── config
🗄️ Database Flow
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
🎟️ Booking Workflow
Step 1: User selects a show
User
 ↓
Movie
 ↓
Show
Step 2: Fetch available seats
GET /api/show-seats/show/{showId}
Step 3: User selects seats
{
  "seatIds": [1,2]
}
Step 4: Create Booking
{
  "userId": 1,
  "showId": 1,
  "seatIds": [1,2],
  "paymentMethod": "UPI"
}
Step 5: System Validation
Verify User
Verify Show
Verify Seats
Check Availability
Step 6: Seat Locking
AVAILABLE
    ↓
LOCKED
Step 7: Payment Processing
SUCCESS
Step 8: Confirm Booking
LOCKED
    ↓
BOOKED
Step 9: Generate Ticket
Booking Number
Transaction ID
🔄 Booking Cancellation Flow
CONFIRMED
      ↓
CANCELLED

Actions performed:

Booking status updated
Seats released
Payment refunded
BOOKED
   ↓
AVAILABLE
SUCCESS
   ↓
REFUNDED
📌 Important APIs
Movies
POST /api/movies
GET  /api/movies
GET  /api/movies/{id}
Users
POST /api/users
GET  /api/users
GET  /api/users/{id}
Theaters
POST /api/theaters
GET  /api/theaters
GET  /api/theaters/{id}
Screens
POST /api/screens
GET  /api/screens
GET  /api/screens/{id}
Seats
POST /api/seats
GET  /api/seats
GET  /api/seats/{id}
GET  /api/seats/screen/{screenId}
Shows
POST /api/shows
GET  /api/shows
GET  /api/shows/{id}
Show Seats
GET /api/show-seats/show/{showId}
GET /api/show-seats/show/{showId}/available
Bookings
POST /api/bookings
GET  /api/bookings/{id}
Payments
POST /api/payments
GET  /api/payments
GET  /api/payments/{id}
⚡ Key Concepts Implemented
Spring Boot
REST APIs
Dependency Injection
Validation
Exception Handling
JPA/Hibernate
Entity Relationships
Lazy/Eager Loading
Repository Pattern
Transaction Management
Database
Foreign Keys
Normalization
Relationship Mapping
Business Logic
Seat Availability Tracking
Booking Lifecycle Management
Payment Processing
Refund Handling
🔮 Future Enhancements
JWT Authentication & Authorization
Spring Security
Redis Seat Locking
Email Notifications
Payment Gateway Integration
Docker Support
Kafka Event Streaming
Microservices Architecture
API Documentation using Swagger/OpenAPI
CI/CD Pipeline



👨‍💻 Author

Pankaj Verma
