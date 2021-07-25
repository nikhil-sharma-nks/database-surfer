import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowInfo extends JFrame {

    public void displayData() throws SQLException {

        Login login = new Login();
        String url = login.getUrl();
        String uname = login.getUsername();
        String pass = login.getPassword();
        Connection con = DriverManager.getConnection(url,uname,pass);
        String query = "SELECT * FROM "+table_name;
        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
            data_table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void displayDetails(int col) throws SQLException {

        Login login = new Login();
        String url = login.getUrl();
        String uname = login.getUsername();
        String pass = login.getPassword();
        Connection con = DriverManager.getConnection(url,uname,pass);
        String query = "SELECT * FROM "+table_name;
        PreparedStatement st = con.prepareStatement(query);

        ResultSet resultSet = null;
        ResultSetMetaData rsMetaData;
        try {
            resultSet = st.executeQuery(query);
            rsMetaData = resultSet.getMetaData();
            col_number.setText(String.valueOf(col));
            col_name.setText(rsMetaData.getColumnName(col));
            col_dataType.setText(rsMetaData.getColumnTypeName(col));
            col_dataClass.setText(rsMetaData.getColumnClassName(col));
            int num = rsMetaData.isNullable(col);
            boolean b;
            if (num == 1){
                b = true;
            }
            else b = false;
            col_nullable.setText(String.valueOf(b));
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private JPanel contentPane;
    private JTable list_table;
    private JTable data_table;
    public static String table_name;
    JLabel col_number;
    JLabel col_name;
    JLabel col_dataType;
    JLabel col_dataClass;
    JLabel col_nullable;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShowInfo frame = new ShowInfo();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws SQLException
     */
    public ShowInfo() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 100, 890, 490);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setTitle("Database Data");
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Connected to database:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(295, 7, 157, 30);
        contentPane.add(lblNewLabel);

        JLabel database_url = new JLabel("Connected to database:");
        database_url.setFont(new Font("Tahoma", Font.PLAIN, 13));
        database_url.setBounds(462, 7, 232, 30);
        contentPane.add(database_url);

        JLabel lblDatabaseProduct = new JLabel("Database Product:");
        lblDatabaseProduct.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDatabaseProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatabaseProduct.setBounds(321, 37, 131, 30);
        contentPane.add(lblDatabaseProduct);

        JLabel database_pname = new JLabel("Connected to database:");
        database_pname.setFont(new Font("Tahoma", Font.PLAIN, 13));
        database_pname.setBounds(462, 37, 232, 30);
        contentPane.add(database_pname);

        JLabel lblNewLabel_2 = new JLabel("List of Tables");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(26, 77, 120, 30);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_2_1 = new JLabel("Data in selected table");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2_1.setBounds(156, 78, 476, 30);
        contentPane.add(lblNewLabel_2_1);

        JLabel lblNewLabel_1_3 = new JLabel("Column Number:");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1_3.setBounds(639, 140, 97, 30);
        contentPane.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1 = new JLabel("Column Name:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1.setBounds(639, 181, 97, 30);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Data Type:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1_1.setBounds(639, 222, 97, 30);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Data Type Class:");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1_2.setBounds(639, 263, 97, 30);
        contentPane.add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_4 = new JLabel("Nullabe:");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel_1_4.setBounds(642, 304, 89, 30);
        contentPane.add(lblNewLabel_1_4);

        col_number = new JLabel("");
        col_number.setBounds(746, 140, 118, 30);
        contentPane.add(col_number);

        col_name = new JLabel("");
        col_name.setBounds(746, 181, 118, 30);
        contentPane.add(col_name);

        col_dataType = new JLabel("");
        col_dataType.setBounds(746, 222, 118, 30);
        contentPane.add(col_dataType);

        col_dataClass = new JLabel("");
        col_dataClass.setBounds(746, 263, 118, 30);
        contentPane.add(col_dataClass);

        col_nullable = new JLabel("");
        col_nullable.setBounds(746, 304, 118, 30);
        contentPane.add(col_nullable);

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
        home_btn.setBounds(469, 417, 89, 23);
        contentPane.add(home_btn);

        Login login = new Login();
        String url = login.getUrl();
        String uname = login.getUsername();
        String pass = login.getPassword();
        Connection con = DriverManager.getConnection(url,uname,pass);

        try {

            DatabaseMetaData dbmd=con.getMetaData();
            database_url.setText(dbmd.getURL());
            database_pname.setText(dbmd.getDatabaseProductName());

            JButton back_btn = new JButton("Back");
            back_btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DatabaseHome databaseHome = null;
                    try {
                        databaseHome = new DatabaseHome();
                        databaseHome.setVisible(true);
                        dispose();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            back_btn.setBounds(190, 417, 89, 23);
            contentPane.add(back_btn);

            JScrollPane list_scroll = new JScrollPane();
            list_scroll.setBounds(26, 113, 120, 246);
            contentPane.add(list_scroll);

            list_table = new JTable();
            list_table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = list_table.getSelectedRow();
                    table_name = (list_table.getModel().getValueAt(row,0).toString());
                    System.out.println(table_name);
                    try {
                        displayData();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            });

            DefaultTableModel tableModel = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //all cells false
                    return false;
                }
            };

            list_table.setModel(tableModel);

            list_table.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                            "Table Name"
                    }
            ) {
                boolean[] columnEditables = new boolean[] {
                        false
                };
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            list_scroll.setViewportView(list_table);

            JScrollPane data_scroll = new JScrollPane();
            data_scroll.setBounds(156, 113, 476, 246);
            contentPane.add(data_scroll);

            data_table = new JTable();
            data_table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int col = data_table.getSelectedColumn();
                    col++;
                    try {
                        displayDetails(col);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            });
            data_scroll.setViewportView(data_table);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String show_tables = "Show tables";
        Statement st = con.createStatement();
        //Retrieving the data
        ResultSet rs = null;
        ResultSet resultSet = null;
        try {
            rs = st.executeQuery(show_tables);
            list_table.setModel(DbUtils.resultSetToTableModel(rs));
//            resultSet = st.executeQuery("SELECT table_name FROM user_tables order by table_name");
//            list_table.setModel(DbUtils.resultSetToTableModel(resultSet));

            JLabel lblNewLabel_2_2 = new JLabel("Column Info");
            lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
            lblNewLabel_2_2.setBounds(635, 77, 221, 30);
            contentPane.add(lblNewLabel_2_2);

        }
        catch (Exception throwables ) {
            throwables.printStackTrace();
        }

        revalidate();
    }
}
