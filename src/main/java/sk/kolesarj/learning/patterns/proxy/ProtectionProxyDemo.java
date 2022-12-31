package sk.kolesarj.learning.patterns.proxy;
interface Drivable {
    void drive();
}

class Car implements Drivable {
    protected Driver driver;

    public Car(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("Car being driven");
    }
}

class Driver {
    public int age;

    public Driver(int age) {
        this.age = age;
    }
}

class CarProxy extends Car {
    public CarProxy(Driver driver) {
        super(driver);
    }

    @Override
    public void drive() {
        if(driver.age >= 16){
            super.drive();
        }
        else {
            System.out.println("DRIVER TOO YOUNG!");
        }
    }
}
public class ProtectionProxyDemo {
    public static void main(String[] args) {
        /*
        * namiesto manualneho inicializovania vieme spravit dependency
        * injection a keby niekto chcel inicializovat Car, poskytneme
        * mu CarProxy*/

       Car car = new CarProxy(new Driver(20));
       car.drive();
    }
}
