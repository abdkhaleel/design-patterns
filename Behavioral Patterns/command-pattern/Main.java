

interface Command {
    void execute();
    void undo();
}

class Light {
    public void on () {
        System.out.println("Light ON");
    }

    public void off () {
        System.out.println("Light OFF");
    }
}

class Fan {
    public void start () {
        System.out.println("Fan STARTED");
    }

    public void stop () {
        System.out.println("Fan STOPPED");
    }
}

class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand (Light light) {
        this.light = light;
    }

    @Override
    public void execute () {
        light.on();
    }

    @Override
    public void undo () {
        light.off();
    }
}

class FanOnCommand implements Command {
    private final Fan fan;

    public FanOnCommand (Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute () {
        fan.start();
    }

    @Override
    public void undo () {
        fan.stop();
    }
}

class RemoteControl {
    private Command command;
    private Command lastCommand;

    public void setCommand (Command command) {
        this.command = command;
    }

    public void pressButton () {
        command.execute();
        this.lastCommand = command;
    }

    public void pressUndo () {
        if (lastCommand != null) {
            lastCommand.undo();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        RemoteControl control = new RemoteControl();

        Light light = new Light();
        Command lightOn = new LightOnCommand(light);

        control.setCommand(lightOn);
        control.pressButton();

        Fan fan = new Fan();
        Command fanStart = new FanOnCommand(fan);

        control.setCommand(fanStart);
        control.pressButton();
    }
}