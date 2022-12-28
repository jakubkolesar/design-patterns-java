package sk.kolesarj.learning.patterns.singleton;
 class CEO {
     /*
     * V tomto prípade je povolené vytvorenie viacerých
     * inštancií, no ak sa niečo zmení, všetko je mapované
     * do rovnakého pamäťového priestoru kvôli static fieldom.
     *
     * UNSAFE. Trieda sa nespráva ako singleton. Pamäť áno*/

    public static int age;
    public static String name;

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        CEO.age = age;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        CEO.name = name;
    }

     @Override
     public String toString() {
         return "CEO{" +
                 "age=" + age +
                 ", name='" + name + '\'' +
                 '}';
     }
 }
public class Monostate {
    public static void main(String[] args) {
        CEO ceo = new CEO();
        ceo.setName("Jakub Kolesar");
        ceo.setAge(20);

        CEO ceo2 = new CEO();
        System.out.println(ceo2);
    }
}
