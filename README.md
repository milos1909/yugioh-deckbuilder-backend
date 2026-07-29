# Yu-Gi-Oh! Deck Builder Backend

Backend REST API for a **Yu-Gi-Oh! Deck Builder** application that provides card management, deck building, and user collection functionality.

This repository contains the server-side application built with Spring Boot, responsible for handling business logic, database communication, and API endpoints.

## Features

- REST API for card searching and management
- Card details retrieval
- Deck creation and management
- User collection management
- User-related data handling
- User authentication and authorization using JWT tokens
- Database integration
- Communication with frontend application

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT Authentication
- REST API
- MySQL
- Maven

## API

The backend provides REST endpoints used by the frontend application.

Main functionality includes:

- Card management
- Deck management
- User collection management
- User data handling

## Frontend

This backend API is consumed by the Yu-Gi-Oh! Deck Builder frontend application.

[Yu-Gi-Oh! Deck Builder Frontend](https://github.com/milos1909/yugioh-deckbuilder-frontend)

## Project Setup

### Install dependencies

If using Maven:

```sh
mvn install
```

### Run Application

The application can be started using the Spring Boot run configuration in an IDE such as Visual Studio Code or IntelliJ IDEA.

Alternatively, if Maven is configured:

```sh
mvn spring-boot:run
```
