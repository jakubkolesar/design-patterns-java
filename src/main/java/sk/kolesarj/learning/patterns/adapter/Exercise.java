package sk.kolesarj.learning.patterns.adapter;

class RoundPeg {
    private int radius;

    public RoundPeg(int radius) {
        this.radius = radius;
    }

    public RoundPeg() {

    }

    public int getRadius() {
        return this.radius;
    }
}

class RoundHole {
    private int radius;

    public RoundHole(int radius) {
        this.radius = radius;
    }

    public boolean fits(RoundPeg peg) {
        return peg.getRadius() <= this.radius;
    }
}

class SquarePeg {
    private int width;

    public SquarePeg(int width) {
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }
}

class SquarePegAdapter extends RoundPeg {
    private SquarePeg squarePeg;

    public SquarePegAdapter(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    @Override
    public int getRadius() {
        return (int) (this.squarePeg.getWidth() * Math.sqrt(2) / 2);
    }
}

public class Exercise {
    public static void main(String[] args) {
        RoundHole roundHole = new RoundHole(5);
        RoundPeg roundPeg = new RoundPeg(5);
        SquarePeg squarePeg = new SquarePeg(10);

        System.out.println(roundHole.fits(roundPeg));
        /*
         * Program nám spadne lebo typy nie sú kompatibilné..
         * Musíme použiť adapter.
         * System.out.println(roundHole.fits(squarePeg));
         * */
        SquarePegAdapter squarePegAdapter = new SquarePegAdapter(squarePeg);
        System.out.println(roundHole.fits(squarePegAdapter));

    }
}
