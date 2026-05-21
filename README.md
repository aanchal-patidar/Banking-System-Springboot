# Bank Management System

A secure and scalable backend application developed using Java and Spring Boot that provides REST APIs for managing banking operations such as customer management, account handling, transactions, loan processing, and authentication using JWT Security.

This project follows a layered architecture and demonstrates real-world backend development concepts including authentication, authorization, exception handling, database integration, and RESTful API design.

---

# Project Overview

The Bank Management System is designed to simulate core banking functionalities digitally. The application allows users to securely manage bank accounts and perform banking operations through APIs.

The system focuses on:

- Secure authentication using JWT
- Managing customer and account information
- Handling deposits, withdrawals, and transactions
- Loan management operations
- Clean and maintainable backend architecture

This project is suitable for learning enterprise-level backend development using Spring Boot and can be extended into a full-stack banking application.

---

# Features

## Authentication & Security
- User Login Authentication
- JWT Token Generation
- Secure API Access
- Spring Security Integration
- Protected Endpoints using Authorization Header

## Customer Management
- Add New Customer
- Fetch Customer Details
- Update Customer Information
- Delete Customer Records

## Account Management
- Create New Bank Account
- View Account Details
- Deposit Money
- Withdraw Money
- Balance Management

## Transaction Management
- Money Transfer Between Accounts
- Transaction History Tracking
- Transaction Validation

## Loan Management
- Apply for Loan
- View Loan Details
- Update Loan Status

## Exception Handling
- Global Exception Handling
- Custom Error Responses
- Validation Handling

---

# Tech Stack

## Backend Technologies
- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate
- JWT Authentication
- Maven

## Database
- Postgresql

## Development Tools
- Postman
- VS Code / IntelliJ IDEA
- Git & GitHub

---

# Architecture Used

This project follows a layered architecture for better code organization and maintainability.

```bash
Controller Layer
↓
Service Layer
↓
Repository Layer
↓
Database
