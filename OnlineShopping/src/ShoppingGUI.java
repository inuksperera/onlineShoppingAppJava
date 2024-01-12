import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ShoppingGUI extends JFrame implements ActionListener, ListSelectionListener{


    private ShoppingManager shoppingManager = new WestminsterShoppingManager();
    private ArrayList<Product> productList = shoppingManager.productList;
    private JComboBox<String> itemTypeList;
    private JTable table;
    private DefaultTableModel defaultTableModel;
    private JLabel productID, productCategory, productName, productInfo1, productInfo2, productAvailability, topicLabel
            , jlabel;
    private HashMap<Product, Integer> shoppingCartMap = new HashMap<Product, Integer>();
    ;
    private JButton addToCartBtn;
    private Product currentProduct;
    private JButton cartBtn;
    private JLabel initialLabel;
    private boolean userFound;

    //REMOVE THIS CUHHHH
//    public static void main(String[] args) {
//        ShoppingGUI gui = new ShoppingGUI();
//
//    }

    ShoppingGUI(boolean userFound) {


        this.userFound = userFound;
        this.setTitle("Westminster Shopping");
        this.setSize(900,720);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH );


        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(195, 191, 255));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255, 225, 195));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        topPanel.add(new Label("Select Product Category"));

        itemTypeList = new JComboBox<>();
        itemTypeList.addItem("All");
        itemTypeList.addItem("Electronics");
        itemTypeList.addItem("Clothing");
        itemTypeList.addActionListener(this);
        topPanel.add(itemTypeList);


        cartBtn = new JButton("Shopping Cart");
        cartBtn.addActionListener(this);
        topPanel.add(cartBtn);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(217, 255, 191));
        mainPanel.add(centerPanel, BorderLayout.CENTER);


        String[] columns = new String[]{"productid", "name", "category", "price", "info"};
        defaultTableModel = new DefaultTableModel(columns, 0);
        table = new JTable(defaultTableModel){
            public boolean isCellEditable(int nRow, int nCol) {
                return false;
            }
        };
        table.getSelectionModel().addListSelectionListener(this);
        tableFormatting("All");
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        table.setPreferredSize(new Dimension(800, 300));

        scrollPane.setPreferredSize(new Dimension(800, 300));


        centerPanel.add(scrollPane);


        bottomPanel.setPreferredSize(new Dimension(800, 300));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(new EmptyBorder(25, 50, 10, 10));

        topicLabel = new JLabel("Selected product details: ");
        jlabel = new JLabel("");
        initialLabel = new JLabel("No product selected.");

        productID = new JLabel();
        productCategory = new JLabel();
        productName = new JLabel();
        productInfo1 = new JLabel();
        productInfo2 = new JLabel();
        productAvailability = new JLabel();

        bottomPanel.add(topicLabel);
        bottomPanel.add(jlabel);
        bottomPanel.add(initialLabel);
        bottomPanel.add(productID);
        bottomPanel.add(productCategory);
        bottomPanel.add(productName);
        bottomPanel.add(productInfo1);
        bottomPanel.add(productInfo2);
        bottomPanel.add(productAvailability);

        addToCartBtn = new JButton("Add to Shopping Cart");
        addToCartBtn.setPreferredSize(new Dimension(40,30));
        addToCartBtn.addActionListener(this);
        bottomPanel.add(addToCartBtn);


        this.add(mainPanel);
        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void tableFormatting(String productCategory){

        Collections.sort(productList);
        String[] productsArray = new String[5];

        defaultTableModel.setRowCount(0);
        defaultTableModel.fireTableDataChanged();

        for (int i=0;i<productList.size();i++) {
//            System.out.println(product);
                productsArray[0] = productList.get(i).productID;
                productsArray[1] = productList.get(i).productName;
                productsArray[2] = productList.get(i).getClass().toString().replace("class ", "");
                productsArray[3] = String.valueOf(productList.get(i).price);

                if (productList.get(i).getClass().toString().replace("class ", "").equals("Electronics")) {
                    Electronics electronicProduct = (Electronics) productList.get(i);
                    productsArray[4] = electronicProduct.getBrand() + ", " + electronicProduct.getWarrantyPeriod() + " " +
                            "weeks warranty";


                } else if (productList.get(i).getClass().toString().replace("class ", "").equals("Clothing")) {
                    Clothing clothingProduct = (Clothing) productList.get(i);
                    productsArray[4] = clothingProduct.getSize() + ", " + clothingProduct.getColour();
                }




//                productsArray[4] = productList.get(i).;


            if (productList.get(i).getClass().toString().replace("class ", "").equals(productCategory) || productCategory.equals("All")) {
//                defaultTableModel.setRowCount(0);
                defaultTableModel.addRow(productsArray);
//                System.out.println(itemTypeList.getSelectedItem());
//                System.out.println(productCategory);
//                System.out.println(productList.get(i).getClass().toString().replace("class ", ""));

//                System.out.println(productList.get(i).getClass().toString().replace("class ", ""));


            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemTypeList) {

            tableFormatting(itemTypeList.getSelectedItem().toString());
        }
        if(e.getSource() == addToCartBtn){


            if (shoppingCartMap.containsKey(currentProduct)) {
                shoppingCartMap.put(currentProduct, shoppingCartMap.get(currentProduct) + 1);

            }else{
                shoppingCartMap.put(currentProduct, 1);

            }




//            shoppingCartList.add(currentProduct);
        }
        if(e.getSource() == cartBtn){

            ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI(shoppingCartMap, userFound);
        }

    }
//
//    private void showShoppingCart() {
//
//
//    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
//            System.out.println(table.getSelectedRow());
//            System.out.println(productList.get(table.getSelectedRow()));
            int selectedRow = table.getSelectedRow();
            currentProduct = productList.get(selectedRow);

            initialLabel.setVisible(false);
            jlabel.setText("");
            productID.setText("Product ID: " + currentProduct.getProductID());
            productCategory.setText("Category: " + currentProduct.getClass().toString().replace("class ", ""));
            productName.setText("Name: " + currentProduct.getProductName());

            if (currentProduct.getClass().toString().replace("class ", "").equals("Electronics")) {
                Electronics electronicProduct = (Electronics) currentProduct;
                productInfo1.setText("Brand: " + electronicProduct.getBrand());
                productInfo2.setText("Warranty Period: " + String.valueOf(electronicProduct.getWarrantyPeriod()));

            } else if (currentProduct.getClass().toString().replace("class ", "").equals("Clothing")) {
                Clothing clothingProduct = (Clothing) currentProduct;
                productInfo1.setText("Size: " + clothingProduct.getSize());
                productInfo2.setText("Colour: " + clothingProduct.getColour());
            }
            productAvailability.setText("Items Available: " + currentProduct.getAvailableNumber());

        }
    }
    //http://www.java2s.com/Tutorial/Java/0240__Swing/ListeningtoJListEventswithaListSelectionListener.htm
    //https://stackoverflow.com/questions/12975460/listselectionlistener-invoked-twice
    //https://www.w3schools.com/java/java_hashmap.asp
    //https://stackoverflow.com/questions/4157972/how-to-update-a-value-given-a-key-in-a-hashmap



}
