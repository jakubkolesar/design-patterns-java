package sk.kolesarj.learning.patterns.decorator;

interface Beverage {
    public double cost();
}

class Coffee implements Beverage{
    @Override
    public double cost() {
        return 1;
    }
}

class BeverageDecorator implements Beverage {
    protected Beverage beverage;
    public BeverageDecorator(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return this.beverage.cost();
    }
}

class CaramelAddon extends BeverageDecorator {
    public CaramelAddon(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return super.cost() + 0.7;
    }
}

class CreamAddon extends BeverageDecorator {
    public CreamAddon(Beverage beverage) {
        super(beverage);
    }
    @Override
    public double cost() {
        return super.cost() + 3;
    }
}
public class CoffeShopExample {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        System.out.println(coffee.cost());

        Beverage caramelCoffee = new CaramelAddon(new Coffee());
        System.out.println(caramelCoffee.cost());

        Beverage caramelCreamCoffee = new CreamAddon(new CaramelAddon(new Coffee()));
        System.out.println(caramelCreamCoffee.cost());

    }
}
