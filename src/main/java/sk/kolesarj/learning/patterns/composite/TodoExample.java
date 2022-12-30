package sk.kolesarj.learning.patterns.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface TodoList {
    public String getHTML();
}

class Todo implements TodoList {
    private String text;
    public Todo(String text){
        this.text = text;
    }

    @Override
    public String getHTML() {
        return this.text;
    }
}

class Project implements TodoList {
    public List<TodoList> todoLists = new ArrayList<>();
    private String name;
    public Project(String name){
        this.name = name;
    }

    private String nestedHTML(StringBuilder sb, int level){
        String i = String.join("", Collections.nCopies(level * 2, " "));
        sb.append(String.format("%s<h1>%s</h1>",i,this.name));
        sb.append(System.lineSeparator());
        sb.append(String.format("%s<ul>\n",i));


        for(TodoList td : todoLists){
            if(td instanceof Project){
                ((Project) td).nestedHTML(sb,level+1);
            }
            else {
                sb.append(String.format("  %s<li>%s</li>",i,td.getHTML())).append(System.lineSeparator()).append(i);
            }
        }

        sb.append("</ul>").append(System.lineSeparator());
        return sb.toString();
    }
    @Override
    public String getHTML() {
        StringBuilder sb = new StringBuilder();
        return this.nestedHTML(sb,0);
    }

}
public class TodoExample {
    public static void main(String[] args) {
        Project todoList = new Project("todo");
        Project subTodo = new Project("subTodo");


        todoList.todoLists.add(new Todo("win poker"));
        todoList.todoLists.add(new Todo("win roulette"));

        todoList.todoLists.add(subTodo);
        subTodo.todoLists.add(new Todo("go home"));

        System.out.println(todoList.getHTML());

    }
}
