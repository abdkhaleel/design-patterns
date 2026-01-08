# Design Patterns: Architectural Overview

Design patterns are categorized into three major groups based on the nature of the design problem they solve. Below is a high-level explanation of how each category fundamentally alters code structure and logic flow.

---

## 1. Creational Patterns

### Meaning
Creational patterns focus on **object creation mechanisms**. They abstract the instantiation process, making the system independent of how its objects are created, composed, and represented. Instead of the system directly copying specific classes, it asks for the interface or the family of objects it needs.

### How it Affects Code
*   **Decoupling:** Removes hard-coded class dependencies.
*   **Flexibility:** Allows the system to switch between different implementations of a class at runtime without changing the client code.
*   **Complexity Management:** Hides complex initialization logic (e.g., reading configs, connecting to databases) inside the creation mechanism.

### Code Comparison

**Without Creational Patterns (Rigid)**
The code is tightly coupled to the `PDFReport` class. To change to an `ExcelReport`, you must modify this code block.

```java
public class ReportService {
    public void generate() {
        // Direct instantiation using 'new'
        Report report = new PDFReport(); 
        report.build();
    }
}
```

**With Creational Patterns (Flexible)**
The code relies on an abstraction. The actual object type is determined elsewhere, keeping this logic clean.

```java
public class ReportService {
    public void generate(ReportCreator creator) {
        // Creation logic is delegated; we don't know if it's PDF or Excel
        Report report = creator.makeReport();
        report.build();
    }
}
```

---

## 2. Structural Patterns

### Meaning
Structural patterns explain how to **assemble objects and classes into larger structures** while keeping these structures flexible and efficient. They focus on composition and inheritance to identify a simple way to realize relationships between entities. They act as "glue" between different parts of a system that might otherwise be incompatible.

### How it Affects Code
*   **Interface Unification:** Wraps incompatible interfaces into a unified one.
*   **Simplification:** Reduces the complexity of connecting subsystems.
*   **Extensibility:** Allows adding new functionality to objects dynamically without altering the underlying class structure.

### Code Comparison

**Without Structural Patterns (Incompatible/Messy)**
Client code tries to force two incompatible systems to talk, resulting in messy translation logic scattered throughout the business logic.

```java
public void processData(LegacySystem legacy, ModernSystem modern) {
    // Client has to manually translate disparate formats
    String uglyData = legacy.getXmlData();
    JsonObject cleanData = convertXmlToJson(uglyData); 
    modern.save(cleanData);
}
```

**With Structural Patterns (Unified)**
A "wrapper" or structure acts as a translator. The business logic interacts with a clean interface, unaware of the complex translation happening underneath.

```java
public void processData(DataSystem unifiedSystem) {
    // The structural pattern handles the translation internally
    // The client treats the complex system as a simple standard interface
    unifiedSystem.transferData(); 
}
```

---

## 3. Behavioral Patterns

### Meaning
Behavioral patterns are concerned with **algorithms and the assignment of responsibilities between objects**. They describe not just the patterns of objects or classes but also the patterns of communication between them. These patterns let you define how objects interact and handle control flow.

### How it Affects Code
*   **Dynamic Behavior:** Replaces hard-coded conditional logic (massive `if/else` or `switch` statements) with object polymorphism.
*   **Loose Coupling:** Objects can communicate (e.g., sending notifications) without knowing exactly who is listening.
*   **state Management:** encapsulating state-specific behavior into distinct classes.

### Code Comparison

**Without Behavioral Patterns (Rigid Flow)**
Control flow is hardcoded. Adding a new behavior requires modifying the main class and adding more conditional logic.

```java
public void navigate(String transportType) {
    if (transportType.equals("Road")) {
        calculateRoadRoute();
    } else if (transportType.equals("Sea")) {
        calculateSeaRoute();
    } else if (transportType.equals("Air")) {
        calculateAirRoute();
    }
}
```

**With Behavioral Patterns (Dynamic Flow)**
Behavior is encapsulated in objects. The context simply executes the behavior it was given, eliminating conditionals.

```java
public void navigate(RouteStrategy strategy) {
    // The behavior is determined by the object passed in.
    // No if/else logic is required here.
    strategy.calculateRoute();
}
```