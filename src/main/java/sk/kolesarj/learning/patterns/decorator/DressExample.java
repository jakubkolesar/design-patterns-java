package sk.kolesarj.learning.patterns.decorator;

interface Dress {
    public void assemble();
}

class BasicDress implements Dress{
    @Override
    public void assemble() {
        System.out.println("Basic dress features");
    }
}

class DressDecorator implements Dress {
    protected Dress dress;
    public DressDecorator(Dress dress){
        this.dress = dress;
    }

    @Override
    public void assemble() {
        this.dress.assemble();
    }
}

class CasualDress extends DressDecorator {
    public CasualDress(Dress dress) {
        super(dress);
    }

    @Override
    public void assemble(){
        super.assemble();
        System.out.println("Adding Casual dress features");
    }
}
class SportyDress extends DressDecorator {
    public SportyDress(Dress dress) {
        super(dress);
    }

    @Override
    public void assemble(){
        super.assemble();
        System.out.println("Adding sporty dress features");
    }
}
public class DressExample {
    public static void main(String[] args) {
        Dress sportyDress = new SportyDress(new BasicDress());
        sportyDress.assemble();

        Dress sportyCasual = new SportyDress(new CasualDress(new BasicDress()));
        sportyCasual.assemble();
    }
}
