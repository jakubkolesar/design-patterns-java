package sk.kolesarj.learning.patterns.prototype;

class Point
{
    public int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point(Point other){
        this(other.x, other.y);
    }
}

class Line
{
    public Point start, end;

    public Line(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    public Line deepCopy()
    {
        // todo
        return new Line(new Point(start), new Point(end));
    }
}

public class Exercise {
    public static void main(String[] args) {
        Line line = new Line(new Point(2,3), new Point(4,5));

    }
}
