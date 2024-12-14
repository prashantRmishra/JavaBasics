# README: Comprehensive Guide to Spring Data JPA, Microservices, and Hibernate

## **Introduction to Spring Data JPA**
For a comprehensive overview of Spring Data JPA, refer to the [Spring Data JPA Documentation](https://github.com/prashantRmishra/JpaHibernateLearning/blob/database-demo-jpa-and-jdbc/README.md).

## **Introduction to Microservices**
Microservices architecture provides a modular approach to building scalable and distributed systems. For details, refer to [Introduction to Microservices](https://github.com/prashantRmishra/SpringBootWithMicroservices/blob/limit-microservice/ReadMe.md).

## **Hibernate In-Depth**
Hibernate is a powerful ORM framework for Java. For detailed insights into its core functionality, visit [Hibernate Documentation](https://github.com/prashantRmishra/JpaHibernateLearning/tree/jpa-hibernate-in-depth?tab=readme-ov-file).

---

### **Important Hibernate Methods/Classes for Interviews**

#### **Methods of `EntityManager`**
1. **`flush()`**: Synchronizes the persistence context to the database.
2. **`detach(Object)`**: Removes the given object from the persistence context.
3. **`clear()`**: Clears the persistence context, detaching all managed entities.
4. **`refresh(Object)`**: Reloads the state of the given entity from the database.

#### **Annotations**
- `@Entity`
- `@Id`
- `@GeneratedValue`
- `@Column(nullable = false)`
- `@CreationTimestamp`
- `@UpdateTimestamp`
- `@OneToOne`
- `@OneToMany`
- `@ManyToMany`
- `@ManyToOne(fetch = FetchType.LAZY)`
- `@NamedQuery(name = "find_all_courses", query = "select c from Course c")`
- `@Transactional`
- `@PersistenceContext`

#### **Fetching Strategy by Default**
- Relationships ending with `__ToOne` (`ManyToOne`, `OneToOne`): **Eager Fetching**.
- Relationships ending with `__ToMany` (`ManyToMany`, `OneToMany`): **Lazy Fetching**.

---

### [**JPA Inheritance Hierarchy and Mapping**](https://github.com/prashantRmishra/JpaHibernateLearning/blob/jpa-hibernate-in-depth/JpaIngeritanceHirarchyAndMapping.md)
1. **`@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`**
2. **`@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)`**
3. **`@Inheritance(strategy = InheritanceType.JOINED)`**

---

### **Hibernate Queries**

#### [**JPQL (Java Persistence Query Language)**](https://github.com/prashantRmishra/JpaHibernateLearning/blob/jpa-hibernate-in-depth/src/test/java/com/prashant/jpa/hibernate/JpaHIbernate/repository/JpqlTest.java)
JPQL allows object-oriented querying:
- **Query Example**: `SELECT c FROM Course c WHERE c.name LIKE '%Java%'`

#### **Query Formats**
1. **Named Query**:
   ```java
   @NamedQuery(name = "find_all_courses", query = "SELECT c FROM Course c")
   ```
2. **Native Query**:
   ```java
   @Query(value = "SELECT * FROM Course", nativeQuery = true)
   ```

#### **JPA Queries**
JPA provides an interface for Hibernate queries. Refer to [this](https://github.com/prashantRmishra/JpaHibernateLearning/blob/jpa-hibernate-in-depth/SpringDataJpa.md) for examples.

---

### **Caching in Hibernate**
For details on first-level and second-level caching mechanisms, refer to the [Hibernate Caching Documentation](https://github.com/prashantRmishra/JpaHibernateLearning/blob/jpa-hibernate-in-depth/IntoductionToCaching.md).

---

## **Important Spring Boot Snippets**

### **Global Exception Handlers**
Refer to [Spring Boot Exception Handling](https://github.com/prashantRmishra/Spring-App-With-AOP-and-Interceptors/tree/main/src/main/java/com/example/demo/GlobalException).

### **Interceptors**
Use interceptors to filter or preprocess requests:
- Refer to [Spring Interceptors Guide](https://github.com/prashantRmishra/Spring-App-With-AOP-and-Interceptors/tree/main/src/main/java/com/example/demo/interceptor).

### **Aspect-Oriented Programming (AOP)**
Create an aspect class to define cross-cutting concerns:
- Use annotations like `@Before`, `@After`, and `@Around`.
- Refer to [Spring AOP Documentation](https://github.com/prashantRmishra/Spring-App-With-AOP-and-Interceptors/blob/main/src/main/java/com/example/demo/aspect/EmployeeAspect.java).

### **Spring Boot Controller**
Refer to [Spring Controllers](https://github.com/prashantRmishra/Spring-App-With-AOP-and-Interceptors/blob/main/src/main/java/com/example/demo/controller/Controller.java).

### **RowMapper**
A `RowMapper` populates objects from database rows:
- Refer to [RowMapper Documentation](https://github.com/prashantRmishra/Spring-App-With-AOP-and-Interceptors/blob/main/src/main/java/com/example/demo/dao/RowMapper/Mapper.java).

---

### **On-the-Fly Revision Questions**
1. **Difference Between `@Controller` and `@RestController`**:
   - `@Controller`: Used for rendering views.
   - `@RestController`: Combines `@Controller` and `@ResponseBody` for REST APIs.

