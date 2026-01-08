import java.util.*;
interface Observer {
    void update(int temperature);
}

interface Subject {
    void register(Observer observer);
    void remove(Observer observer);
    void notifyObservers();
}

class WeatherStation implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void setTemperature (int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void register (Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove (Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers () {
        for (Observer observer: observers) {
            observer.update(temperature);
        }
    }
}

class MobileDisplay implements Observer {
    @Override
    public void update (int temperature) {
        System.out.println("Mobile Display: " + temperature);
    }
}

class WebDisplay implements Observer {
    @Override
    public void update (int temperature) {
        System.out.println("Web Display: " + temperature);
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();

        Observer mobileObserver = new MobileDisplay();
        Observer webObserver = new WebDisplay();

        station.register(webObserver);
        station.register(mobileObserver);

        station.setTemperature(20);

        station.remove(webObserver);;

        station.setTemperature(50);

        station.register(webObserver);
        station.notifyObservers();
    }
}
