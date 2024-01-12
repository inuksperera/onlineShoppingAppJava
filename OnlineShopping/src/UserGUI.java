import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class UserGUI extends JFrame implements ActionListener {
    private JButton submitBtn;
    private JTextField username;
    private JTextField password;
    private boolean userFound = false;


    UserGUI() {
        this.setTitle("User Login");
        this.setSize(650,450);



        JPanel mainPanel = new JPanel();
        mainPanel.add(new JLabel ("Username"));
        username = new JTextField(8);
        mainPanel.add(username);
        mainPanel.add(new JLabel ("Password"));
        password = new JTextField(8);
        mainPanel.add(password);
        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(this);
        mainPanel.add(submitBtn);



        System.out.println(username.getText() + password.getText());
        this.add(mainPanel);
        this.setVisible(true);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn){
            try {
                File file = new File("C:\\Users\\INUKA PERERA\\Documents\\Files\\IIT\\OOP\\cw\\OnlineShopping",
                        "users.txt");
                FileWriter writer = new FileWriter(file, true);


                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);


                String line = bufferedReader.readLine();
                while(line != null && !userFound) {
                    if (line.contains(username.getText())){
                        userFound = true;
                    }
                    line = bufferedReader.readLine();

                }


                if (userFound) {
                    System.out.println("userFound");
                } else {
                    System.out.println("new user");
                    User user = new User(username.getText(), password.getText());
                    writer.write(user.toString());
                }


            writer.close();
            reader.close();
            bufferedReader.close();

            }catch (Exception err){
                System.out.println(err);
        }

    }
                ShoppingGUI shoppingGUI = new ShoppingGUI(userFound);


    }
}


