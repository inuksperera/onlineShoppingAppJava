import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager{

    //variable to toggle append mode when saving file

    public static void main(String[] args) {
        System.out.println("\nManager Menu");
        printMenu();

    }

    //menu for manager to manage products
    public static void printMenu(){
        ShoppingManager shoppingManager = new WestminsterShoppingManager();
        Boolean exit = false;
        while(!exit) {
            System.out.print(
                    "-------------------------------------------------\n" +
                    "Please select an option: \n" +
                    "\t1) add a new product to the products list\n" +
                    "\t2) delete a product from the products list\n" +
                    "\t3) print the list of products\n" +
                    "\t4) save the list of products to file\n" +
                    "\t5) read the list of products from file\n" +
                    "\t6) open GUI\n" +
                    "\t0) exit menu\n" +
                    "-------------------------------------------------\n" +
                    "Enter option: "
            );
            Scanner input = new Scanner(System.in);
            String choice = input.next();
            switch (choice) {

                case "0":
                    exit = true;
                    break;

                case "1":

                    if (productList.size()<=50) {
                        shoppingManager.addProduct();
                    } else {
                        System.out.println("\nError: Maximum number of products(50) reached!");
                    }
                    break;


                case "2":
                    shoppingManager.deleteProduct();
                    break;

                case "3":
                    if (productList.size() == 0) {
                        System.out.println("Product list is empty");
                    }else {
                        shoppingManager.printProducts();
                    }
                    break;

                case "4":
                    shoppingManager.saveProducts();
                    break;
                case "5":
                    shoppingManager.readProducts();
                    break;
                case "6":
                    shoppingManager.userInterface();
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        }


    }

    @Override
    public void userInterface(){
//        ShoppingGUI shoppingGUI = new ShoppingGUI();
        UserGUI userGUI = new UserGUI();
    }









    @Override
    public void addProduct() {
        Product product = null;
        String option;
        Scanner input = new Scanner(System.in);

        while(true){
            System.out.print("Press 'E' to add an electronic product to the list\n" +
                    "Press 'C' to add a clothing product to the list\n" +
                    "Enter option: ");
            option = input.next();
            if (option.equalsIgnoreCase("E") || option.equalsIgnoreCase("C")) {
                break;
            }else{
                System.out.println("Invalid input. Try again.");
            }
        }

        try {
            System.out.print("Enter product ID: ");
            String productID = input.next();

            //to jump to next line
            input.nextLine();

            System.out.print("Enter product name: ");
            String productName = input.nextLine();
            System.out.print("Enter available number of product in stock: ");
            int availableNumber = input.nextInt();
            System.out.print("Enter price of product: ");
            double price = input.nextDouble();

            //to jump to next line
            input.nextLine();

            if (option.equalsIgnoreCase(("E"))) {
                System.out.print("Enter the brand: ");
                String brand = input.nextLine();

                System.out.print("Enter the warranty period: ");
                int warrantyPeriod = input.nextInt();

                product = new Electronics(productID, productName, availableNumber, price, brand, warrantyPeriod);
//            product = new Electronics("456","TV",7, 2000.00,brand, warrantyPeriod);
            } else if (option.equalsIgnoreCase("C")) {
                System.out.print("Enter the size: ");
                int size = input.nextInt();

                //to jump to next line
                input.nextLine();

                System.out.print("Enter the colour: ");
                String colour = input.next();

                product = new Clothing(productID, productName, availableNumber, price, size, colour);
//            product = new Clothing("123", "t-shirt", 19, 21.0, size, colour);

            }
            productList.add(product);

        }catch (Exception e){
            System.out.println(e);
        }



        //REMOVE
//        for (Product item: productList) {
//            System.out.println(item.toString());
//        }
    }

    @Override
    public void deleteProduct() {
        Product removedProduct = null;

        System.out.println("Enter product ID of product to be deleted: ");
        Scanner input = new Scanner(System.in);
        String removeID = input.next();
//        System.out.println("removeid u entered: " + removeID);

        //contains(?)
        for (Product currentProduct: productList) {
            if (currentProduct.getProductID().contains(removeID)) {
                removedProduct = currentProduct;
            }
        }
//        System.out.println("id: " + currentProduct.getProductID());
//        System.out.println("rrr: " + removedProduct);


        if(productList.remove(removedProduct)){
            System.out.println("Removed product " + removedProduct);
        }else{
            System.out.println("Error: Could not find product");
        }

    }

    @Override
    public void printProducts() {
        //sorting product list
        Collections.sort(productList);



        for (Product item: productList) {
//            if (item.getClass().toString().contains("Electronics")) {

                System.out.println("Product type: " + item.getClass().toString().replace("class ", "") + " , " +
                        item.toString());
//                System.out.println(item.toString());
//            }
//            else if (item.getClass().toString().contains("Clothing")) {
//                System.out.println("Product type: Clothing, " + item.toString());
//            }
        }
    }

    @Override
    public void saveProducts() {
        //close stream in file handling
        try {
            File file = new File("C:\\Users\\INUKA PERERA\\Documents\\Files\\IIT\\OOP\\cw\\OnlineShopping",
                    "onlineShopping.txt");
            FileWriter writer = new FileWriter(file);
            for (Product currentProduct: productList) {
                writer.write(currentProduct.getClass().toString().replace("class ", "") + "," + currentProduct.attributeValues());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Products saved to file");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void readProducts() {
//        String[] values = {};
        ArrayList<String> valueList = new ArrayList<>();
        try{
            File file = new File("C:\\Users\\INUKA PERERA\\Documents\\Files\\IIT\\OOP\\cw\\OnlineShopping",
                    "onlineShopping.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

//            String line = bufferedReader.readLine();
//            while(line != null){
//                valueList.add(line);
//
////                System.out.println(line);
//                line = bufferedReader.readLine();
////                System.out.println(line);
////                values = line.split(",");
//            }
////            for (int i=0; i<values.length-1; i++) {
////                System.out.println(values[i]);
////            }
//
//            for (String currentLine: valueList) {
////                System.out.println(currentLine);
//                String[] splitArray = currentLine.split(",");
//
//                Product product = new Electronics(
//                        splitArray[0],
//                    splitArray[1],
//                        Integer.valueOf(splitArray[2]),
//                        Double.valueOf(splitArray[3]),
//                        splitArray[4],
//                        Integer.valueOf(splitArray[5])
//                );

            String line = bufferedReader.readLine();
            while(line != null) {
//                valueList.add(line);

//                System.out.println(line);
//                System.out.println(line);
//                values = line.split(",");

//            for (int i=0; i<values.length-1; i++) {
//                System.out.println(values[i]);
//            }

//            for (String currentLine: valueList) {
//                System.out.println(currentLine);
//                String[] splitArray = new String[7];

                String[] splitArray = new String[0];
                if (line != null) {
                    splitArray = line.split(",");
                }

//                for (int i=0; i<splitArray.length; i++) {
//                System.out.println(splitArray[i]);
//            }
                Product product = null;
                if (splitArray[0].equals("Electronics")) {
                    product = new Electronics(
                            splitArray[1],
                            splitArray[2],
                            Integer.valueOf(splitArray[3]),
                            Double.valueOf(splitArray[4]),
                            splitArray[5],
                            Integer.valueOf(splitArray[6])
                    );
                } else if (splitArray[0].equals("Clothing")) {
                    product = new Clothing(
                            splitArray[1],
                            splitArray[2],
                            Integer.valueOf(splitArray[3]),
                            Double.valueOf(splitArray[4]),
                            Integer.valueOf(splitArray[5]),
                            splitArray[6]
                    );
                }

                productList.add(product);


//            }

                line = bufferedReader.readLine();

//                Product product = new Electronics(productID, productName, availableNumber, price, brand, warrantyPeriod);
            }
            reader.close();
            bufferedReader.close();
            System.out.println("Products successfully read from file");
        }catch (Exception e){
            System.out.println(e);
        }




    }
}


/*
//            File file = new File(".");
//            if (file.exists()) {
//                System.out.println("harro");
//            }else System.out.println("nuh uh");

//            //checking if the file already exists
//            if () {
//            }

//            FileWriter writer = null;
//            boolean appendStatus = false;

            File file = new File("C:\\Users\\INUKA PERERA\\Documents\\Files\\IIT\\OOP\\cw\\OnlineShopping",
                    "onlineShopping.txt");
//            for (Product currentProduct: productList) {
               FileWriter writer = new FileWriter(file);
               writer.write(" WONDERHOY HARRO ");

               writer.close();
//               appendStatus = true;
//            }

//            writer.write(productList.toString());
*/