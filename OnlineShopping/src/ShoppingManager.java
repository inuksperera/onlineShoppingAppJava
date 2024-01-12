import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

public interface ShoppingManager {

    //list of available products in the shop
    ArrayList<Product> productList = new ArrayList<>();
    void addProduct();
    void deleteProduct();
    void printProducts();
    void saveProducts();

    void readProducts();

    void userInterface();
}
