class Singleton {
    public static void main(String[] args) {
        SingletonObject o = SingletonObject.getInstance();
        o.showMessage();
    }
}

class SingletonObject {
    private static SingletonObject instance = new SingletonObject();
    private SingletonObject () {}

    public static SingletonObject getInstance() {
        return instance;
    }
    public void showMessage() {
        System.out.println("Hello world");
    }
}