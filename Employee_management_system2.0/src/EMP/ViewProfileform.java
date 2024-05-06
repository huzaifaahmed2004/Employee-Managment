/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMP;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Huzaifa Ahmed
 */
public class ViewProfileform extends javax.swing.JFrame {

    /**
     * Creates new form ViewProfileform
     */
    public ViewProfileform() {
        initComponents();
        
            getTableData();
            adjustColumnWidths();
    }
DBConnection db=new DBConnection();
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        Back_btn = new javax.swing.JButton();

        setTitle("Employees Profile");
        setResizable(false);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setEnabled(false);
        jScrollPane1.setViewportView(table);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        Back_btn.setBackground(new java.awt.Color(255, 255, 204));
        Back_btn.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Back_btn.setText("Cancel");
        Back_btn.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(255, 0, 0)));
        Back_btn.setContentAreaFilled(false);
        Back_btn.setFocusPainted(false);
        Back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(559, 559, 559)
                .addComponent(Back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(604, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_btnActionPerformed
         this.setVisible(false);
    }//GEN-LAST:event_Back_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
/* Set the Nimbus look and feel */

                //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewProfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProfileform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewProfileform().setVisible(true);
            }
        });
    }
public void getTableData() {
    DefaultTableModel tableModel = new DefaultTableModel();

    try {
        String query = "SELECT * FROM empdata";
        db.resultSet = db.stm.executeQuery(query);

        // Create the column headers
        tableModel.addColumn("ID");  // Moved ID column to the beginning
        tableModel.addColumn("Username");
        tableModel.addColumn("Password");
        tableModel.addColumn("Father Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("DOB");
        tableModel.addColumn("Address");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");
        tableModel.addColumn("Education");
        tableModel.addColumn("Job Post");
        tableModel.addColumn("CNIC");

        // Populate the table with data
        while (db.resultSet.next()) {
            Object[] rowData = new Object[12];
            rowData[0] = db.resultSet.getString("ID");  // Moved ID column to the beginning
            rowData[1] = db.resultSet.getString("username");
            rowData[2] = db.resultSet.getString("password");
            rowData[3] = db.resultSet.getString("Fname");
            rowData[4] = db.resultSet.getString("age");
            rowData[5] = db.resultSet.getString("DOB");
            rowData[6] = db.resultSet.getString("address");
            rowData[7] = db.resultSet.getString("phone");
            rowData[8] = db.resultSet.getString("email");
            rowData[9] = db.resultSet.getString("Education");
            rowData[10] = db.resultSet.getString("JobPost");
            rowData[11] = db.resultSet.getString("CNIC");

            tableModel.addRow(rowData);
        }

        db.resultSet.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    table.setModel(tableModel);
}

private void adjustColumnWidths() {
    // Set column widths
    TableColumn idColumn = table.getColumnModel().getColumn(0);
    idColumn.setPreferredWidth(30);

    TableColumn usernameColumn = table.getColumnModel().getColumn(1);
    usernameColumn.setPreferredWidth(100);

    TableColumn passwordColumn = table.getColumnModel().getColumn(2);
    passwordColumn.setPreferredWidth(80);

    TableColumn fnameColumn = table.getColumnModel().getColumn(3);
    fnameColumn.setPreferredWidth(100);

    TableColumn ageColumn = table.getColumnModel().getColumn(4);
    ageColumn.setPreferredWidth(30);

    TableColumn dobColumn = table.getColumnModel().getColumn(5);
    dobColumn.setPreferredWidth(100);

    TableColumn addressColumn = table.getColumnModel().getColumn(6);
    addressColumn.setPreferredWidth(180);

    TableColumn phoneColumn = table.getColumnModel().getColumn(7);
    phoneColumn.setPreferredWidth(100);

    TableColumn emailColumn = table.getColumnModel().getColumn(8);
    emailColumn.setPreferredWidth(180);

    TableColumn educationColumn = table.getColumnModel().getColumn(9);
    educationColumn.setPreferredWidth(100);

    TableColumn jobPostColumn = table.getColumnModel().getColumn(10);
    jobPostColumn.setPreferredWidth(60);

    TableColumn cnicColumn = table.getColumnModel().getColumn(11);
    cnicColumn.setPreferredWidth(120);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back_btn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
