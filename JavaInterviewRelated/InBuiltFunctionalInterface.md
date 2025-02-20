## **Functional Interfaces in Java**
A **functional interface** in Java is an interface that contains exactly **one abstract method**. It can have multiple **default** and **static** methods, but only **one abstract method**.  

### **Key Features of Functional Interfaces:**
1. **Exactly one abstract method.**  
2. **Can have multiple default and static methods.**  
3. **Used with Lambda expressions and Method references.**  
4. **`@FunctionalInterface` annotation (optional but recommended).**  

---

## **Common Built-in Functional Interfaces in Java**
### **1. `Function<T, R>`** – Takes an input `T`, returns an output `R`
```java
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        Function<String, Integer> lengthFunction = str -> str.length();

        System.out.println(lengthFunction.apply("Hello")); // Output: 5
    }
}
```

---

### **2. `Predicate<T>`** – Takes an input `T`, returns a `boolean`
```java
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<Integer> isEven = num -> num % 2 == 0;

        System.out.println(isEven.test(10)); // Output: true
    }
}
```

---

### **3. `Consumer<T>`** – Takes an input `T`, returns nothing
```java
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        Consumer<String> printUpper = str -> System.out.println(str.toUpperCase());

        printUpper.accept("hello"); // Output: HELLO
    }
}
```

---

### **4. `Supplier<T>`** – Takes no input, returns `T`
```java
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<Double> randomSupplier = () -> Math.random();

        System.out.println(randomSupplier.get()); // Output: Random number
    }
}
```

---

## **Custom Functional Interface**
We can also create our own functional interfaces.

### **Example: Custom Functional Interface**
```java
@FunctionalInterface
interface MyFunctionalInterface {
    int add(int a, int b);
}

public class CustomFunctionalInterfaceExample {
    public static void main(String[] args) {
        MyFunctionalInterface sum = (a, b) -> a + b;

        System.out.println(sum.add(5, 10)); // Output: 15
    }
}
```

---

## **Summary of Functional Interfaces**
| Interface | Method Signature | Description | Example |
|-----------|-----------------|-------------|---------|
| **Function<T, R>** | `R apply(T t)` | Takes `T`, returns `R` | `Function<String, Integer> length = str -> str.length();` |
| **Predicate<T>** | `boolean test(T t)` | Takes `T`, returns `boolean` | `Predicate<Integer> isEven = num -> num % 2 == 0;` |
| **Consumer<T>** | `void accept(T t)` | Takes `T`, returns nothing | `Consumer<String> print = str -> System.out.println(str);` |
| **Supplier<T>** | `T get()` | Takes nothing, returns `T` | `Supplier<Double> random = () -> Math.random();` |