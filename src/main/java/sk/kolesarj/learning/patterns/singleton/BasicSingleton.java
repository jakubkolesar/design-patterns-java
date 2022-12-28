package sk.kolesarj.learning.patterns.singleton;

import java.io.*;

class Demo implements Serializable {

    private int value = 0;
    private Demo(){

    }

    /*
    * Konštruktor je private aby sme zabránili vytváraniu inštancií.
    * Vytvoríme si JEDNU inštanciu a exposneme ju programu pomocou
    * public getteru. */
    private static final Demo INSTANCE = new Demo();
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    /*
    * readResolve method allows a class to replace/resolve the
    * object read from the stream before it is returned to the caller. */
    protected Object readResolve(){
        return INSTANCE;
    }
    public static Demo getInstance(){
        return INSTANCE;
    }
}

public class BasicSingleton {
    static void savetoFile(Demo demo, String filename) throws Exception {
        try(FileOutputStream fileout = new FileOutputStream(filename); ObjectOutputStream out = new ObjectOutputStream(fileout)){
            out.writeObject(demo);
        };
    }

    static Demo readFromFile(String filename) throws Exception {
        try(FileInputStream fileIn = new FileInputStream(filename); ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (Demo) in.readObject();
        }
    }
    public static void main(String[] args) throws Exception{
        Demo instance = Demo.getInstance();
        instance.setValue(123);

        /*problem nastáva pri serializácii*/
        String filename = "singleton.bin";
        savetoFile(instance, filename);

        instance.setValue(222);

        Demo instance2 = readFromFile(filename);
        System.out.println(instance == instance2);
        /*teraz máme 2 singletony čo je nereálne.
        * fix je použitie readResolve*/
        System.out.println(instance.getValue());
        System.out.println(instance2.getValue());

    }
}

