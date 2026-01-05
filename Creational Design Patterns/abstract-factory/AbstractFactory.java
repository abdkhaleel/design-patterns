public class AbstractFactory {
    public static void main(String[] args){
        AbstractShapeFactory f = FactoryProducer.getFactory(false);
        Shape s = f.getShape("Rectangle");
        s.draw();
    }
}
interface Shape {
    void draw();
}
class Square implements Shape {
    @Override
    public void draw(){
        System.out.println("Draw method from Square class");
    }
}

class RoundedSquare implements Shape {
    @Override
    public void draw(){
        System.out.println("Draw method from RoundedSquare class");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw(){
        System.out.println("Draw method from Rectangle class");
    }
}

class RoundedRectangle implements Shape {
    @Override
    public void draw(){
        System.out.println("Draw method from RoundedRectangle class");
    }
}

interface AbstractShapeFactory {
    Shape getShape(String s);
}

class ShapeFactory implements AbstractShapeFactory {
    @Override
    public Shape getShape(String s) {
        if (s.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }
        return new Square();
    }
}

class RoundedShapeFactory implements AbstractShapeFactory {
    @Override
    public Shape getShape(String s) {
        if (s.equalsIgnoreCase("RECTANGLE")) {
            return new RoundedRectangle();
        }
        return new RoundedSquare();
    }
}

class FactoryProducer {
    public static AbstractShapeFactory getFactory(boolean rounded) {
        if (rounded) {
            return new RoundedShapeFactory();
        }
        return new ShapeFactory();
    }
}