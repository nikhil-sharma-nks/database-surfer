import java.awt.EventQueue;
import java.sql.*;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home {

    public JFrame frmDatabaseInfo;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField url_tf;
    private JTextField name_tf;
    private JTextField pass_tf;
    public static String database = "";


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home window = new Home();
                    window.frmDatabaseInfo.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Home() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmDatabaseInfo = new JFrame();
        frmDatabaseInfo.setTitle("DataBase Info");
        frmDatabaseInfo.setBounds(450, 100, 503, 456);
        frmDatabaseInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmDatabaseInfo.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("DataBase Information Software");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 21, 467, 56);
        frmDatabaseInfo.getContentPane().add(lblNewLabel);

        JLabel label_url = new JLabel("URL");
        label_url.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_url.setHorizontalAlignment(SwingConstants.LEFT);
        label_url.setBounds(74, 185, 86, 34);
        frmDatabaseInfo.getContentPane().add(label_url);

        JLabel label_name = new JLabel("User Name");
        label_name.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_name.setHorizontalAlignment(SwingConstants.LEFT);
        label_name.setBounds(74, 243, 86, 34);
        frmDatabaseInfo.getContentPane().add(label_name);


        JLabel label_pass = new JLabel("Password");
        label_pass.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_pass.setHorizontalAlignment(SwingConstants.LEFT);
        label_pass.setBounds(74, 299, 86, 34);
        frmDatabaseInfo.getContentPane().add(label_pass);

        JLabel lblNewLabel_2 = new JLabel("Select Type of database to connect to");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(74, 109, 246, 24);
        frmDatabaseInfo.getContentPane().add(lblNewLabel_2);

        url_tf = new JTextField();
        url_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
        url_tf.setBounds(184, 189, 234, 27);
        frmDatabaseInfo.getContentPane().add(url_tf);
        url_tf.setColumns(10);

        name_tf = new JTextField();
        name_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
        name_tf.setColumns(10);
        name_tf.setBounds(184, 247, 234, 27);
        frmDatabaseInfo.getContentPane().add(name_tf);

        pass_tf = new JTextField();
        pass_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pass_tf.setColumns(10);
        pass_tf.setBounds(184, 303, 234, 27);
        frmDatabaseInfo.getContentPane().add(pass_tf);

        String company[]={"MySql", "Oracle"};
        JComboBox comboBox = new JComboBox(company);
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"MySql", "Oracle"}));
        database = String.valueOf(comboBox.getItemAt(0));
        url_tf.setText("jdbc:mysql://localhost:3306/");
        name_tf.setText("root");
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                database = String.valueOf(comboBox.getItemAt(comboBox.getSelectedIndex()));
                if (database=="MySql"){
                    url_tf.setText("jdbc:mysql://localhost:3306/");
                    name_tf.setText("root");

                }
                else if (database=="Oracle"){
                    url_tf.setText("jdbc:oracle:thin:@localhost:1521:");
                    name_tf.setText("system");

                }

            }
        });


        comboBox.setBounds(338, 104, 80, 34);
        frmDatabaseInfo.getContentPane().add(comboBox);

        JButton show_btn = new JButton("Show All");
        show_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                String url = url_tf.getText();
                String uname = name_tf.getText();
                String pass = pass_tf.getText();
                JFrame f = new JFrame();
                try {
                    Connection con = DriverManager.getConnection(url,uname,pass);
                    System.out.println("Connection made");
                    login.setUrl(url);
                    login.setPassword(pass);
                    login.setUsername(uname);

                    ListDatabase listDatabase = null;
                    try {
                        listDatabase = new ListDatabase();
                        listDatabase.setVisible(true);
                        frmDatabaseInfo.dispose();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

        }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                }});
        show_btn.setBounds(288, 364, 89, 34);
        frmDatabaseInfo.getContentPane().add(show_btn);


        JButton connect_btn = new JButton("Connect");
        connect_btn.setBounds(112, 364, 89, 34);
        frmDatabaseInfo.getContentPane().add(connect_btn);
        connect_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                String url = url_tf.getText();
                String uname = name_tf.getText();
                String pass = pass_tf.getText();
                JFrame f = new JFrame();
                try {
                    Connection con = DriverManager.getConnection(url,uname,pass);
                    System.out.println("Connection made");
                    login.setUrl(url);
                    login.setPassword(pass);
                    login.setUsername(uname);

                    DatabaseHome databaseHome = null;
                    try {
                        databaseHome = new DatabaseHome();
                        databaseHome.setVisible(true);
                        frmDatabaseInfo.dispose();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(f, "Connection successful");

                } catch (SQLException exp) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(f, "Connection failed");
                    exp.printStackTrace();
                }

            }
        });

    }
}
