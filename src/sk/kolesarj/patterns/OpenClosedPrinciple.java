package sk.kolesarj.patterns;

import java.util.Arrays;
import java.util.List;

/*
 * OPEN CLOSED PRINCIPLE
 * Code open for extension,
 * closed for modification
 */
enum Color {
    RED, GREEN, BLUE

}
enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}
class Product {
    public String name;
    public Color color;
    public Size size;
    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}
/*
 * Pri takomto postupe by sme potrebovali na kazdy filter
 * osobitnu metodu co nie je dobry practise..
 */
class ProductFilter{
    public List<Product> filterProductsByColor(List<Product> products, Color color){
        return products.stream().filter(p -> p.color == color).toList();
    }
    public List<Product> filterProductsBySize(List<Product> products, Size size){
        return products.stream().filter(p -> p.size == size).toList();
    }
    /*
     * Ak by sme chceli filter podla velkosti, museli by sme
     * pridat dalsiu metodu nehovoriac o tom, ze ak by sme
     * chceli filtre kombinovat, bolo by to velmi neprakticke,
     * pretoze by vzniklo 7 metod (pri tejto variacii ulohy)
     */
}

/*
 * Riesenim problemu je nasledujuca implementacia :
 * pre kazdu specifikaciu vytvorime triedu v ktorej
 * vytvorime isSatisfied metodu
 */
interface Specification<T>{
    boolean isSatisfied(T item);
}
interface Filter<T>{
    List<T> filter(List<T> items, Specification<T> spec);
}
class ColorSpecification implements Specification<Product>{
    private Color color;
    public ColorSpecification(Color color){
        this.color = color;
    }
    @Override
    public boolean isSatisfied(Product item) {
        return item.color == this.color;
    }
}
class SizeSpecification implements Specification<Product>{
    private Size size;
    public SizeSpecification(Size size){
        this.size = size;
    }
    @Override
    public boolean isSatisfied(Product item) {
        return item.size == this.size;
    }
}
class AndSpecification<T> implements Specification<T>{
    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}
class OrSpecification<T> implements Specification<T>{
    private Specification<T> first, second;
    public OrSpecification(Specification<T> first, Specification<T> second){
        this.first = first;
        this.second = second;
    }
    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) || second.isSatisfied(item);
    }
}
class BetterFilter implements Filter<Product>{

    @Override
    public List<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p)).toList();
    }
}

public class OpenClosedPrinciple {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.RED, Size.SMALL);
        Product car = new Product("Car", Color.BLUE, Size.MEDIUM);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple,car,tree,house);
        ProductFilter pf = new ProductFilter();

        System.out.println("Filter by BLUE color: (old):");
        pf.filterProductsByColor(products, Color.BLUE)
            .forEach(item -> System.out.println(" - {name: "+item.name+" ,color: "+item.color+", size: "+item.size+"}"));

        BetterFilter bf = new BetterFilter();
        System.out.println("\nFilter by BLUE color (new):");
        bf.filter(products, new ColorSpecification(Color.BLUE))
                .forEach(item -> System.out.println(" - {name: "+item.name+" ,color: "+item.color+", size: "+item.size+"}"));

        /*
        * Pri novom filtri nenarusame OCP, pretoze
        * kod je uz finalny, jedine donho pripiseme
        * novu funkcionalitu ak by trebalo :
        * Potrebujeme napr novu specifikaciu ? Neupravujeme
        * staru, len pridame novu
        * */

        System.out.println("\nFilter by BLUE color and LARGE size:");
        bf.filter(products, new AndSpecification<>(
                new ColorSpecification(Color.BLUE),
                new SizeSpecification(Size.LARGE)
                ))
                .forEach(item -> System.out.println(" - {name: "+item.name+" ,color: "+item.color+", size: "+item.size+"}"));

        System.out.println("\nFilter by BLUE color OR LARGE size:");
        bf.filter(products, new OrSpecification<>(
                new ColorSpecification(Color.BLUE),
                new SizeSpecification(Size.LARGE)
                ))
                .forEach(item -> System.out.println(" - {name: "+item.name+" ,color: "+item.color+", size: "+item.size+"}"));

        /*
        * Chceme pridat or ? Nie je problem :)
        * */
    }
}
