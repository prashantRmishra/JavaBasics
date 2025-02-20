### **Method Reference in Java**
Method reference is a shorthand notation of lambda expressions to refer to a method without executing it. It makes code more readable and concise.

### **Types of Method References**
There are **four** types of method references:

1. **Reference to a Static Method**  
2. **Reference to an Instance Method of a Particular Object**  
3. **Reference to an Instance Method of an Arbitrary Object of a Particular Type**  
4. **Reference to a Constructor**

---

### **1. Reference to a Static Method**
We can refer to a static method using **ClassName::staticMethodName**.

#### **Example**
```java
import java.util.function.Function;

class Utility {
    public static int square(int x) {
        return x * x;
    }
}

public class MethodRefExample {
    public static void main(String[] args) {
        // Using lambda expression
        Function<Integer, Integer> lambdaSquare = x -> Utility.square(x);
        
        // Using method reference
        Function<Integer, Integer> methodRefSquare = Utility::square;

        System.out.println(methodRefSquare.apply(5)); // Output: 25
    }
}
```

---

### **2. Reference to an Instance Method of a Particular Object**
We can refer to an instance method of an **already created object** using **objectName::methodName**.

#### **Example**
```java
import java.util.function.Predicate;

public class MethodRefExample {
    public boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static void main(String[] args) {
        MethodRefExample obj = new MethodRefExample();

        // Using lambda expression
        Predicate<Integer> lambdaIsEven = x -> obj.isEven(x);

        // Using method reference
        Predicate<Integer> methodRefIsEven = obj::isEven;

        System.out.println(methodRefIsEven.test(10)); // Output: true
    }
}
```

---

### **3. Reference to an Instance Method of an Arbitrary Object of a Particular Type**
This is used when we call an instance method on an **unknown object of a specific type**.

#### **Example**
```java
import java.util.Arrays;
import java.util.List;

public class MethodRefExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Using lambda expression
        names.forEach(name -> System.out.println(name));

        // Using method reference
        names.forEach(System.out::println);
    }
}
```
Here, `System.out::println` refers to the `println` method of `PrintStream` class.

---

### **4. Reference to a Constructor**
We can refer to a constructor using **ClassName::new**.

#### **Example**
```java
import java.util.function.Supplier;

class Person {
    Person() {
        System.out.println("Person object created");
    }
}

public class MethodRefExample {
    public static void main(String[] args) {
        // Using lambda expression
        Supplier<Person> lambdaPerson = () -> new Person();

        // Using method reference
        Supplier<Person> methodRefPerson = Person::new;

        methodRefPerson.get(); // Output: Person object created
    }
}
```

---

### **Summary**
| Type of Method Reference | Syntax | Example |
|-------------------------|--------|---------|
| Reference to a Static Method | `ClassName::staticMethod` | `Math::sqrt` |
| Reference to an Instance Method of a Particular Object | `objectName::instanceMethod` | `obj::toString` |
| Reference to an Instance Method of an Arbitrary Object of a Particular Type | `ClassName::instanceMethod` | `String::length` |
| Reference to a Constructor | `ClassName::new` | `Person::new` |
