interface Visitor {
    int visit(Book book);
    int visit(Electronics electronics);
}

interface Item {
    int accept(Visitor visitor);
}

class Book implements Item {
    int price = 500;
    @Override
    public int accept (Visitor visitor) {
        return visitor.visit(this);
    }
}

class Electronics implements Item {
    int price = 2000;
    @Override
    public int accept (Visitor visitor) {
        return visitor.visit(this);
    }
}

class TaxVisitor implements Visitor {
    @Override
    public int visit (Book book) {
        return book.price * 5 / 100;
    }

    @Override
    public int visit (Electronics electronics) {
        return electronics.price * 18 / 100;
    }
}

class DiscountVisitor implements Visitor {
    @Override
    public int visit (Book book) {
        return 50;
    }

    @Override
    public int visit (Electronics electronics) {
        return 200;
    }
}

public class Main {
    public static void main(String[] args) {
        Item[] items = {new Book(), new Electronics()};

        Visitor taxVisitor = new TaxVisitor();
        Visitor discountVisitor = new DiscountVisitor();

        for (Item item: items) {
            System.out.println("Tax: " + item.accept(taxVisitor));
            System.out.println("Discount: " + item.accept(discountVisitor));
        }
    }
}