# 🔐 OTP-Based Passwordless Authentication System

A full-stack authentication system built with **React.js** and **Spring Boot** that allows users to log in using a **6-digit OTP** sent to their email — no passwords required!

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.x-6DB33F?logo=spring-boot)
![React](https://img.shields.io/badge/React-18.x-61DAFB?logo=react)
![Java](https://img.shields.io/badge/Java-21+-ED8B00?logo=java)
![License](https://img.shields.io/badge/License-MIT-blue)

---

## ✨ Features

- ✅ **Passwordless Login** – Users only need their email address
- 📧 **6-Digit OTP** – Secure one-time password sent via email
- ⏱️ **5-Minute Expiry** – OTP expires after 5 minutes for security
- 🎨 **Beautiful UI** – Responsive design with CSS animations
- 🔄 **RESTful API** – Clean separation between frontend and backend
- 🗄️ **Database Persistence** – User data and OTP stored in H2/MySQL
- 🚀 **Async Email** – Non-blocking email sending for better performance

---

## 🛠️ Tech Stack

### Backend (Spring Boot)
| Technology | Version | Purpose |
|:---|:---:|:---|
| Java | 21+ | Programming Language |
| Spring Boot | 3.2.x | Application Framework |
| Spring Data JPA | - | Database ORM |
| Spring Mail | - | Email Service |
| H2 Database | - | In-memory Database |
| Lombok | - | Boilerplate Reduction |

### Frontend (React)
| Technology | Version | Purpose |
|:---|:---:|:---|
| React | 18.x | UI Library |
| React Router DOM | 6.x | Page Navigation |
| Axios | 1.x | HTTP Client |
| CSS3 | - | Styling & Animations |

## 🏗️ Project Structure
otp-login-system/
├── backend/ # Spring Boot Backend
│ ├── src/main/java/com/otplogin/
│ │ ├── config/
│ │ │ └── CorsConfig.java # CORS Configuration
│ │ ├── controller/
│ │ │ └── AuthController.java # REST API Endpoints
│ │ ├── service/
│ │ │ ├── EmailService.java # Email Sending Logic
│ │ │ └── OTPService.java # OTP Generation/Verification
│ │ ├── dto/
│ │ │ ├── LoginRequest.java
│ │ │ ├── OTPRequest.java
│ │ │ └── ApiResponse.java
│ │ ├── entity/
│ │ │ └── User.java # User Entity
│ │ └── repository/
│ │ └── UserRepository.java # JPA Repository
│ └── src/main/resources/
│ └── application.properties # Configuration
│
└── frontend/ # React Frontend
├── src/
│ ├── components/
│ │ ├── Login.jsx # Email Input Page
│ │ ├── Login.css
│ │ ├── VerifyOTP.jsx # OTP Verification Page
│ │ ├── VerifyOTP.css
│ │ ├── Success.jsx # Success Page
│ │ └── Success.css
│ ├── services/
│ │ └── api.js # Axios API Calls
│ ├── App.js
│ └── App.css
└── package.json
