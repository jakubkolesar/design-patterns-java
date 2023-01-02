package sk.kolesarj.learning.patterns.observer;

import java.util.ArrayList;
import java.util.List;

class PropertyChangedEventArgs<T> {
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangedEventArgs(T source, String propertyName, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}

interface Observer<T> {
    void handle(PropertyChangedEventArgs<T> arg);
}

class Observable<T> {
    private List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer) {
        this.observers.add(observer);
    }

    protected void propertyChanged(T source, String propertyName, Object newValue) {
        for (Observer<T> ob : this.observers) {
            ob.handle(new PropertyChangedEventArgs<T>(source, propertyName, newValue));
        }
    }
}

class Person extends Observable<Person> {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        propertyChanged(this, "age", age);
    }
}

public class ObserverDemo implements Observer<Person> {
    public ObserverDemo(){
        Person person = new Person();
        person.subscribe(this);
        for(int i = 0; i<10; i++){
            person.setAge(i+10);
        }
    }
    public static void main(String[] args) {
        new ObserverDemo();
    }

    @Override
    public void handle(PropertyChangedEventArgs<Person> arg) {
        System.out.println("person's "+arg.propertyName + " has changed to "+arg.newValue);
    }
}
