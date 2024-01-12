public abstract class Product implements Comparable<Product>{
    protected String productID;
    protected String productName;
    protected int availableNumber;
    protected double price;

    public Product() {}




    public Product(String productID, String productName, int availableNumber, double price) {
        this.productID = productID;
        this.productName = productName;
        this.availableNumber = availableNumber;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Product o) {
        if (this.productID.equals(o.productID)){return 0;}
        else if(this.productID.compareTo(o.productID)>0){return 1;}
        else return -1;
//        return this.productID.compareTo(o.productID);
    }

    public String toString() {
        return "Product ID: " + productID + ' ' +
                ", Product Name: " + productName + ' ' +
                ", Available Number in stock: " + availableNumber +
                ", Price:" + price;
    }

    public String attributeValues(){
        return (productID + "," + productName + ","  + availableNumber + ","  + price);
    }


}
