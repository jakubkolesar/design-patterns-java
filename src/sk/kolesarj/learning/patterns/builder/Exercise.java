package sk.kolesarj.learning.patterns.builder;

import java.util.ArrayList;
import java.util.Collections;

class ClassField {
    public String name;
    public String type;
    private ArrayList<ClassField> fields = new ArrayList<>();
    private final int indentSize = 2;

    public ClassField(){}
    public ClassField(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void add(ClassField a){
        this.fields.add(a);
    }

    private String toStringImpl(int indent){
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        if(this.name != null && !this.name.isEmpty() && this.type != null && !this.type.isEmpty()){
            sb.append(String.join("", Collections.nCopies(indentSize * indent , " ")))
                    .append(String.format("public %s %s;\n",this.type, this.name));
        }
        for (ClassField e : fields) {
            sb.append((e.toStringImpl(indent + 1)));
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        return this.toStringImpl(0);
    }
}

class CodeBuilder
{
    private String className;
    private ClassField root = new ClassField();
    public CodeBuilder(String className)
    {
        this.className = className;
    }

    public CodeBuilder addField(String name, String type)
    {
        this.root.add(new ClassField(name,type));
        return this;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("public class %s\n{\n",this.className))
                .append(this.root.toString())
                .append("}\n");
        return sb.toString();
    }
}

class Exercise {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
        System.out.println(cb);
    }
}