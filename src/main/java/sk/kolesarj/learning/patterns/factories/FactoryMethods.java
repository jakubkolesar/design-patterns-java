package sk.kolesarj.learning.patterns.factories;

class Point {
    private double x, y;

    /*
     * Do konštruktora by sme dávali nejakú logiku,
     * začína to byť celé komplikované, čo nie je
     * veľmi dobrý practise. Chceli by sme to nejako
     * vyriešiť, lenže nedá sa, pretože dva konštruktory
     * s rovnakým menom a parametrami existovať
     * v jednej triede nemôžu. FACTORY PATTERN to vyrieši*/
    private Point(double a, double b) {
        this.x = x;
        this.y = y;
    }

    /*FACTORY*/
    public static class Factory {
        /*FACTORY METHODS*/
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }
        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }
}

public class FactoryMethods {
    public static void main(String[] args) {
        Point point = Point.Factory.newCartesianPoint(3,4);
    }
}
