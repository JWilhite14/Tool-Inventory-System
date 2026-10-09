# Tool Inventory System (CLI - Phase 1)

A robust, object-oriented command-line interface (CLI) tool inventory management system built in Core Java. Designed with a clean enterprise architectural separation of concerns and local file persistence.

## 🚀 Project Intent & Overview
Managing tool crib assets effectively requires reliable tracking of tool availability, assignments, and audit states. This project bridges academic computer science theory and industry-standard practices by implementing a layered architecture, data serialization, business rule validation, and functional stream processing from scratch without relying on external frameworks.

## 🏛️ Architectural Breakdown
The application is structured into distinct, decoupled layers to ensure high maintainability and scalability:

*   **`model`**: Houses core data entities (`Tool`) and enumerations (`ToolStatus` like `AVAILABLE`, `CHECKED_OUT`, `IN_REPAIR`) with built-in validation (e.g., automated uppercase formatting for Tool IDs).
*   **`repository`**: Handles persistence and file I/O using Java NIO (`Files`) and Java Object Serialization (`ObjectOutputStream`/`ObjectInputStream`) to safely read and write inventory states to disk (`inventory_data.dat`).
*   **`service`**: Implements the business logic layer. Acts as the mediator between user input and storage, enforcing transaction rules (e.g., preventing a checked-out tool from being re-issued) and executing searches via **Java Streams** and lambda expressions.
*   **`Main`**: Serves as the interactive CLI control loop, utilizing modern switch expressions and `Scanner` inputs to route commands cleanly.

## 🛠️ Tech Stack
*   **Language:** Core Java
*   **Persistence:** Java NIO & Object Serialization
*   **Data Structures:** `HashMap`, `ArrayList`, `Optional` for null-safety
*   **Querying:** Java Streams API & Lambda Expressions
*   **Version Control:** Git & GitHub

## 🗺️ Roadmap & Future Phases
*   **Phase 1 (Current):** Standalone Core Java CLI application with local binary file persistence. ✅
*   **Phase 2 (Upcoming):** Migration to a **Spring Boot REST API** framework with `@RestController` endpoints.
*   **Phase 3 (Planned):** Integration of a relational database (**PostgreSQL**) via Spring Data JPA / Hibernate to replace raw file serialization.
*   **Phase 4 (Planned):** Implementation of **Spring Security** and JWT-based authentication for role-based tool management.

## ⚙️ Getting Started
1. Clone the repository:
   ```bash
   git clone [https://github.com/JWilhite14/Tool-Inventory-System.git](https://github.com/JWilhite14/Tool-Inventory-System.git)
