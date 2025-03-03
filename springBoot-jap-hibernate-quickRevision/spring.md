# Spring

[Bean Scope](#bean-scope) <br>
[Qualifier annotation](#qualifier-annotation) <br>


## Bean Scope

Spring provides different bean scopes to control the lifecycle and instance creation of beans.

| Scope       | What It Does                        | When to Use               |
|-------------|------------------------------------|--------------------------|
| `singleton` | Only one instance per Spring container (Default) | Shared services like PaymentService |
| `prototype` | Creates a **new instance every time** the bean is requested | For non-shared beans like PDF Generator or Temporary Objects |
| `request`   | One instance per **HTTP request** | Web applications (for REST APIs) |
| `session`   | One instance per **HTTP session** | Web applications (for user-specific session data) |
| `application` | One instance per **ServletContext** | Web applications (global data shared across sessions) |
| `websocket` | One instance per **WebSocket session** | WebSocket-based applications |

---

### 🎯 Quick Summary

| Annotation                  | Scope Type  | New Instance Every Time? | Use Case                |
|-----------------------------|-------------|--------------------------|-----------------------|
| `@Autowired`               | Singleton   | ❌ No                   | Service Layer Beans    |
| `@Autowired + @Scope("prototype")` | Prototype | ✅ Yes                | Utility Classes       |
| `@RequestScope`             | Request    | ✅ Yes                | REST API Request Data |
| `@SessionScope`             | Session    | ✅ Yes                | User Session Data     |
| `@ApplicationScope`         | Application | ❌ No                | Global Configurations |

---

### 💡 Best Practice
- Use **Singleton** for most services.
- Use **Prototype** only when you need a fresh instance every time.
- For web applications, prefer **RequestScope** or **SessionScope** where appropriate.

## @Qualifier annotation

### 🔥 `@Qualifier` Annotation in Spring
The **`@Qualifier`** annotation in **Spring** is used to **resolve the ambiguity** when there are **multiple beans of the same type** defined in the Spring container.

---

### Why Do We Need `@Qualifier`?
If there are **multiple beans of the same type**, Spring will **not know which bean to inject** automatically. In such cases, we use **`@Qualifier`** to specify the exact bean name that should be injected.

---

### Example Without `@Qualifier` ❌
Let's say we have two payment services:
```java
@Component
public class PayPalService {
    public void pay() {
        System.out.println("Payment made via PayPal");
    }
}

@Component
public class StripeService {
    public void pay() {
        System.out.println("Payment made via Stripe");
    }
}

@Component
public class PaymentGateway {
    @Autowired
    private PayPalService paymentService; // This works fine!
}
```
✅ This will inject **PayPalService** without any issue because there is only one bean of this type.

---

### What if Both Services Implement the Same Interface?
```java
public interface PaymentService {
    void pay();
}

@Component("paypalService")
public class PayPalService implements PaymentService {
    public void pay() {
        System.out.println("Payment made via PayPal");
    }
}

@Component("stripeService")
public class StripeService implements PaymentService {
    public void pay() {
        System.out.println("Payment made via Stripe");
    }
}

@Component
public class PaymentGateway {
    @Autowired
    private PaymentService paymentService;

    public void processPayment() {
        paymentService.pay();
    }
}
```
❌ This will throw an **exception**:
```
No qualifying bean of type 'PaymentService' available: expected single matching bean but found 2
```
---

### How to Fix This? ✅
Use **`@Qualifier`** to tell Spring which bean to inject:
```java
@Component
public class PaymentGateway {

    @Autowired
    @Qualifier("paypalService")  // Bean name is specified here
    private PaymentService paymentService;

    public void processPayment() {
        paymentService.pay();
    }
}
```
---

### ✅ Output:
```
Payment made via PayPal
```
---

### How Does It Work?
1. Spring finds two beans of type `PaymentService`.
2. Since both beans are eligible, Spring checks the **bean name** provided in the **`@Qualifier`** annotation.
3. It injects the bean with the name `"paypalService"`.

---

### Where Can You Use `@Qualifier`?
| Location               | Supported? |
|-----------------------|------------|
| Constructor Injection  | ✅ Yes |
| Field Injection        | ✅ Yes |
| Setter Injection       | ✅ Yes |
| Method Parameter       | ✅ Yes |

---

### 🎯 What Happens if the Bean Name is Wrong?
If you specify a wrong bean name like:
```java
@Qualifier("upiService")
```
Spring will throw:
```
No qualifying bean of type 'PaymentService' available
```
---

### Difference between `@Autowired` and `@Qualifier`
| Annotation   | Purpose                         | When to Use        |
|-------------|--------------------------------|------------------|
| `@Autowired` | Automatic dependency injection | When there is **only one bean** available |
| `@Qualifier` | Specifies the exact bean       | When there are **multiple beans** of the same type |

---

### Conclusion 🚀
Use **`@Qualifier`** when:
- You have **multiple beans of the same type**.
- You need to **select a specific bean** by its **name**.
- You want **more control** over which bean gets injected.

---

