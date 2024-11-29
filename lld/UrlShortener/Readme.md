### **README.md**
### **Table of Contents**

- [**URL Shortener - Low-Level Design**](#url-shortener---low-level-design)
  - [**Overview**](#overview)
  - [**Features**](#features)
  - [**Architecture**](#architecture)
  - [**Technologies**](#technologies)
  - [**Configurations**](#configurations)
  - [**Example Usage**](#example-usage)
  - [**Future Enhancements**](#future-enhancements)
  - [High Level design of URL shortener like Bit.ly](#high-level-design-of-url-shortener-like-bitly)

---
# **URL Shortener - Low-Level Design**

## **Overview**
This project implements a simple and efficient URL Shortener service. It converts long URLs into short, compact strings and supports retrieval, updating, and deletion of URLs. The design emphasizes modularity, scalability, and ease of extensibility.

---

## **Features**
- Create short URLs with configurable lengths.
- Retrieve long URLs using the short URL.
- Update or delete existing short URLs.
- Automatically handle expired URLs.
- Handle retries for collisions of the short URLs
- Thread-safe data handling using `ConcurrentHashMap`.
- Custom alias support for short URLs.

---

## **Architecture**
The project follows a layered architecture:
1. **Controller**:
   - Exposes APIs for creating, retrieving, updating, and deleting short URLs.

2. **Service**:
   - Implements business logic for URL management.
   - Delegates data storage operations to the `Data` class.

3. **Data**:
   - Acts as the in-memory storage layer for URL mappings.
   - Uses a `ConcurrentHashMap` for thread safety.

4. **Utility**:
   - `ShortUrlCreatorUtility` provides methods to generate short URLs using counter-based and base62 encoding techniques.

---

## **Technologies**
- **Java**: Core programming language.
- **ConcurrentHashMap**: For thread-safe in-memory storage.
- **AtomicLong**: For thread-safe counter management.

---

## **Configurations**
Modify `UrlShortenerConfig` for:
- `Short_Len`: Default length of short URLs.

---

## **Example Usage**
```java
 User user1 = new User("prashant", "prashant's address", "1234566");
Controller controller = new Controller(new UrlCrudService(new ShortUrlCreatorUtility(), new Data()));

Url url = new Url("https://google.com", LocalDate.of(2028, 12, 4), user1);
Url url2 = new Url("https://flipkart/catalog", LocalDate.of(2028, 12, 4), user1);

System.out.println("short:"+ controller.createShortUrl(url, "abcddd"));
System.out.println("long:"+ controller.getLongUrl(url.getShortUrl()));

System.out.println("short:"+ controller.createShortUrl(url2, ""));
System.out.println("long:"+ controller.getLongUrl(url2.getShortUrl()));

System.out.println("short:"+ controller.createShortUrl(url2, ""));
System.out.println("long:"+ controller.getLongUrl(url2.getShortUrl()));
```
**Output**

```
short:abcddd
long:https://google.com
short:fYMauT
long:https://flipkart/catalog
short:gfT0aY
long:https://flipkart/catalog
```
---

## **Future Enhancements**
- Add database integration for persistent storage.
- Handle short URL collisions with a retry mechanism.
- Introduce background jobs for periodic cleanup of expired URLs.
- Implement analytics (e.g., tracking the number of times a URL is accessed).

---

## [High Level design of URL shortener like Bit.ly](https://github.com/prashantRmishra/System-design/blob/main/url-shrtener/readme.md)