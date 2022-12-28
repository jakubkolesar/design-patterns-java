package sk.kolesarj.learning.patterns.singleton;
/*
* Niekedy chceme inicializovať singleton, keď niekto
* zavolá getInstance() a nechceme aby bol vytvorený v
* static bloku.*/
class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){
        System.out.println("initializing lazy singleton");
    }

    public static synchronized LazySingleton getInstance() {
        //check if instance was created
        if(instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    //double-checked locking (outdated)
//    public static LazySingleton getInstance(){
//        if(instance == null){
//            synchronized (LazySingleton.class){
//                if(instance == null){
//                    instance = new LazySingleton();
//                }
//            }
//        }
//        return instance;
//    }
}
public class LazySingletonDemo {
    public static void main(String[] args) {
    }
}
