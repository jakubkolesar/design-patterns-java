package sk.kolesarj.learning.patterns.singleton;

import java.util.HashMap;

enum Subsystem {
    PRIMARY,
    AUXILARY,
    FALLBACK
}

class Printer {
    private static int instanceCount = 0;
    private Printer() {
        instanceCount++;
        System.out.println(String.format("total of %d instances created so far", instanceCount));
    }
    private static HashMap<Subsystem, Printer> instances = new HashMap<>();
    public static Printer get(Subsystem ss) {
        /*SYNC behaviour*/
        if (instances.containsKey(ss)) {
            return instances.get(ss);
        }
        Printer instance = new Printer();
        instances.put(ss, instance);
        return instance;
    }
}

public class Multition {
    public static void main(String[] args) {
        Printer main = Printer.get(Subsystem.PRIMARY);
        Printer aux = Printer.get(Subsystem.AUXILARY);
        Printer aux2 = Printer.get(Subsystem.AUXILARY);
    }
}
