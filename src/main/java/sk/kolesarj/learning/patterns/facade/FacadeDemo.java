package sk.kolesarj.learning.patterns.facade;

import java.util.ArrayList;
import java.util.List;

class Buffer {
    private char[] characters;
    private int lineWidth;

    public Buffer(int lineHeight, int lineWidth) {
        this.lineWidth = lineWidth;
        characters = new char[lineHeight * lineWidth];
    }

    public char charAt(int x, int y) {
        return characters[y * lineWidth + x];
    }
}

class Viewport {
    private final Buffer buf;
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;

    public Viewport(Buffer buf, int width, int height,
                    int offsetX, int offsetY) {
        this.buf = buf;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public char charAt(int x, int y) {
        return buf.charAt(x + offsetX, y + offsetY);
    }
}

class Console {
    private List<Viewport> viewportList = new ArrayList<>();
    int width, height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addViewport(Viewport viewport) {
        viewportList.add(viewport);
    }

    public static Console newConsole(int width, int height){
        Buffer buffer = new Buffer(height, width);
        Viewport viewport = new Viewport(buffer, width, height, 0, 0);
        Console console = new Console(width, height);
        console.addViewport(viewport);
        return console;
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for(Viewport vp : viewportList){
                    System.out.println(vp.charAt(x,y));
                }
            }
            System.out.println();
        }
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        /*Aby sme vedeli vyrenderovat nieco na konzolu,
        * musime spravit vsetky tieto kroky co je pre usera
        * nie velmi dobre.
        * namiesto toho poskytneme FACADE aby sme poskytli nieco jednoduche*/
        Buffer buffer = new Buffer(30, 20);
        Viewport viewport = new Viewport(buffer, 30, 20, 0, 0);
        Console console = new Console(30, 20);
        console.addViewport(viewport);
        console.render();

        Console console2 = Console.newConsole(30,20);
        console2.render();

    }
}
