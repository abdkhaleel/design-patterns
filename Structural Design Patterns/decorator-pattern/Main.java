interface Coffee {
    String getDescription();
    int getCost();
}
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription () {
        return "Simple Coffee";
    }

    @Override
    public int getCost () {
        return 50;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected final Coffee coffee;
    public CoffeeDecorator (Coffee coffee) {
        this.coffee = coffee;
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator (Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription () {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public int getCost () {
        return coffee.getCost() + 20;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator (Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription () {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public int getCost () {
        return coffee.getCost() + 10;
    }
}

class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator (Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription () {
        return coffee.getDescription() + ", Whip";
    }

    @Override
    public int getCost () {
        return coffee.getCost() + 30;
    }
}

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " = Rs." + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        coffee = new WhipDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " = Rs." + coffee.getCost());
    }
}