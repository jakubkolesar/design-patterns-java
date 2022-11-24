package sk.kolesarj.patterns;
/**
 * Liskovej substitucny princip:
 * ak mame base class ako napr Rectangle,
 * musime vediet pouzit odvodenu classu
 * bez narusenia funkcionality vsade v programe!
 * Toto riesenie je zle, Square is not a Rectangle:)
 */
class Rectangle{
    protected int width;
    protected int height;
    public Rectangle(){}
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getArea(){
        return this.width * this.height;
    }
    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
/**
 * Toto je problem, pretoze mozme dalej v
 * programe menit width a height, tym padom
 * zo stvorca spravime obdlznik..
 */
class Square extends Rectangle {
    public Square(){
    }
    public Square(int size){
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}
public class LiskovSubstitutionPrinciple {
    /**
     * preco to je problem ?
     * funkcia moze pouzit aj square aj rectangle
     *
     * useIt funguje dobre pre Obdlznik, nedobre
     * pre Stvorec cize PRINCIP JE PORUSENY
     *
     */
    static void useIt(Rectangle r){
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("Expected area of "+width*10+", got "+r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2,3);
        useIt(rc);
        Square sq = new Square(5);
        /**
         * chceme dostat 50, ale dostaneme 100
         */
        useIt(sq);
    }
}
