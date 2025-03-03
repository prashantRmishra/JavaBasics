## Spring
### Scope of Bean

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
