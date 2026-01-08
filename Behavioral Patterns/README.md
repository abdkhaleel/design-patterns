# Behavioral Design Patterns

Behavioral patterns focus on algorithms and the assignment of responsibilities between objects. They describe how objects communicate and coordinate to accomplish tasks.

---

## 1. Command Pattern

### Concept
Encapsulates a request as a standalone object containing all information about the request. This transformation lets you parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.

### What it Does
*   **Decoupling:** Separation between the object that invokes the operation (Sender) and the object that knows how to perform it (Receiver).
*   **History:** Enables "Undo/Redo" functionality by storing the command objects in a stack.

### Code Illustration

**Without Command:** The button directly calls the logic. To change what the button does, you must change the button class.
```java
class Button {
    public void click() {
        // Tightly coupled to printing
        System.out.println("Printing document...");
    }
}
```

**With Command:** The button executes a generic command. It doesn't care what the command does.
```java
// The command encapsulates the logic
class PrintCommand implements Command {
    public void execute() {
        printer.print();
    }
}

class Button {
    private Command command;
    public Button(Command c) { this.command = c; }
    
    public void click() {
        // Button is decoupled from the actual action
        command.execute(); 
    }
}
```

---

## 2. Interpreter Pattern

### Concept
Provides a way to evaluate language grammar or expressions. It involves implementing an expression interface which interprets a particular context. This is rarely used for complex languages but useful for simple rules engines, math expressions, or SQL-like parsing.

### What it Does
*   **Grammar Definition:** Represents the grammar of a language as class hierarchies.
*   **Evaluation:** Interprets sentences in the language one by one.

### Code Illustration
**Scenario:** Interpreting a math context "10 + 5".

```java
// Terminal Expression
class NumberExpression implements Expression {
    int number;
    public int interpret() { return number; }
}

// Non-Terminal Expression
class PlusExpression implements Expression {
    Expression left;
    Expression right;
    
    public int interpret() {
        // The logic for the grammar exists here
        return left.interpret() + right.interpret(); 
    }
}
```

---

## 3. Iterator Pattern

### Concept
Provides a way to access the elements of an aggregate object (like a list, tree, or graph) sequentially without exposing its underlying representation.

### What it Does
*   **Abstraction:** Hides whether the collection is an Array, a Linked List, or a Tree.
*   **Uniformity:** Provides a standard interface (`next()`, `hasNext()`) for traversing any collection type.

### Code Illustration

**Without Iterator:** You must know exactly how the data is stored (index vs. node).
```java
// Dependent on 'List' implementation using index i
for (int i = 0; i < list.size(); i++) {
    process(list.get(i));
}
```

**With Iterator:** The loop structure remains the same regardless of the data structure.
```java
Iterator it = collection.createIterator();
// Works for Arrays, Lists, Trees, etc.
while (it.hasNext()) {
    process(it.next());
}
```

---

## 4. Mediator Pattern

### Concept
Defines an object that encapsulates how a set of objects interact. It promotes loose coupling by keeping objects from referring to each other explicitly, allowing you to vary their interaction independently. Think of it as an Air Traffic Controller.

### What it Does
*   **Centralization:** Moves complex many-to-many relationships into a single mediator class.
*   **Decoupling:** Components (Colleagues) only know about the Mediator, not each other.

### Code Illustration

**Without Mediator:** Objects call each other directly (Spaghetti code).
```java
class Checkbox {
    void click() {
        textField.setVisible(true); // Direct dependency
        submitButton.setEnabled(true); // Direct dependency
    }
}
```

**With Mediator:** Objects simply notify the mediator.
```java
class Checkbox {
    void click() {
        mediator.notify(this, "check");
    }
}

class DialogMediator {
    void notify(Component sender, String event) {
        if (event.equals("check")) {
            // Mediator handles the logic for others
            textField.setVisible(true);
            submitButton.setEnabled(true);
        }
    }
}
```

---

## 5. Memento Pattern

### Concept
Captures and externalizes an object's internal state so that the object can be restored to this state later, without violating encapsulation.

### What it Does
*   **Snapshots:** Saves the state of an object at a specific moment.
*   **Rollback:** Allows restoring the object to a previous state (Ctrl+Z functionality).

### Code Illustration

```java
class Editor {
    private String text;

    // Create a snapshot (Memento)
    public Snapshot createSnapshot() {
        return new Snapshot(this.text);
    }

    // Restore from snapshot
    public void restore(Snapshot snapshot) {
        this.text = snapshot.getText();
    }
}

class Snapshot {
    private final String textState;
    // Immutable state storage
    public Snapshot(String text) { this.textState = text; }
}
```

