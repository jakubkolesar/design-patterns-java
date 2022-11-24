package sk.kolesarj.patterns;
/*
* How to split interfaces into a smaller interfaces.
* Do Interface-u by sme nemali davat viac veci, ako
* planujeme implementovat
* */

class Document {
    //
}

interface Machine {
    void print(Document d);
    void fax(Document d);
    void scan(Document d);
}

class MultifunctionPrinter implements Machine {
    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void fax(Document d) {
        //
    }

    @Override
    public void scan(Document d) {
        //
    }
}
/**
* Zla implementacia pretoze implementuje Machine,
* ktora vie aj skenovat aj faxovat (co stara
* tlaciaren nevie). Mohli by sme nechat metody prazdne
* ale uz je tam indikacia, ze by metody mali
* byt implementovane
* */
class OldFashionedPrinter implements Machine{
    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void fax(Document d) {
        //
    }

    @Override
    public void scan(Document d) {
        //
    }
}

/*
* RIESENIE: Interface Segregation
* */
interface Printer {
    void print(Document d);
}
interface Scanner {
    void scan(Document d);
}

/*
* Chceme implementovat LEN printer, implementujeme
* LEN printer
* */
class JustAPrinter implements Printer{
    @Override
    public void print(Document d) {
        //
    }
}

/*
* chceme zariadenie ktore implementuje viac funkcionalit ?
* implementacia vyzera nasledovne :
* */
class PhotoCopier implements Printer, Scanner {
    @Override
    public void print(Document d) {
        //
    }

    @Override
    public void scan(Document d) {
        //
    }
}
