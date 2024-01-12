public class Clothing extends Product{
    private int size;
    private String colour;


    public Clothing(String productID, String productName, int availableNumber, double price, int size, String colour) {
        super(productID, productName, availableNumber, price);
        this.size = size;
        this.colour = colour;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return(super.toString() +
                " , Size: " + size +
                " , Colour: " + colour);
    }

    @Override
    public String attributeValues() {
        return super.attributeValues() + ","  + size + ","  + colour;
    }
}
