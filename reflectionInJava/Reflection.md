### 🔥 What is Reflection in Java? (Interview Explanation)
✅ Best Answer for Interviews 🚀

---

### What is Reflection in Java?
**Reflection** in Java is a feature that allows your program to:
- **Inspect** classes, methods, fields, and constructors at runtime
- **Modify** private fields or methods
- **Invoke** methods dynamically without knowing their names at compile-time
- **Create objects dynamically**

---

### Simple Definition 📌
👉 Reflection is a **runtime mechanism** to inspect and manipulate the behavior of classes, methods, and fields without knowing them at compile-time.

---

### Why Do We Need Reflection?
Reflection is widely used in:
| Use Case           | Example                        |
|----------------|-------------------------------|
| Frameworks    | Spring, Hibernate (Dependency Injection) |
| Serialization | Converting Objects to JSON/XML |
| Testing       | JUnit Testing (Mocking Private Methods) |
| IDE Tools     | IntelliJ, Eclipse (Auto-Suggestions) |
| ORM           | Hibernate (Entity Mapping) |

---

### How Reflection Works in Java?
Java provides **Reflection API** in the package:
```java
import java.lang.reflect.*;
```

---

### How to Get Class Information?
There are **3 Ways** to get Class object at runtime:

| Method                 | Example                  |
|-----------------------|--------------------------|
| `Class.forName()`      | `Class<?> cls = Class.forName("java.lang.String");` |
| `getClass()`           | `String s = "Prashant"; Class<?> cls = s.getClass();` |
| `.class`               | `Class<?> cls = String.class;` |

---

### Example Code 🔥
Let's inspect the methods and fields of a class at runtime:
```java
import java.lang.reflect.*;

class Person {
    private String name = "Prashant";
    
    public void display() {
        System.out.println("Hello " + name);
    }
}

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        Person p = new Person();

        // Get Class Object
        Class<?> cls = p.getClass();

        // Get All Methods
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());
        }

        // Access Private Field
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);  // Allow private access
        System.out.println("Private Field: " + field.get(p));

        // Invoke Method Dynamically
        Method method = cls.getDeclaredMethod("display");
        method.invoke(p);
    }
}
```
---

### Output:
```
Method: display
Private Field: Prashant
Hello Prashant
```

---

### Important Reflection Methods 🔑
| Method               | Purpose                 |
|---------------------|------------------------|
| `getDeclaredFields()` | Get all fields (public + private) |
| `getDeclaredMethods()` | Get all methods |
| `getConstructors()`    | Get all constructors |
| `setAccessible(true)`   | Access private methods or fields |
| `invoke()`            | Invoke methods dynamically |
| `newInstance()`       | Create Object Dynamically |

---

### 🔥 How Spring Uses Reflection?
Spring uses Reflection for:
- Dependency Injection  
- Bean Creation  
- Proxy Design Pattern  
- AOP (Aspect-Oriented Programming)

---

### Pros ✅
- Dynamic Object Creation
- Access Private Members
- Useful in Frameworks like Spring, Hibernate

---

### Cons ❌
| Disadvantage | Reason               |
|-------------|---------------------|
| Performance | Slower than normal code |
| Security    | Can break Encapsulation |
| Compile-Time Safety | No Type Checking |

---

### When to Use Reflection?
| Scenario    | Use or Avoid |
|------------|-------------|
| Dependency Injection | ✅ Use |
| Unit Testing Private Methods | ✅ Use |
| Regular Business Logic | ❌ Avoid |
| Performance-Critical Code | ❌ Avoid |

---

### How to Answer in Interviews 🔥
👉 **"Reflection in Java is a runtime API that allows inspecting and modifying class behavior dynamically. It is commonly used in frameworks like Spring and Hibernate for Dependency Injection and testing purposes. However, it should be used carefully due to performance overhead and security risks."**

---

### Bonus Tip 💪
You can also mention:
- How Reflection **breaks Singleton Pattern** 🔥
- How **Spring uses Reflection** internally
- What is **Proxy Pattern** in Reflection?

---