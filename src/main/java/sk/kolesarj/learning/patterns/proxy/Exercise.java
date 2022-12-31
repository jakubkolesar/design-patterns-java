package sk.kolesarj.learning.patterns.proxy;
class Person1
{
    private int age;

    public Person1(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String drink() { return "drinking"; }
    public String drive() { return "driving"; }
    public String drinkAndDrive() { return "driving while drunk"; }
}

class ResponsiblePerson
{
    private Person1 person1;
    public ResponsiblePerson(Person1 person1)
    {
        this.person1 = person1;
    }

    public void setAge(int age){
        person1.setAge(age);
    }
    public int getAge(){
        return person1.getAge();
    }

    public String drink()
    {
        return getAge() >= 18 ? person1.drink() : "too young";
    }

    public String drive()
    {
        return getAge() >= 16 ? person1.drive() : "too young";
    }

    public String drinkAndDrive()
    {
        return "dead";
    }
}
public class Exercise {
    public static void main(String[] args) {

    }
}
