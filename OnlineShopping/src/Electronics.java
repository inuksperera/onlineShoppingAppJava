public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;


    public Electronics(String productID, String productName, int availableNumber, double price, String brand, int warrantyPeriod){
        super(productID, productName, availableNumber, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return(
                super.toString() +
                        " , Brand: " + brand + ' ' +
                        " , Warranty Period: " + warrantyPeriod);
    }

    @Override
    public String attributeValues() {
        return super.attributeValues()  + "," + brand  + "," + warrantyPeriod;

    }

}
