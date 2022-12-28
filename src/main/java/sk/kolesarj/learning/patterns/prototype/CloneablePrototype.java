package sk.kolesarj.learning.patterns.prototype;

import java.util.Arrays;

/*
* Java poskytuje Cloneable marker interface.
* Môžme zaimplementovať metódu clone(), pomocou
* ktorej skopírujeme objekt, nie referenciu.*/
class Address implements Cloneable{
    public String streetname;
    public int houseNumber;

    public Address(String streetname, int houseNumber) {
        this.streetname = streetname;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetname='" + streetname + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
    //deep copying
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Address(streetname, houseNumber);
    }
}

class Person implements Cloneable{
    public String[] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Person(names.clone(),(Address) address.clone());
    }
}

public class CloneablePrototype {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person john = new Person(new String[]{"John", "smith"},
                new Address("London Road", 24));
        //chceme vytvorit niekoho, kto zije na rovnakej ulici alebo v rovnakom dome

        //zlé riešenie pretože kopírujeme REFERENCIU
        /*
        Person jane = john;
        jane.names[0] = "Jane";
        jane.address.houseNumber = 124;
        */

        Person jane = (Person) john.clone();
        jane.names[0] = "Jane";
        jane.address.houseNumber = 124;



        System.out.println(john);
        System.out.println(jane);
    }
}
