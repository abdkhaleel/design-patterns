interface Expression {
    int interpret();
}

class NumberExpression implements Expression {
    private final int number;
    public NumberExpression (int number) {
        this.number = number;
    }

    @Override
    public int interpret () {
        return this.number;
    }
}

class AddExpression implements Expression {
    private final Expression left;
    private final Expression right;
    public AddExpression (Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret () {
        return left.interpret() + right.interpret();
    }
}

class SubExpression implements Expression {
    private final Expression left;
    private final Expression right;
    public SubExpression (Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret () {
        return left.interpret() - right.interpret();
    }
}

public class Main {
    public static void main(String[] args) {
        Expression five = new NumberExpression(5);
        Expression ten = new NumberExpression(10);
        Expression three = new NumberExpression(3);

        Expression add = new AddExpression(ten, five);
        Expression sub = new SubExpression(add, three);

        System.out.println(sub.interpret());
    }
}