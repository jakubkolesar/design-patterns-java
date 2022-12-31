package sk.kolesarj.learning.patterns.flyweight;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

class FormattedText {
    /*
    * problematic because we are allocating capitalize array
    * every time FormattedText is initialized */
    private String plainText;
    private boolean [] capitalize;

    public FormattedText(String plainText) {
        this.plainText = plainText;
        capitalize = new boolean[plainText.length()];
    }

    public void capitalize(int start, int end){
        for(int i = start; i<=end; i++){
            capitalize[i] = true;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< plainText.length(); i++){
            char c = plainText.charAt(i);
            sb.append(capitalize[i] ? Character.toUpperCase(c) : c);
        }
        return sb.toString();
    }
}

class BetterFormattedText {
    private String plainText;
    private List<TextRange> formatting = new ArrayList<>();
    public BetterFormattedText(String plainText) {
        this.plainText = plainText;
    }

    public class TextRange {
        // FLYWEIGHT CLASS
        public int start, end;
        public boolean capitalize,bold,italic;

        public TextRange(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public boolean covers(int pos){
            return pos >= start && pos <= end;
        }
    }

    public TextRange getRange(int start, int end){
        TextRange textRange = new TextRange(start, end);
        formatting.add(textRange);
        return textRange;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< plainText.length(); i++){
            char c = plainText.charAt(i);
            for(TextRange range : formatting){
                if(range.covers(i) && range.capitalize){
                    c = Character.toUpperCase(c);
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
public class TextFormatting {
    public static void main(String[] args) {
        FormattedText ft = new FormattedText("blabla ty jeden");
        ft.capitalize(3,10);
        System.out.println(ft);

        BetterFormattedText bft = new BetterFormattedText("Make America Great Again");
        bft.getRange(13,18).capitalize = true;
        System.out.println(bft);

    }
}
