# 🗄️ Hardware Store JPA Module

**This is the JPA (Java Persistence API) module of the Hardware Store Management System, which is responsible for
managing database interactions using Jakarta Persistence.**

---

## 📋 Table of Contents

- [About](#about)
- [Technologies Used](#technologies-used)
- [Entities](#entities)
    - [Product Entity](#product-entity)
    - [Customer Entity](#customer-entity)
    - [Order Entity](#order-entity)
- [Persistence Configuration](#persistence-configuration)
- [Database Setup](#database-setup)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## 📝 About

The **JPA Module** (`hardware-store-jpa`) is part of the multi-module Jakarta EE-based Hardware Store Management System.
It provides persistence capabilities to interact with the relational database by mapping Java objects (entities) to
database tables.

The JPA module is designed to:

- **Define Entities**: Represent the data structure for the `Product`, `Customer`, `Order`, etc.
- **Manage Persistence**: Provide an easy interface to interact with the database using `EntityManager`.

---

## 💻 Technologies Used

- **Jakarta EE 10** (specifically **Jakarta Persistence**)
- **Java 11** (or higher)
- **Maven** (build and dependency management)

---

## 📦 Entities

### 📦 Product Entity

The `Product` entity represents the details of products available in the hardware store.

- **Fields**:
    - `id`: Primary key for the product.
    - `name`: Product name.
    - `price`: Cost of the product.
    - `stockQuantity`: Number of items in stock.

- **Entity Code Example**:
  ```java
  @Entity
  @Table(name = "products")
  public class Product {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String name;

      private Double price;

      @Column(name = "stock_quantity")
      private Integer stockQuantity;

      // Getters and Setters
  }

### 👤 Customer Entity

The `Customer` entity stores information about the hardware store customers.

- **Fields**:
    - `id`: Primary key for the customer.
    - `name`: Customer's name.
    - `email`: Customer's email address.
    - `phoneNumber`: Contact number.

- **Entity Code Example**:
    ```java
  @Entity
    @Table(name = "customers")
    public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
        private String name;
    
        private String email;
    
        private String phoneNumber;
    
        // Getters and Setters
    }

## ⚙️ Persistence Configuration

The JPA module uses `persistence.xml` for configuring the persistence unit and database connectivity.

**Example** of `persistence.xml`:

  ```xml
    <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
                 version="2.2">
        <persistence-unit name="hardware-store-unit">
            <class>com.hardwarestore.jpa.entity.Product</class>
            <class>com.hardwarestore.jpa.entity.Customer</class>
            <class>com.hardwarestore.jpa.entity.Order</class>
    
            <properties>
                <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/hardwarestore"/>
                <property name="jakarta.persistence.jdbc.user" value="root"/>
                <property name="jakarta.persistence.jdbc.password" value="password"/>
    
                <!-- Hibernate Properties -->
                <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
                <property name="hibernate.hbm2ddl.auto" value="update"/>
                <property name="hibernate.show_sql" value="true"/>
            </properties>
        </persistence-unit>
    </persistence>
  ```

* **Database Configuration**: Modify the JDBC properties to match your environment (e.g., URL, username, password).
* **DDL Auto Mode** (`hibernate.hbm2ddl.auto`): You can set this to `create`, `update`, `validate`, or `none` depending
  on the requirement.

## 🗄️ Database Setup

1. **Install MySQL** (or any preferred relational database).
2. **Create Database**:

    ```postgresql 
    CREATE DATABASE hardwarestore;

3. Update the connection properties in `persistence.xml` with your database credentials.