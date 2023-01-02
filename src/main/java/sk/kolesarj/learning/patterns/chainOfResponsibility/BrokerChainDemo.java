package sk.kolesarj.learning.patterns.chainOfResponsibility;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//CQS
class Event<Args>{
    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();
    public int subscribe(Consumer<Args> handler){
        int i = index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key){
        handlers.remove(key);
    }

    public void fire(Args args){
        for(Consumer<Args> handler : handlers.values()){
            handler.accept(args);
        }
    }
}

class Query {
    public String creatureName;
    enum Argument {
        ATTACK, DEFENSE
    }
    public Argument argument;
    public int result;

    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game {
     public Event<Query> queries = new Event<>();

}

class Creaturee {
    private Game game;
    public String name;
    public int baseAttack, baseDefense;

    public Creaturee(Game game, String name, int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack(){
        Query q = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    int getDefense(){
        Query q = new Query(name, Query.Argument.DEFENSE, baseDefense);
        game.queries.fire(q);
        return q.result;
    }

    @Override
    public String toString() {
        return "Creaturee{" +
                "game=" + game +
                ", name='" + name + '\'' +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}

class CreatureeModifier {
    protected Game game;
    protected Creaturee creaturee;

    public CreatureeModifier(Game game, Creaturee creaturee) {
        this.game = game;
        this.creaturee = creaturee;
    }
}

class DoubleAttack extends CreatureeModifier implements AutoCloseable {
    private final int token;
    public DoubleAttack(Game game, Creaturee creaturee) {
        super(game, creaturee);
        token = game.queries.subscribe(q->{
           if(q.creatureName.equals(creaturee.name) && q.argument == Query.Argument.ATTACK){
               q.result *=2;
           }
        });
    }

    @Override
    public void close()  {
        game.queries.unsubscribe(token);
    }
}
class IncreasedDefense extends CreatureeModifier implements AutoCloseable {
    private final int token;
    public IncreasedDefense(Game game, Creaturee creaturee) {
        super(game, creaturee);
        token = game.queries.subscribe(q->{
           if(q.creatureName.equals(creaturee.name) && q.argument == Query.Argument.DEFENSE){
               q.result +=3;
           }
        });
    }

    @Override
    public void close()  {
        game.queries.unsubscribe(token);
    }
}
public class BrokerChainDemo {
    public static void main(String[] args) {
        Game game = new Game();
        Creaturee strongGoblin = new Creaturee(game, "StrongGoblin", 2, 2);

        System.out.println(strongGoblin);
        IncreasedDefense increasedDefense = new IncreasedDefense(game, strongGoblin);
        DoubleAttack da = new DoubleAttack(game,strongGoblin);
        try(da){
            System.out.println(strongGoblin);
        }

        System.out.println(strongGoblin);
    }
}
