package sk.kolesarj.learning.patterns.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class User {
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}

class User2 {
    public static List<String> fullNames = new ArrayList<>();
    private int[] names;

    public User2(String fullName) {
        Function<String, Integer> getOrAdd = (String s) -> {
            int i = fullNames.indexOf(s);
            if (i != -1) {
                return i;
            } else {
                fullNames.add(s);
                return fullNames.size() - 1;
            }
        };
        names = Arrays.stream(fullName.split(" "))
                .mapToInt(s -> getOrAdd.apply(s)).toArray();

        for(int i : names){
            System.out.println(i);
        }
        System.out.println("---------");
    }
}

public class FlyweightDemo {
    public static void main(String[] args) {
        //inneficient
        User jakubKolesar = new User("Jakub Kolesar");
        User jakubKolesar1 = new User("Jan Kolesar");
        User jakubKolesar2 = new User("Jozef Kolesar");
        User jakubKolesar3 = new User("Hehe Kolesar");

        //efficient

        User2 jakubKolesarE = new User2("Jakub Kolesar");
        User2 jakubKolesarE1 = new User2("Jakub Kolenar");
        User2 jakubKolesarE2 = new User2("Jan Kolesar");
        User2 jakubKolesarE3 = new User2("Jakub Kolesar");

    }
}
