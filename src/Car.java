import java.util.List;
public class Car {
    String name;
    List<String> variants;
    List<Double> prices;

    Car(String name, List<String> variants, List<Double> prices) {
        this.name = name;
        this.variants = variants;
        this.prices = prices;
    }
}
