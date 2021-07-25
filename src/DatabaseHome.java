import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatabaseHome extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DatabaseHome frame = new DatabaseHome();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public DatabaseHome() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 100, 525, 491);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle("Database Home");
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("CONNECTION SUCCESSFUL");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 483, 54);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Driver Name");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setBounds(77, 100, 123, 30);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Driver Version:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_1.setBounds(77, 140, 123, 33);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("URL:");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_1_1.setBounds(77, 220, 123, 33);
        contentPane.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("UserName:");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_2.setBounds(77, 180, 123, 33);
        contentPane.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Database Product Name:");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_1_1_1.setBounds(77, 260, 158, 33);
        contentPane.add(lblNewLabel_1_1_1_1);

        JLabel lblNewLabel_1_1_1_2 = new JLabel("Database Product Version:");
        lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_1_1_2.setBounds(77, 300, 179, 33);
        contentPane.add(lblNewLabel_1_1_1_2);

        JLabel driver_name = new JLabel("");
        driver_name.setFont(new Font("Tahoma", Font.PLAIN, 12));
        driver_name.setBounds(249, 100, 244, 30);
        contentPane.add(driver_name);

        JLabel driver_version = new JLabel("");
        driver_version.setFont(new Font("Tahoma", Font.PLAIN, 12));
        driver_version.setBounds(249, 140, 244, 30);
        contentPane.add(driver_version);

        JLabel database_username = new JLabel("");
        database_username.setFont(new Font("Tahoma", Font.PLAIN, 12));
        database_username.setBounds(249, 180, 244, 30);
        contentPane.add(database_username);

        JLabel database_url = new JLabel("");
        database_url.setFont(new Font("Tahoma", Font.PLAIN, 12));
        database_url.setBounds(249, 220, 244, 30);
        contentPane.add(database_url);

        JLabel database_name = new JLabel("");
        database_name.setFont(new Font("Tahoma", Font.PLAIN, 12));
        database_name.setBounds(249, 260, 244, 30);
        contentPane.add(database_name);

        JLabel database_version = new JLabel("");
        database_version.setFont(new Font("Tahoma", Font.PLAIN, 12));
        database_version.setBounds(249, 300, 244, 30);
        contentPane.add(database_version);

        Login login = new Login();
        String url = login.getUrl();
        String uname = login.getUsername();
        String pass = login.getPassword();
        Connection con = DriverManager.getConnection(url,uname,pass);
        DatabaseMetaData dbmd=con.getMetaData();
        driver_name.setText(dbmd.getDriverName());
        driver_version.setText(dbmd.getDriverVersion());
        database_username.setText(dbmd.getUserName());
        database_url.setText(dbmd.getURL());
        database_name.setText(dbmd.getDatabaseProductName());
        database_version.setText(dbmd.getDatabaseProductVersion());

        JButton home_btn = new JButton("Home");
        home_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Home window = null;
                try {
                    window = new Home();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                window.frmDatabaseInfo.setVisible(true);
                dispose();
            }
        });
        home_btn.setBounds(217, 418, 89, 23);
        contentPane.add(home_btn);

        JLabel lblNewLabel_2 = new JLabel("Show list of table and data in them");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel_2.setBounds(77, 368, 206, 14);
        contentPane.add(lblNewLabel_2);

        JButton show_btn = new JButton("Show");
        show_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShowInfo showInfo = null;
                try {
                    showInfo = new ShowInfo();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                showInfo.setVisible(true);
                dispose();

            }
        });
        show_btn.setBounds(330, 365, 89, 23);
        contentPane.add(show_btn);

    }
}
