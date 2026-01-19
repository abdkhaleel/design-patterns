# Structural Design Patterns

Structural patterns explain how to assemble objects and classes into larger structures while keeping these structures flexible and efficient. They act as a blueprint for defining relationships between entities.

---

## 1. Adapter Pattern

### Concept
Acts as a bridge between two incompatible interfaces. It allows objects with incompatible interfaces to collaborate. Think of it like a power plug adapter traveling between countries.

### What it Does
*   **Translation:** Converts the interface of a class into another interface the client expects.
*   **Reusability:** Allows integrating legacy or third-party code into new systems without modifying the original code.

### Code Illustration

**Scenario:** An app expects XML data, but a 3rd-party stock library only gives JSON.

```java
// The standard interface your app uses
interface XmlAnalyzer {
    void analyze(String xmlData);
}

// The incompatible 3rd party library
class JsonStockLibrary {
    void analyzeJson(String jsonData) { ... }
}

// The Adapter makes them compatible
class StockAdapter implements XmlAnalyzer {
    private JsonStockLibrary lib;

    public StockAdapter(JsonStockLibrary lib) {
        this.lib = lib;
    }

    public void analyze(String xmlData) {
        // 1. Convert XML to JSON
        String json = convertXmlToJson(xmlData);
        // 2. Call the library's method
        lib.analyzeJson(json);
    }
}
```

---

## 2. Bridge Pattern

### Concept
Splits a large class or a set of closely related classes into two separate hierarchies—abstraction and implementation—which can be developed independently. It prefers composition over inheritance.

### What it Does
*   **Decoupling:** Separates *what* an object is (Abstraction) from *how* it works (Implementation).
*   **Prevention of Class Explosion:** Avoids creating `RedCircle`, `BlueCircle`, `RedSquare`, `BlueSquare`. Instead, you have `Shape` and `Color`.

### Code Illustration

**Without Bridge:**
You need a class for every combination.

**With Bridge:**
```java
// Implementation Hierarchy
interface Color {
    void apply();
}
class Red implements Color { 
    public void apply() { System.out.println("Applying Red"); } 
}

// Abstraction Hierarchy
abstract class Shape {
    protected Color color; // The "Bridge"
    public Shape(Color c) { this.color = c; }
    abstract void draw();
}

class Circle extends Shape {
    public Circle(Color c) { super(c); }
    public void draw() {
        System.out.print("Drawing Circle in ");
        color.apply(); // Delegates implementation
    }
}
```

---

## 3. Filter Pattern (Criteria Pattern)

### Concept
Enables developers to filter a set of objects using different criteria and chaining them in a decoupled way through logical operations (AND, OR).

### What it Does
*   **Pluggable Logic:** complex filtering logic is broken down into small, separate classes.
*   **Combinability:** Filters can be chained together dynamically.

### Code Illustration

```java
interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}

class MaleCriteria implements Criteria {
    public List<Person> meetCriteria(List<Person> persons) {
        // Return only males
    }
}

class AndCriteria implements Criteria {
    private Criteria first;
    private Criteria second;

    public List<Person> meetCriteria(List<Person> persons) {
        // Filter by first, then filter that result by second
        List<Person> firstRound = first.meetCriteria(persons);
        return second.meetCriteria(firstRound);
    }
}
```

---

## 4. Composite Pattern

### Concept
Lets you compose objects into tree structures and then work with these structures as if they were individual objects. It creates a "Part-Whole" hierarchy.

### What it Does
*   **Uniformity:** Client code treats a simple leaf object and a complex container (composite) exactly the same way.
*   **Recursion:** Simplifies code that calculates totals or renders trees (like file systems or UI menus).

### Code Illustration

```java
interface Component {
    void showPrice();
}

class Product implements Component {
    int price;
    public void showPrice() { System.out.println(price); }
}

class Box implements Component {
    List<Component> contents = new ArrayList<>();

    public void add(Component c) { contents.add(c); }

    public void showPrice() {
        // Delegates recursively to children
        for (Component c : contents) {
            c.showPrice();
        }
    }
}
```

---

## 5. Decorator Pattern

### Concept
Allows you to attach new behaviors to objects by placing these objects inside special wrapper objects that contain the behaviors. It is an alternative to subclassing.

### What it Does
*   **Dynamic Extension:** Add responsibilities to an object at runtime.
*   **Layering:** Combine multiple behaviors by wrapping wrappers (e.g., A Notify stack that sends Email, then SMS, then Slack).

