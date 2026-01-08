
import java.util.*;

interface ChatMediator {
    void sendMessage(String message, User user);
}

class ChatRoom implements ChatMediator {
    private final List<User> users = new ArrayList<>();

    public void addUser (User user) {
        users.add(user);
    }

    @Override
    public void sendMessage (String message, User sender) {
        for(User user: users) {
            if(user != sender) {
                user.recieve(message);
            }
        }
    }
}

abstract class User {
    protected final ChatMediator mediator;
    protected final String name;

    protected User (ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void recieve(String message);
}

class ChatUser extends User {
    public ChatUser (ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send (String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void recieve (String message) {
        System.out.println(name + " recieves: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatRoom();

        User alice = new ChatUser(mediator, "Alice");
        User bob = new ChatUser(mediator, "Bob");
        User charlie = new ChatUser(mediator, "Charlie");

        ((ChatRoom) mediator).addUser(alice);
        ((ChatRoom) mediator).addUser(bob);
        ((ChatRoom) mediator).addUser(charlie);

        alice.send("Hi all!!");
    }
}