import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCartGUI extends JFrame {
//    public static void main(String[] args) {
//        // For testing purposes
//        ArrayList<Product> shoppingCartList = new ArrayList<>();
//        shoppingCartList.add(new Electronics("E001", "Laptop", 0, 2, "hello", 2));
//        shoppingCartList.add(new Clothing("C001", "Shirt", 0, 2, 1, "blue"));
//
//        SwingUtilities.invokeLater(() -> new ShoppingCartGUI(shoppingCartList));
//    }
    ShoppingCartGUI(HashMap<Product, Integer> shoppingCartMap, boolean userFound){
        this.setTitle("Shopping Cart");
        this.setSize(650,450);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(195, 191, 255));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255, 225, 195));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        String[] columns = {"Product", "Quantity", "Price"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0);

        JTable table = new JTable(defaultTableModel){
            public boolean isCellEditable(int nRow, int nCol) {
                return false;
            }
        };

//        ArrayList<Product> quantitiesList = new ArrayList<>();
//        for (Product i: shoppingCartMap.keySet()) {
//            if (quantitiesList.contains(item)) {
//
//            }
//
//        }




        int total = 0;
        int clothingProducts = 0;
        int electronicProducts = 0;



        String[] cartArray = new String[3];

        defaultTableModel.setRowCount(0);
        defaultTableModel.fireTableDataChanged();

        for (Product currentProduct: shoppingCartMap.keySet()) {
//            System.out.println(product);

            if (currentProduct.getClass().toString().replace("class ", "").equals("Electronics")) {
                electronicProducts+=shoppingCartMap.get(currentProduct);                Electronics electronicProduct =
                        (Electronics) currentProduct;
                cartArray[0] = currentProduct.getProductID() + " • " + currentProduct.getProductName()
                        + " • " + electronicProduct.getBrand() + " • " + electronicProduct.getWarrantyPeriod();
            } else if (currentProduct.getClass().toString().replace("class ", "").equals("Clothing")) {
                Clothing clothingProduct = (Clothing) currentProduct;
                cartArray[0] = currentProduct.getProductID() + " • " + currentProduct.getProductName()
                        + " • " + clothingProduct.getSize() + " • " + clothingProduct.getColour();
                clothingProducts+=shoppingCartMap.get(currentProduct);
            }

//            cartArray[0] = currentProduct.getProductID() + " • " + currentProduct.getProductName()
//                    + " • " + currentProduct.getProductID() + " • " + currentProduct.getProductID() + " • ";
            cartArray[1] = String.valueOf(shoppingCartMap.get(currentProduct));
            total += currentProduct.getPrice() * shoppingCartMap.get(currentProduct);
            cartArray[2] = String.valueOf(currentProduct.getPrice() * shoppingCartMap.get(currentProduct));

//
//
//            if (productList.get(i).getClass().toString().replace("class ", "").equals(productCategory) || productCategory.equals("All")) {
////                defaultTableModel.setRowCount(0);
//                cartArray[0] = shoppingCartList.get(i).getProductID() shoppingCartList.get(i).getProductID() shoppingCartList.get(i).getProductID()
//                shoppingCartList.get(i).getProductID();

            defaultTableModel.addRow(cartArray);




        }



        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        table.setPreferredSize(new Dimension(600, 200));

        scrollPane.setPreferredSize(new Dimension(600, 200));
        topPanel.setPreferredSize(new Dimension(600, 100));
        topPanel.add(scrollPane, BorderLayout.NORTH);
        bottomPanel.setBorder(new EmptyBorder(25, 25, 10, 10));

        bottomPanel.setPreferredSize(new Dimension(600,150));
        bottomPanel.add(new JLabel("Total: " + total));
        double firstDiscount = 0.0;
        if (!userFound) {
            firstDiscount = (0.1 * total);
        }
        bottomPanel.add(new JLabel("First purchase discount (10%): -£" + String.valueOf(firstDiscount)));

        double discount20 = 0.0;
        if (clothingProducts>3 || electronicProducts>3) {
            discount20 = total * 0.2;
        }


        bottomPanel.add(new JLabel("Three items in same category discount (20%):  -£" + String.valueOf(discount20)));
        bottomPanel.add(new JLabel("Final Total: £" + String.valueOf(total-firstDiscount-discount20)));

        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));





        mainPanel.add(topPanel);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.add(mainPanel);
        this.setVisible(true);


















    }


}
