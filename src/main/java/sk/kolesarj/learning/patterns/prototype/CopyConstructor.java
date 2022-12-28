package sk.kolesarj.learning.patterns.prototype;

class AddressC {
    public String streetAddress, country;

    public AddressC(String streetAddress, String country) {
        this.streetAddress = streetAddress;
        this.country = country;
    }

    /*
    * copy constructor
    * Zoberie už existujúci objekt a skopíruje ho.
    * Problém je, že pre každú triedu musíme vytvoriť tento
    * konštruktor.
    *  */
    public AddressC(AddressC other){
        this(other.streetAddress, other.country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee {
    public String name;
    public AddressC address;

    public Employee(String name, AddressC address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other){
        name = other.name;
        address = new AddressC(other.address);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
public class CopyConstructor {
    public static void main(String[] args) {
        Employee john = new Employee("John", new AddressC("123 aba", "Slovenia"));
        //Employee chris = john
        Employee chris = new Employee(john);
        chris.name = "Chris";
        chris.address.country = "Portugal";
        System.out.println(john);
        System.out.println(chris);
    }
}
