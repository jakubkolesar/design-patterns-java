package sk.kolesarj.learning.patterns.decorator;

import java.util.function.Supplier;

class StaticColoredShape<T extends Shape> implements Shape {
    private Shape shape;
    private String color;

    public StaticColoredShape(Supplier<? extends T> ctor, String color){
        shape = ctor.get();
        this.color = color;
    }
    @Override
    public String info() {
        return shape.info() + " has the color "+ color;
    }
}

class StaticTransparentShape<T extends Shape> implements Shape {
    private Shape shape;
    private int transparency;

    public StaticTransparentShape(Supplier<? extends Shape> ctor, int transparency){
        this.shape = ctor.get();
        this.transparency = transparency;
    }
    @Override
    public String info() {
        return this.shape.info() + " has " + transparency+ "% of transparency";
    }
}
public class StaticDecorator {
    public static void main(String[] args) {
        StaticColoredShape<Square> blueSquare = new StaticColoredShape<>(()->new Square(20), "blue");
        System.out.println(blueSquare.info());

        StaticTransparentShape<StaticColoredShape<Circle>> myCircle =
                new StaticTransparentShape<>(() ->
                        new StaticColoredShape<>(()->
                                new Circle(20), "yellow"),10);

        System.out.println(myCircle.info());

    }
}
