package EMP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LeaveDetailsForm extends JFrame {
    private final int Id;
    private JLabel lblReason;
    private JLabel lblStartDate;
    private JLabel lblEndDate;
    private JComboBox<String> statusComboBox;

    public LeaveDetailsForm(int employeeId) {
        this.Id = employeeId;
        initComponents();
        fetchLeaveDetails();
    }

    private void initComponents() {
        lblReason = new JLabel();
        lblStartDate = new JLabel();
        lblEndDate = new JLabel();
        statusComboBox = new JComboBox<>(new String[]{"Pending", "Approved", "Rejected"});
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Reason:"));
        panel.add(lblReason);
        panel.add(new JLabel("Start Date:"));
        panel.add(lblStartDate);
        panel.add(new JLabel("End Date:"));
        panel.add(lblEndDate);
        panel.add(new JLabel("Status:"));
        panel.add(statusComboBox);
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        add(buttonPanel, BorderLayout.PAGE_END);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newStatus = (String) statusComboBox.getSelectedItem();
                updateLeaveStatus(Id, newStatus);
                new LeaveManagementSystem().setVisible(true);
                dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this leave request?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteLeaveRequest(Id);
                    new LeaveManagementSystem().setVisible(true);
                    dispose();
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Leave Details");
        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    private void fetchLeaveDetails() {
        try {
            DBConnection db = new DBConnection();
            String q = "SELECT * FROM leavedata WHERE employee_id = ?";
            Connection connect = db.conn;
            PreparedStatement statement = connect.prepareStatement(q);
            statement.setInt(1, Id);
            db.resultSet = statement.executeQuery();

            if (db.resultSet.next()) {
                String reason = db.resultSet.getString("reason");
                Date startDate = db.resultSet.getDate("start_date");
                Date endDate = db.resultSet.getDate("end_date");
                String status = db.resultSet.getString("status");

                lblReason.setText(reason);
                lblStartDate.setText(startDate.toString());
                lblEndDate.setText(endDate.toString());
                statusComboBox.setSelectedItem(status);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateLeaveStatus(int employeeId, String newStatus) {
        try {
            DBConnection db = new DBConnection();
            String q = "UPDATE leavedata SET status = ? WHERE employee_id = ?";
            Connection connect = db.conn;
            PreparedStatement stmt = connect.prepareStatement(q);

            stmt.setString(1, newStatus);
            stmt.setInt(2, employeeId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Leave status updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update leave status.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteLeaveRequest(int employeeId) {
        try {
            DBConnection db = new DBConnection();
            String q = "DELETE FROM leavedata WHERE employee_id = ?";
            Connection connect = db.conn;
            PreparedStatement stmt = connect.prepareStatement(q);

            stmt.setInt(1, employeeId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Leave request deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete leave request.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
