package sk.kolesarj.learning.patterns.singleton;

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".",".");
    }

    private static StaticBlockSingleton instance; //instance - not final, INSTANCE - final
    static {
        try {
            instance = new StaticBlockSingleton();
        }
        catch(Exception e){
            System.err.println("failed");
        }
    }

    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}
public class DemoStaticBlockSingleton {
}
