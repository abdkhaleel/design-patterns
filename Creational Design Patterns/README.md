# Creational Design Patterns

Creational patterns abstract the instantiation process. They help make a system independent of how its objects are created, composed, and represented.

---

## 1. Factory Method Pattern

### Concept
Defines an interface for creating an object but lets subclasses alter the type of objects that will be created. It provides a "virtual constructor."

### What it Does
*   **Decoupling:** The client code deals with the abstract interface (e.g., `Transport`) rather than concrete classes (e.g., `Truck`).
*   **Extensibility:** Introducing a new type only requires creating a new subclass, without breaking existing code.

### Code Illustration

**Without Factory:** The code is tightly coupled to specific classes.
```java
Transport t = new Truck(); // Hardcoded
```

**With Factory:**
```java
// Interface
interface Transport { void deliver(); }

class Truck implements Transport {
    public void deliver() { System.out.println("Deliver by land"); }
}

class Ship implements Transport {
    public void deliver() { System.out.println("Deliver by sea"); }
}

// The Factory Class
class Logistics {
    public Transport createTransport(String type) {
        if (type.equals("Road")) return new Truck();
        if (type.equals("Sea")) return new Ship();
        return null;
    }
}

// Client usage
Transport t = logistics.createTransport("Road");
t.deliver();
```

---

## 2. Abstract Factory Pattern

### Concept
Produces families of related or dependent objects without specifying their concrete classes. It allows you to produce a suite of products (e.g., Modern Furniture vs. Victorian Furniture) that work together.

### What it Does
*   **Consistency:** Ensures that products created together match (e.g., a Mac button is always created with a Mac scrollbar).
*   **Abstraction:** The client code is written against interfaces and doesn't know which specific family (Windows or Mac) is being used at runtime.

### Code Illustration

```java
// Abstract Factory Interface
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory for Windows
class WinFactory implements GUIFactory {
    public Button createButton() { return new WinButton(); }
    public Checkbox createCheckbox() { return new WinCheckbox(); }
}

// Concrete Factory for Mac
class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

// Client Application
class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        // Creates a matching family of products
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }
}
```

---

## 3. Singleton Pattern

### Concept
Ensures that a class has only one instance and provides a global point of access to that instance.

### What it Does
*   **Resource Control:** Useful for shared resources like Database Connections, Thread Pools, or Loggers.
*   **Global Access:** Provides a strict way to access the instance without passing it around everywhere.

### Code Illustration

**Naive Implementation:**
```java
public class Database {
    // 1. Static variable to hold the single instance
    private static Database instance;

    // 2. Private constructor prevents direct instantiation
    private Database() { ... }

    // 3. Public static method to access the instance
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    public void query(String sql) { ... }
}

// Client code
Database.getInstance().query("SELECT * FROM users");
```

---

## 4. Builder Pattern

### Concept
Constructs complex objects step by step. It allows you to produce different types and representations of an object using the same construction code. It solves the issue of "Telescoping Constructors" (constructors with too many parameters).

### What it Does
*   **Readability:** Replaces confusing constructors `new House(4, 2, true, false, "Concrete")` with readable method chains.
*   **Immutability:** Often used to build immutable objects safely.

### Code Illustration

**Without Builder (Complex Constructor):**
```java
// Hard to know what 'true' and '4' stand for
House h = new House(4, true, false, true, "Wood"); 
```

**With Builder:**
```java
class House {
    // Complex object fields...
}

class HouseBuilder {
    private House house;
    public HouseBuilder() { this.house = new House(); }

    public HouseBuilder setWalls(int walls) {
        house.walls = walls;
        return this; // Return builder for chaining
    }
    
    public HouseBuilder hasPool(boolean hasPool) {
        house.hasPool = hasPool;
        return this;
    }

    public House build() { return house; }
}

// Client usage
House luxuryHouse = new HouseBuilder()
                        .setWalls(4)
                        .hasPool(true)
                        .build();
```

---

## 5. Prototype Pattern

### Concept
Creates new objects by copying an existing object, known as the prototype. This is useful when object creation is more expensive (e.g., database queries) than copying.

### What it Does
*   **Performance:** Avoids costly initialization logic by cloning memory.
*   **Decoupling:** The code does not need to depend on the concrete class of the object being copied; it just calls `.clone()`.

### Code Illustration

```java
// Interface supporting cloning
abstract class Shape implements Cloneable {
    public int x, y;
    
    public Shape clone() {
        return (Shape) super.clone(); // Native Java cloning
    }
}

class Circle extends Shape {
    public int radius;

    public Circle(int radius) {
        // Expensive calculation might happen here
        this.radius = radius;
    }
}

// Client usage
Circle original = new Circle(10);

// Create a copy without running the constructor or expensive logic again
Circle copy = (Circle) original.clone(); 
```