### Code Illustration

```java
interface Notifier {
    void send(String msg);
}

class BasicNotifier implements Notifier {
    public void send(String msg) { System.out.println("Email: " + msg); }
}

class SMSDecorator implements Notifier {
    private Notifier wrappee;
    
    public SMSDecorator(Notifier n) { this.wrappee = n; }
    
    public void send(String msg) {
        wrappee.send(msg); // Original behavior
        System.out.println("SMS: " + msg); // Added behavior
    }
}

// Usage
Notifier stack = new SMSDecorator(new BasicNotifier());
stack.send("Alert!"); // Sends both Email and SMS
```

---

## 6. Facade Pattern

### Concept
Provides a simplified interface to a library, a framework, or any other complex set of classes. It hides the complexity of the subsystem behind a simple "front door."

### What it Does
*   **Simplicity:** Reduces the learning curve for using a complex system.
*   **Decoupling:** Detaches client code from the inner workings of a library.

### Code Illustration

**Without Facade:** Client must know about codecs, bitrates, and buffers.
```java
VideoFile file = new VideoFile("video.ogg");
Codec source = CodecFactory.extract(file);
Buffer buffer = BitrateReader.read(file, source);
// ... complex conversion logic
```

**With Facade:** Client makes one simple call.
```java
class VideoConverterFacade {
    public File convert(String filename, String format) {
        // All the complex "Without Facade" logic happens inside here
        // The client doesn't see it
    }
}

// Client code
new VideoConverterFacade().convert("video.ogg", "mp4");
```

---

## 7. Flyweight Pattern

### Concept
Lets you fit more objects into the available amount of RAM by sharing common parts of state between multiple objects instead of keeping all of the data in each object.

### What it Does
*   **Memory Optimization:** Splits state into "Intrinsic" (shared/constant, e.g., Texture, Color) and "Extrinsic" (unique/varying, e.g., X/Y coordinates).
*   **Scalability:** Essential for systems with millions of objects (like particles in a game).

### Code Illustration

```java
// The heavy object stored once (Intrinsic State)
class TreeType {
    private String name;
    private String color;
    private String otherHeavyTextureDat;
    
    public void draw(int x, int y) { ... }
}

// The lightweight object (Extrinsic State)
class Tree {
    private int x, y;
    private TreeType type; // Reference to the shared object

    public void draw() {
        // Uses shared type to draw, passes unique coordinates
        type.draw(x, y);
    }
}
```

---

## 8. Proxy Pattern

### Concept
Provides a placeholder or substitute for another object to control access to it. The proxy looks exactly like the real object to the client.

### What it Does
*   **Lazy Loading:** Delays the creation of heavy objects until they are actually needed.
*   **Access Control:** Checks permissions before executing a method.
*   **Caching:** Returns cached results instead of re-executing heavy operations.

### Code Illustration

```java
interface Image { void display(); }

class RealImage implements Image {
    public RealImage(String file) { loadFromDisk(file); } // Expensive
    public void display() { ... }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String file;

    public void display() {
        if (realImage == null) {
            // Lazy Initialization: Only load when .display() is actually called
            realImage = new RealImage(file);
        }
        realImage.display();
    }
}
```

---

## 9. Chain of Responsibility Pattern
*(Technically a Behavioral Pattern)*

### Concept
Lets you pass requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.

### What it Does
*   **Decoupling:** The sender doesn't know which object will serve the request.
*   **Dynamic processing:** You can add or remove handlers (loggers, auth checks, compressors) dynamically.

### Code Illustration

```java
abstract class Logger {
    protected Logger nextLogger;

    public void setNext(Logger next) { this.nextLogger = next; }

    public void logMessage(int level, String msg) {
        if (this.level <= level) {
            write(msg);
        }
        // Pass to next link in the chain
        if (nextLogger != null) {
            nextLogger.logMessage(level, msg);
        }
    }
    abstract void write(String msg);
}

// Usage
Logger errorLogger = new ErrorLogger(LogLevel.ERROR);
Logger fileLogger = new FileLogger(LogLevel.DEBUG);
errorLogger.setNext(fileLogger); // Build the chain

// If ERROR, both loggers might write. If DEBUG, only fileLogger writes.
errorLogger.logMessage(LogLevel.ERROR, "System Failure"); 
```
