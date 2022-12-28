package sk.kolesarj.learning.patterns.singleton;

class InnerStaticSingleton {
    private InnerStaticSingleton(){

    }

    private static class Implementation {
        /*
        * Private classes can access private members of outer classes*/
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public InnerStaticSingleton getInstance(){
        return Implementation.INSTANCE;
    }
}
public class InnerStaticDemo {
}
