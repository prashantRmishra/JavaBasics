### Object Pool Design Pattern Example for DB Connections

This project demonstrates the implementation of the **Object Pool Design Pattern** to manage database connections in a pool. The goal is to efficiently reuse a limited number of database connections, improving performance and reducing the overhead of frequently creating and closing connections.

---

### **Overview**

In a typical database application, creating and closing a database connection for each request is inefficient and slow. The **Object Pool Pattern** allows you to manage a set of reusable objects (in this case, database connections) that can be shared across multiple requests.

This implementation includes:
1. **DBConnection**: A class that establishes a connection to a database.
2. **ObjectPoolManager**: Manages the pool of connections.
3. **Client**: A sample usage of how the object pool works.

---

### **Classes**

#### **DBConnection**

- **Purpose**: This class handles the creation of a database connection using `DriverManager`.
- **Key Methods**:
  - `getConnection()`: Creates and returns a new database connection if one doesn't already exist.

```java
public class DBConnection {
    private Connection connection;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection("url", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
```

---

#### **ObjectPoolManager**

- **Purpose**: Manages the pool of reusable DB connections. It ensures that no more than a maximum number of connections are created.
- **Key Features**:
  - **Singleton**: The `ObjectPoolManager` is implemented as a Singleton to ensure only one instance exists.
  - **Thread-safe**: The pool is thread-safe using synchronized methods to manage concurrent access.
  - **Pool Size**: Initially creates 3 connections, with a maximum limit of 6 connections.
  
- **Key Methods**:
  - `getDbConnection()`: Returns an available connection from the pool. If all connections are in use, and the maximum pool size has been reached, it will not create more connections.
  - `releaseDBConnection()`: Releases a connection back to the pool once it is no longer needed.

```java
public class ObjectPoolManager {
    public static volatile ObjectPoolManager instance = null;
    private List<DBConnection> free = null;
    private List<DBConnection> inUse = null;

    private static int INITIAL_POOL_SIZE = 3;
    private static int MAX_POOL_SIZE = 6;

    private ObjectPoolManager() {
        free = new ArrayList<>();
        inUse = new ArrayList<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            free.add(new DBConnection());
        }
    }

    public static ObjectPoolManager getInstance() {
        if (instance == null) {
            synchronized (ObjectPoolManager.class) {
                if (instance == null) {
                    instance = new ObjectPoolManager();
                }
            }
        }
        return instance;
    }

    public synchronized DBConnection getDbConnection() {
        DBConnection dbConnection = null;

        if (free.isEmpty() && inUse.size() == MAX_POOL_SIZE) {
            System.out.println("Max pool size reached");
            return dbConnection;
        } else if (free.isEmpty() && inUse.size() < MAX_POOL_SIZE) {
            free.add(new DBConnection());
        }

        dbConnection = free.remove(free.size() - 1);
        inUse.add(dbConnection);

        return dbConnection;
    }

    public synchronized void releaseDBConnection(DBConnection dbConnection) {
        if (dbConnection != null) {
            inUse.remove(dbConnection);
            free.add(dbConnection);
        }
    }
}
```

---

#### **Client**

- **Purpose**: Demonstrates how to use the `ObjectPoolManager` to get and release database connections.
- **Flow**:
  1. The `ObjectPoolManager` instance is accessed.
  2. Database connections are retrieved from the pool.
  3. If the pool is exhausted, no more connections are created (max pool size is 6).
  4. Connections are released back into the pool after use.

```java
public class Client {
    public static void main(String[] args) {
        ObjectPoolManager manager = ObjectPoolManager.getInstance();

        DBConnection dbc1 = manager.getDbConnection();
        DBConnection dbc2 = manager.getDbConnection();
        DBConnection dbc3 = manager.getDbConnection();

        DBConnection dbc4 = manager.getDbConnection();
        DBConnection dbc5 = manager.getDbConnection();
        DBConnection dbc6 = manager.getDbConnection();

        DBConnection dbc7 = manager.getDbConnection();

        System.out.println(dbc7); // This should be null as only 6 can be created at max
    }
}
```

---

### **Key Concepts**

- **Singleton Pattern**: Ensures that there is only one instance of the `ObjectPoolManager` class.
- **Thread Safety**: Ensures that multiple threads can safely access and modify the pool.
- **Object Pool**: Efficiently manages a fixed number of database connections, reusing them as needed, to minimize the overhead of repeatedly opening and closing database connections.

---

### **Usage**

1. **Getting a DB Connection**:  
   Use the `getDbConnection()` method to fetch a connection from the pool.
   
2. **Releasing a DB Connection**:  
   After using the connection, call `releaseDBConnection()` to return it to the pool.

---

### **Limitations**

- The current implementation allows a maximum of 6 database connections. If all are in use and you attempt to fetch more, the system will not create new connections.
- This example assumes that each `DBConnection` can connect to the same database. For a more complex scenario, connections might need to be configured differently.

---

### **Future Enhancements**

- **Dynamic Pool Sizing**: Dynamically adjust the pool size based on demand.
- **Connection Validation**: Validate the connection before using it to ensure it is still valid.
- **Connection Expiry**: Implement a timeout for connections that have been idle for too long.

---

### **Conclusion**

This example demonstrates how to manage a pool of database connections using the **Object Pool Design Pattern** in Java, improving resource usage efficiency by reusing connections and preventing resource exhaustion.

[Protection Singleton from reflection exploitation]()