---

## 6. Observer Pattern

### Concept
Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. Also known as Pub/Sub (Publisher/Subscriber).

### What it Does
*   **Event Handling:** Reacting to changes in another object.
*   **Dynamic Relationships:** Subscribers can be added or removed at runtime.

### Code Illustration

**Without Observer:** The client must constantly check (poll) for updates.
```java
while(true) {
    if (youtubeChannel.hasNewVideo()) {
        notifyUser();
    }
}
```

**With Observer:** The subject notifies the subscribers.
```java
class YouTubeChannel {
    List<Subscriber> subs = new ArrayList<>();

    public void uploadVideo(String title) {
        // Automatically notify all listeners
        for (Subscriber sub : subs) {
            sub.update(title);
        }
    }
}
```

---

## 7. State Pattern

### Concept
Allows an object to alter its behavior when its internal state changes. The object will appear to change its class. It replaces massive `switch` or `if` statements related to the object's status.

### What it Does
*   **State Encapsulation:** Each state (e.g., Draft, Published, Moderation) is a separate class.
*   **Behavior Change:** Invoking a method (like `publish()`) performs different actions depending on the current state class.

### Code Illustration

**Without State:**
```java
public void publish() {
    if (state == DRAFT) {
        state = MODERATION;
    } else if (state == PUBLISHED) {
        // Do nothing
    } else if (state == MODERATION) {
        // Throw error
    }
}
```

**With State:**
```java
class DraftState implements State {
    public void publish(Document doc) {
        // Transition state
        doc.changeState(new ModerationState()); 
    }
}

class Document {
    public void publish() {
        // Delegate behavior to the current state object
        currentState.publish(this); 
    }
}
```

---

## 8. Strategy Pattern

### Concept
Defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

### What it Does
*   **Runtime Switching:** Swap logic (e.g., Sorting method, Payment type, Navigation route) at runtime.
*   **Eliminates Conditionals:** Removes complex conditional logic selecting specific algorithms.

### Code Illustration

```java
// Interface for the strategy
interface RouteStrategy {
    void buildRoute(String a, String b);
}

class Navigator {
    private RouteStrategy strategy;

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void buildRoute(String a, String b) {
        // Context acts the same, but behavior changes based on strategy
        strategy.buildRoute(a, b);
    }
}

// Usage
nav.setStrategy(new RoadStrategy());
nav.buildRoute("Home", "Work"); // Calculates road path

nav.setStrategy(new PublicTransportStrategy());
nav.buildRoute("Home", "Work"); // Calculates bus/train path
```

---

## 9. Template Method Pattern

### Concept
Defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.

### What it Does
*   **Code Reuse:** Common parts of the algorithm are defined once in the parent.
*   **Enforced Structure:** The parent controls the order of operations, children only provide details for specific steps.

### Code Illustration

```java
abstract class DataMiner {
    // This is the Template Method. It is 'final' so steps cannot be reordered.
    public final void mine() {
        openFile();
        extractData(); // Abstract step
        parseData();   // Abstract step
        closeFile();
    }

    // Common step
    void openFile() { System.out.println("Opening..."); }
    
    // Steps to be implemented by subclasses
    abstract void extractData();
    abstract void parseData();
}

class PDFMiner extends DataMiner {
    // Only provides logic specific to PDF
    void extractData() { /* ... */ }
    void parseData() { /* ... */ }
}
```

---

## 10. Visitor Pattern

### Concept
Represents an operation to be performed on the elements of an object structure. Visitor lets you define a new operation without changing the classes of the elements on which it operates.

### What it Does
*   **Separation of Concerns:** Separates algorithms from the objects on which they operate.
*   **Extensibility:** You can add new operations (Export to XML, Export to JSON) without modifying the actual Entity classes.

### Code Illustration

**Without Visitor:** You have to modify the Shape classes every time you need a new feature.
```java
class Circle {
    void draw() { ... }
    void exportXML() { ... } // Pollution of logic
    void exportJSON() { ... } // Pollution of logic
}
```

**With Visitor:** The logic resides in the Visitor, not the Shape.
```java
interface Visitor {
    void visitDot(Dot dot);
    void visitCircle(Circle circle);
}

class XMLExportVisitor implements Visitor {
    public void visitCircle(Circle circle) {
        System.out.println("<circle>...</circle>");
    }
}

// The Shape only needs to accept a visitor
class Circle implements Shape {
    public void accept(Visitor v) {
        v.visitCircle(this);
    }
}
```