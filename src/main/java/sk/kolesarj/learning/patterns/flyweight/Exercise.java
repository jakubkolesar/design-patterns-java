package sk.kolesarj.learning.patterns.flyweight;

import java.util.ArrayList;
import java.util.List;

class Sentence {
    private String sentence;
    private List<WordToken> formatting = new ArrayList<>();

    public Sentence(String plainText) {
        this.sentence = plainText;
    }

    public WordToken getWord(int index) {
        WordToken wordToken = new WordToken(index);
        formatting.add(wordToken);
        return wordToken;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] words = this.sentence.split(" ");
        for(int i = 0; i<words.length; i++){
            String w = words[i];
            for(WordToken wt : formatting){
                if(wt.covers(i)){
                    w = w.toUpperCase();
                }
            }
            sb.append(String.format("%s ",w));
        }
        return sb.toString().trim();
    }

    class WordToken {
        public boolean capitalize;
        public int index;

        public WordToken(int index) {
            this.index = index;
        }

        public boolean covers(int index) {
            return this.index == index;
        }

    }
}

public class Exercise {
    public static void main(String[] args) {
        Sentence s = new Sentence("Jakub Kolesar krasoň");
        s.getWord(1).capitalize = true;

        System.out.println(s);
    }
}
