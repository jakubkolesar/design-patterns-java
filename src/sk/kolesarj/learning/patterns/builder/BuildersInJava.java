package sk.kolesarj.learning.patterns.builder;

import java.util.ArrayList;
import java.util.Collections;

class HTMLBuilder {
    /*
    * Builder is a creational design pattern that lets you construct
    * complex objects step by step. The pattern allows you to produce
    * different types and representations of an object using the
    * same construction code.
    * https://refactoring.guru/design-patterns/builder*/
    private String rootName;
    private HTMLElement root = new HTMLElement();

    public HTMLBuilder(String rootName){
        this.rootName = rootName;
        root.name = rootName;
    }

    public HTMLBuilder addChild(String childName, String childText){
        HTMLElement e = new HTMLElement(childName,childText);
        root.elements.add(e);
        /*
        * If we return this, it is returning the reference to the whole
        * builder, so we can make this method fluent
        * for example: el.addChild().addChild().addChild()...*/
        return this;
    }

    public void clear(){
        root = new HTMLElement();
        root.name = this.rootName;
    }
    @Override
    public String toString(){
        return root.toString();
    }
}
class HTMLElement {
    public String name, text;
    public ArrayList<HTMLElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HTMLElement() {

    }

    public HTMLElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies(indentSize * (indent + 1), " "))).append(text).append(newLine);
        }
        for (HTMLElement e : elements) {
            sb.append((e.toStringImpl(indent + 1)));
        }
        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}
public class BuildersInJava {
    public static void main(String[] args) {
        /*
         * Predstavme si, že máme triedu s 10 parametrovým
         * konštruktorom. Nie je to dobrý practise a preto
         * použijeme builder pattern*/

        String hello = "hello";
        System.out.println("<p>" + hello + "</p>");

        String[] words = {"hello", "world"};
        //solution is using string BUILDER (better than concat)

        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for (String word : words) {
            sb.append(String.format("  <li>%s</li>\n", word));
        }
        sb.append("</ul>");
        //fluent interface because we can append forever sb.append().append().append().....

        System.out.println(sb);

        HTMLBuilder hb = new HTMLBuilder("ul");
        hb.addChild("li","hello").addChild("li","world");
        System.out.println(hb);
    }
}
