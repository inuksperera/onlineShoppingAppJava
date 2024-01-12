import java.util.PropertyPermission;

public class Main {
    public static void main(String[] args) {
        Product product = new Clothing("123", "t-shirt", 19, 21.0, 2, "blue");
        System.out.println(product.attributeValues());
    }
}