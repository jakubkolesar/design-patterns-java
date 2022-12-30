package sk.kolesarj.learning.patterns.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {
    /*
    * collection of other objects
    * or a singular object. it can also
    * contain another GraphicObject*/

    protected String name = "Group";
    public String color;
    public List<GraphicObject> children = new ArrayList<>();

    public GraphicObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void print(StringBuilder stringBuilder, int depth){
        stringBuilder.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth > 0 ? " " : "")
                .append((color == null || color.isEmpty()) ? "" : color + " ")
                .append(getName())
                .append(System.lineSeparator());
        for(GraphicObject child : children)
            child.print(stringBuilder, depth + 1);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb,0);
        return sb.toString();
    }
}

class Circle extends GraphicObject {
    public Circle(String color){
        this.name = "Circle";
        this.color = color;
    }
}
class Square extends GraphicObject {
    public Square(String color){
        this.name = "Square";
        this.color = color;
    }
}
public class CompositeDemo {
    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My drawing");
        drawing.children.add(new Square("red"));
        drawing.children.add(new Circle("yellow"));

        GraphicObject group = new GraphicObject();
        group.children.add(new Circle("blue"));
        group.children.add(new Square("blue"));
        drawing.children.add(group);

        System.out.println(drawing.toString());
    }
}
