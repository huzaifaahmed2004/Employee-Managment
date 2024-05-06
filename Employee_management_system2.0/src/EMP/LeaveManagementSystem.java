package EMP;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LeaveManagementSystem extends JFrame {
    private JTable leaveTable;
    private DefaultTableModel tableModel;

    public LeaveManagementSystem() {
        initComponents();
        fetchLeaveRequests();
        setupTableListener();
    }

    private void initComponents() {
        leaveTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(leaveTable);
        add(scrollPane);
        
        setTitle("Leave Management System");
        setSize(800, 400);
        setLocationRelativeTo(null);
    }

    public void fetchLeaveRequests() {
        try {
            DBConnection db = new DBConnection();
            String q = "SELECT * FROM leavedata";
            Connection connect = db.conn;
            PreparedStatement statement = connect.prepareStatement(q);
            db.resultSet = statement.executeQuery();

            // Create table model with column names
            tableModel = new DefaultTableModel();
            tableModel.addColumn("Employee ID");
            tableModel.addColumn("Reason");
            tableModel.addColumn("Start Date");
            tableModel.addColumn("End Date");
            tableModel.addColumn("Status");

            // Populate table model with leave request data
            while (db.resultSet.next()) {
                int employeeId = db.resultSet.getInt("employee_id");
                String reason = db.resultSet.getString("reason");
                Date startDate = db.resultSet.getDate("start_date");
                Date endDate = db.resultSet.getDate("end_date");
                String status = db.resultSet.getString("status");

                tableModel.addRow(new Object[]{employeeId, reason, startDate, endDate, status});
            }

            leaveTable.setModel(tableModel);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setupTableListener() {
        leaveTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = leaveTable.getSelectedRow();
                if (selectedRow != -1) {
                    int employeeId = (int) leaveTable.getValueAt(selectedRow, 0);
                    showLeaveDetails(employeeId);
                }
            }
        });
    }

    private void showLeaveDetails(int employeeId) {
        try {
            DBConnection db = new DBConnection();
            String q = "SELECT * FROM leavedata WHERE employee_id = ?";
            Connection connect = db.conn;
            PreparedStatement statement = connect.prepareStatement(q);
            statement.setInt(1, employeeId); // Set the employee_id parameter

            db.resultSet = statement.executeQuery();

            if (db.resultSet.next()) {
                LeaveDetailsForm leaveDetailsForm = new LeaveDetailsForm(employeeId);
                leaveDetailsForm.setVisible(true);
                this.setVisible(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LeaveManagementSystem().setVisible(true);
        });
    }
}
