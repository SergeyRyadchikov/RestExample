import com.lesson9.server.API.*;
import com.lesson9.server.model.RequestBodyCreateCustomer;
import com.lesson9.server.model.RequestBodyPut;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class PhoneBookDesktopClient extends JFrame {
    private JTable tblCustomers;
    private JButton btnRefresh;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JTextField txtName;
    private JTextField txtID;
    private JButton btnAdd;
    private JButton btnSearch;
    private JButton btnChange;
    private JButton btnRemove;
    private JPanel phoneBook;
    private JLabel labelResult;
    private JTextArea textArea1;
    private JScrollBar scrollBar1;
    private JLabel lableResult;

    public PhoneBookDesktopClient() {
        setContentPane(phoneBook);
        setTitle("Телефонная книга");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = txtName.getText();
                String email = txtEmail.getText();
                String phone = txtPhone.getText();

                try {
                    String result = new RequestCreateCustomer().sendRequest(new RequestBodyCreateCustomer(
                            name,
                            email,
                            phone
                    ));

                    labelResult.setText("Created");


                } catch (URISyntaxException | IOException | InterruptedException ex ) {
                    System.out.println(ex.getMessage());
                }
            }
        });


        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtID.getText());
                try {
                    String[] result = new RequestSearch(id).sendRequest().split(",");
                    String text = String.format("%-15s %-15s %-15s %-15s\n", result[0], result[1], result[2], result[3]);
                    textArea1.setText(text);
                } catch (URISyntaxException | IOException | InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });


        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtID.getText());
                String name = txtName.getText();
                String email = txtEmail.getText();
                String phone = txtPhone.getText();
                try {
                    String result = new RequestChange(id).sendRequest(new RequestBodyPut(
                            id,
                            name,
                            email,
                            phone
                    ));
                    textArea1.setText(result);
                    labelResult.setText("Changed");

                } catch (URISyntaxException | IOException | InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtID.getText());
                try {
                    String result = new RequestDelete(id).sendRequest();
                    labelResult.setText("Deleted");
                } catch (URISyntaxException | IOException | InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea1.setText("");
                    String[] result = new RequestGetAll().sendRequest().split("},");
                    for (String item : result){
                        textArea1.append(item + "\n");
                    }

                } catch (URISyntaxException | IOException | InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new PhoneBookDesktopClient();
    }
}
