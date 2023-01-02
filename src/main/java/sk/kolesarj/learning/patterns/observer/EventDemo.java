package sk.kolesarj.learning.patterns.observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<TArgs>
{
    private int count = 0;
    private Map<Integer, Consumer<TArgs>>
            handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler)
    {
        int i = count;
        handlers.put(count++, handler);
        return new Subscription(this, i);
    }

    public void fire(TArgs args)
    {
        for (Consumer<TArgs> handler : handlers.values())
            handler.accept(args);
    }

    public class Subscription implements AutoCloseable
    {
        private Event<TArgs> event;
        private int id;

        public Subscription(Event<TArgs> event, int id)
        {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() /*throws Exception*/
        {
            event.handlers.remove(id);
        }
    }
}

class ChangedEventArgs
{
    public Object source;
    public String propertyName;

    public ChangedEventArgs(Object source, String propertyName)
    {
        this.source = source;
        this.propertyName = propertyName;
    }
}
class PersonE {
    public Event<ChangedEventArgs>
            propertyChanged = new Event<>();

    private int age;

    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        if (this.age == age) return;

        boolean oldCanVote = getCanVote();

        this.age = age;
        propertyChanged.fire(new ChangedEventArgs(
                this, "age"
        ));

        if (oldCanVote != getCanVote())
        {
            propertyChanged.fire(new ChangedEventArgs(
                    this, "canVote"
            ));
        }
    }

    public boolean getCanVote()
    {
        return age >= 18;
    }
}
public class EventDemo {
    public static void main(String[] args) {
        PersonE person = new PersonE();

        Event<ChangedEventArgs>.Subscription sub = person.propertyChanged.addHandler(x -> {
            System.out.println("Person's " + x.propertyName + " has changed");
        });

        person.setAge(18);
        person.setAge(19);
        person.setAge(120);
        sub.close();
        person.setAge(21);

    }
}
