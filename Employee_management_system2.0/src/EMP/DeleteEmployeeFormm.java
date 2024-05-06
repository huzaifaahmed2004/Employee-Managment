package EMP;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DeleteEmployeeFormm extends javax.swing.JFrame {

    private final DBConnection db;
    private final Connection connection;
    private final DefaultTableModel tableModel;

    public DeleteEmployeeFormm() {
        initComponents();
        db = new DBConnection();
        connection = db.conn;
        tableModel = (DefaultTableModel) employeeTable.getModel();
        fetchEmployeeData();
        customizeTable();
        centerFrame();
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Father Name", "Age", "DOB", "Address", "Phone", "Email", "Education", "Job Post", "CNIC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(employeeTable);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) {
            int selectedId = (int) employeeTable.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteEmployee(selectedId);
                deleteEmployeeFromTable(selectedRow);
                this.setVisible(false);
                JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
        }
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void fetchEmployeeData() {
        try {
            String query = "SELECT * FROM empdata";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String username = resultSet.getString("Username");
                String fname = resultSet.getString("Fname");
                int age = resultSet.getInt("Age");
                String dob = resultSet.getString("DOB");
                String address = resultSet.getString("Address");
                String phone = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                String education = resultSet.getString("Education");
                String jobPost = resultSet.getString("JobPost");
                String cnic = resultSet.getString("CNIC");

                tableModel.addRow(new Object[]{id, username, fname, age, dob, address, phone, email, education, jobPost, cnic});
            }

            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to fetch employee data: " + e.getMessage());
        }
    }

    private void deleteEmployee(int employeeId) {
        try {
            String query = "DELETE FROM attendata WHERE employee_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            statement.executeUpdate();
            statement.close();

            query = "DELETE FROM salary WHERE ID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            statement.executeUpdate();
            statement.close();

            query = "DELETE FROM leavedata WHERE employee_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            statement.executeUpdate();
            statement.close();

            query = "DELETE FROM empdata WHERE ID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, employeeId);
            statement.executeUpdate();
            statement.close();

            deleteEmployeeFromTable(employeeTable.getSelectedRow());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to delete employee: " + e.getMessage());
        }
    }

    private void deleteEmployeeFromTable(int rowIndex) {
    if (tableModel.getRowCount() > 0 && rowIndex >= 0 && rowIndex < tableModel.getRowCount()) {
        tableModel.removeRow(rowIndex);
    }
}

    private void customizeTable() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        for (int i = 0; i < employeeTable.getColumnCount(); i++) {
            TableColumn column = employeeTable.getColumnModel().getColumn(i);
            column.setCellRenderer(centerRenderer);
            if (i == 0|| i == 3 ) {
                column.setPreferredWidth(30); // ID column width
            } else if (i == 1 || i == 2 || i == 5 || i == 10) {
                column.setPreferredWidth(90); // Aggress, Name, Father Name, CNIC column width}
            }else if ( i == 7) {
                column.setPreferredWidth(120); // Aggress, Name, Father Name, Email column width
            } else {
                column.setPreferredWidth(80); // Other columns width
            }
        }
    }

    private void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        setLocation(x, y);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteEmployeeFormm().setVisible(true);
            }
        });
    }

    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTable employeeTable;
    private javax.swing.JScrollPane jScrollPane1;

}
