package sk.kolesarj.learning.patterns.builder;

class Person{
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

/*
* Co znamena SELF extends PB<SELF> ?
* PB dostane typový argument, ale ten argument musí byť dedič
* PersonBuildera! RECURSIVE GENERIC*/
class PersonBuilder<SELF extends PersonBuilder<SELF>> {
    protected Person person = new Person();
    public SELF withName(String name){
        person.name = name;
        /*VALID downcasting, lebo nič iné ako dedič PB sa sem nedostane..
        * IDE s týmto ma problém*/
//        return (SELF) this;
        return this.self();
    }

    protected SELF self(){
        return (SELF) this;
    }

    public Person build(){
        return this.person;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder>{
    public EmployeeBuilder worksAt(String position){
        this.person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}
public class FluentBuilders {
    public static void main(String[] args) {
        EmployeeBuilder eb = new EmployeeBuilder();
        Person jakub = eb.withName("Jakub").worksAt("Developer").build();
        System.out.println(jakub);
    }
}
