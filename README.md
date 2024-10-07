# 🛠️ Hardware Store Management System

**A Jakarta EE-based Enterprise application for managing a hardware store, developed with IntelliJ IDEA using a modular
architecture involving WAR, EAR, EJB, and utility modules.**

## 📋 Table of Contents

- [About](#about)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Modules Overview](#modules-overview)
    - [WAR Module](#war-module)
    - [EJB Module](#ejb-module)
    - [JPA Module](#-jpa-module)
    - [Utility Module](#utility-module)
    - [EAR Module](#ear-module)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## 📝 About

**Hardware Store Management System** is an enterprise-level application to manage products, customers, and orders in a
hardware store. It follows best practices for Jakarta EE applications and uses a modular structure to ensure easy
scalability, maintainability, and separation of concerns.

The project consists of:

- **Web Layer**: A user interface and REST APIs.
- **Business Logic Layer**: Business services using EJB.
- **Persistence Layer**: JPA entities for data access.
- **EAR Deployment**: Bundled for deployment on an application server.

---

## 💻 Technologies Used

- **Jakarta EE 10** (Servlets, EJB, JPA, CDI, JSF)
- **Maven** (for build automation)
- **IntelliJ IDEA Ultimate Edition**
- **WildFly/GlassFish** (for application server)
- **Java 21** (or higher)

---

## 📂 Project Structure

The project is divided into multiple modules to achieve a clean and modular architecture:

---

## 📦 Modules Overview

### 🌐 WAR Module

- **Artifact ID**: `hardware-store-war`
- **Packaging**: `war`
- **Responsibilities**:
    - UI Components (using JSF)
    - RESTful API Endpoints (using JAX-RS)
    - Handles customer and product interactions

### 🏢 EJB Module

- **Artifact ID**: `hardware-store-ejb`
- **Packaging**: `ejb`
- **Responsibilities**:
    - Contains business logic
    - EJB services such as `ProductService`, `CustomerService`, `OrderService`
    - Uses `@Stateless` for stateless services

### 🗄️ JPA Module

- **Artifact ID**: `hardware-store-jpa`
- **Packaging**: `jar`
- **Responsibilities**:
    - JPA entities and repositories for managing database interaction
    - Persistence configuration with `persistence.xml`
    - Contains entities like `Product`, `Customer`, `Order`

### 🔧 Utility Module

- **Artifact ID**: `hardware-store-util`
- **Packaging**: `jar`
- **Responsibilities**:
    - Shared utilities, constants, and common classes
    - Provides utility methods for validation and logging

### 📦 EAR Module

- **Artifact ID**: `hardware-store-ear`
- **Packaging**: `ear`
- **Responsibilities**:
    - Bundles the WAR, EJB, and utility modules into a deployable unit
    - `application.xml` contains deployment information

---

## ⚙️ Installation

To set up the project locally:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/hardware-store.git
   cd hardware-store

2. **Build with Maven**:
    ```bash
   mvn clean install

3. **Configure the Application Server**:
    - Set up **WildFly** or **GlassFish** in IntelliJ.
    - Deploy the **`hardware-store-ear`** module.


## 🚀 Usage

**Running the Application**:

- Once deployed on the server, the application can be accessed via a web browser at:

  ```http request
  http://localhost:8080/hardware-store

### REST API Endpoints:

* The REST API endpoints are exposed via /api.

* Example endpoints:
    * **GET** `/api/products`: List all products.
    * **POST** `/api/customers`: Create a new customer.

# 🤝 Contributing

We welcome contributions to improve this project. Please follow the steps below:

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Open a pull request.

# 📧 Contact

For any queries or suggestions, please contact:

* Project Maintainer: Stainley Lebron
* GitHub: https://github.com/stainley

🛠️ Happy Coding!

