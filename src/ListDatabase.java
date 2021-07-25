import net.proteanit.sql.DbUtils;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JPasswordField;

public class ListDatabase extends JFrame {

    private JPanel contentPane;
    private JTable listDatabase_table;
    private JTable table;
    public static String table_name;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ListDatabase frame = new ListDatabase();
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
    public ListDatabase() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 100, 463, 453);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle("All Databases");
        contentPane.setLayout(null);


        JLabel lblNewLabel = new JLabel("Following are list of Database present");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 427, 48);
        contentPane.add(lblNewLabel);

        Login login = new Login();
//      String url = login.getUrl();
        String uname = login.getUsername();
        String pass = login.getPassword();
        String url = "jdbc:mysql://localhost:3306/";

        Connection con = DriverManager.getConnection(url, uname, pass);
        Statement stmt = con.createStatement();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(104, 94, 234, 214);
        contentPane.add(scrollPane);

        table = new JTable();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("show databases");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                table_name = (table.getModel().getValueAt(row,0).toString());
                String url = "jdbc:mysql://localhost:3306/"+table_name;
                System.out.println(table_name);
                System.out.println(url);
                login.setUrl(url);

            }
        });
        scrollPane.setViewportView(table);
        revalidate();



        JButton connect_button = new JButton("Connect");
        connect_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DatabaseHome databaseHome = null;
                try {
                    databaseHome = new DatabaseHome();
                    databaseHome.setVisible(true);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dispose();

            }
        });
        connect_button.setBounds(267, 335, 89, 23);
        contentPane.add(connect_button);

        JButton btnNewButton = new JButton("Home");
        btnNewButton.addActionListener(new ActionListener() {
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
        btnNewButton.setBounds(89, 335, 89, 23);
        contentPane.add(btnNewButton);
        revalidate();
    }
}
