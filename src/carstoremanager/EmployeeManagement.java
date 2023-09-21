/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package carstoremanager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class EmployeeManagement extends javax.swing.JFrame {
// class nhân viên
    DefaultTableModel tblModel;
    List<Nhan_Vien> list = new ArrayList<>();
    private int index = 1;
    private static final String P_EMAIL = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)$";

    /**
     * Creates new form ASM
     */
    public EmployeeManagement() {
        initComponents();
         setSize(600,600);

        setLocationRelativeTo(null);
        setTitle("Ninh_PC05855");
       
    }

    public void initTable() {
        tblModel = (DefaultTableModel) tblNhanVien.getModel();
        String[] cosl = new String[]{"Mã", "Họ Và Tên", "Tuổi","SĐT", "Lương", "giới tính"};
        tblModel.setColumnIdentifiers(cosl);
    }

    public void fillToTable() {
        tblModel.setRowCount(0);
        for (Nhan_Vien nv : list) {
            System.out.println(nv.getMa());
            Object[] row = new Object[]{nv.getMa(), nv.getHoten(), nv.getTuoi(), nv.getEmail(), nv.getLuong()};
            tblModel.addRow(row);
        }
    }

    public void clearFrom() {
        txtHoTen.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
        txtMaNV.setText("");
        txtTuoi.setText("");

    }

    public Nhan_Vien readFrom() {
        return new Nhan_Vien(txtMaNV.getText(), txtHoTen.getText(), txtEmail.getText(), Double.parseDouble(txtLuong.getText()), Integer.parseInt(txtTuoi.getText()));
    }

    public void themNV() {
        if (validateForm()) {
            if (index == -1) {
                list.add(readFrom());
                fillToTable();
                JOptionPane.showMessageDialog(this, "Da Them");
            } else {
                capNhat(readFrom());
                fillToTable();
                JOptionPane.showMessageDialog(this, "Da Cap Nhat");
            }
        }
    }

    public Nhan_Vien timTheoMa(String ID) {
        for (Nhan_Vien nv : list) {
            if (nv.getMa().equalsIgnoreCase(ID)) {
                return nv;
            }
        }
        return null;
    }

    public void capNhat(Nhan_Vien newnv) {
        if (validateForm()) {
            Nhan_Vien nv1 = timTheoMa(newnv.getMa());
            if (nv1 != null) {
                nv1.setMa(newnv.getMa());
                nv1.setTuoi(newnv.getTuoi());
                nv1.setEmail(newnv.getEmail());
                nv1.setLuong(newnv.getLuong());
            }
        }
    }

    public String layThongTinBangGhi() {
        return "Record:" + (index + 1) + "of" + list.size();
    }

    public void fillNhanVienLenFrom(int index) {
        txtMaNV.setText(list.get(index).getMa());
        txtHoTen.setText(list.get(index).getHoten());
        txtEmail.setText(list.get(index).getEmail());
        txtTuoi.setText(String.valueOf(list.get(index).getTuoi()));
        txtLuong.setText(String.valueOf(list.get(index).getLuong()));

    }

    public void fillNhanVienLenFrom(Nhan_Vien nv) {
        txtMaNV.setText(nv.getMa());
        txtHoTen.setText(nv.getHoten());
        txtEmail.setText(nv.getEmail());
        txtTuoi.setText(String.valueOf(nv.getTuoi()));
        txtLuong.setText(String.valueOf(nv.getLuong()));

    }
//new nhân viên
    public void XoaNV() {
        list.remove(index);
        fillToTable();
        clearFrom();
        JOptionPane.showMessageDialog(this, "Da Xoa");
    }

    public void fistNV() {
        if (list.size() != 0) {
            index = 0;
            updateInfo();
        }
    }

    public void preNV() {
        if (list.size() != 0) {
            if (index == 0) {
                lastNV();
            } else {
                index--;
            }

            updateInfo();
        }
    }

    public void nextNV() {
        if (list.size() != 0) {
            if (index == list.size() - 1) {
                fistNV();
            } else {
                index++;
            }

            updateInfo();
        }
    }

    public void lastNV() {
        if (list.size() != 0) {
            index = list.size() - 1;
            updateInfo();
        }
    }

    private void updateInfo() {
        tblNhanVien.setRowSelectionInterval(index, index);
        fillNhanVienLenFrom(index);
        lblBangGhi.setText(layThongTinBangGhi());
    }

    public boolean validateForm() {
        if (txtMaNV.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Chau Nhap Ma Nhan Vien");
            return false;
        }
        if (txtHoTen.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Chau Nhap Ho Ten Nhan Vien");
            return false;
        }
        if (txtTuoi.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Chau Nhap Tuoi Nhan Vien");
            return false;
        }
        try {
            Integer.parseInt(txtTuoi.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "tuoi phai la so");
            return false;
        }

        if (txtEmail.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Chua Nhap Email Nhan Vien");
            return false;
        }
        Matcher matcher = Pattern.compile(P_EMAIL).matcher(txtEmail.getText());
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Email chua dung dinh dang");
            return false;
        }
        if (txtLuong.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Chau Nhap Luong  Nhan Vien");
            return false;
        }
        try {
            Double.parseDouble(txtLuong.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "luong phai la so", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void readFive() {
        try {
            list = (List<Nhan_Vien>) XFile.readObj("listNV.dat");
            fillToTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void savaFive() {
        try {
            XFile.writeObj("listNV.dat", list);
            JOptionPane.showMessageDialog(this, "Saved");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtMaNV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtTuoi = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtLuong = new javax.swing.JTextField();
        btnFist = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblBangGhi = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDetele = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(102, 153, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Quản Lý Nhân Viên");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 10, 227, 27);

        jLabel2.setBackground(new java.awt.Color(251, 175, 175));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setText("Mã Nhân Viên ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(6, 77, 90, 16);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("Họ Và Tên ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(6, 117, 78, 16);

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 255));
        jLabel4.setText("Tuổi");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(6, 157, 52, 17);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 255));
        jLabel5.setText("SDT");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(6, 191, 52, 16);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 255));
        jLabel6.setText("Lương");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(6, 225, 37, 16);

        tblNhanVien.setForeground(new java.awt.Color(0, 102, 255));
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(0).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(1).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 470, 527, 120);
        getContentPane().add(txtMaNV);
        txtMaNV.setBounds(110, 70, 210, 30);
        getContentPane().add(txtHoTen);
        txtHoTen.setBounds(110, 110, 213, 30);
        getContentPane().add(txtTuoi);
        txtTuoi.setBounds(110, 150, 210, 30);
        getContentPane().add(txtEmail);
        txtEmail.setBounds(110, 190, 210, 30);
        getContentPane().add(txtLuong);
        txtLuong.setBounds(110, 230, 210, 30);

        btnFist.setBackground(new java.awt.Color(51, 102, 255));
        btnFist.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFist.setForeground(new java.awt.Color(255, 102, 51));
        btnFist.setText("|<");
        btnFist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFistActionPerformed(evt);
            }
        });
        getContentPane().add(btnFist);
        btnFist.setBounds(100, 410, 48, 23);

        btnPre.setBackground(new java.awt.Color(0, 102, 255));
        btnPre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPre.setForeground(new java.awt.Color(255, 102, 51));
        btnPre.setText("<<");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        getContentPane().add(btnPre);
        btnPre.setBounds(160, 410, 48, 23);

        btnNext.setBackground(new java.awt.Color(0, 102, 255));
        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 102, 51));
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        getContentPane().add(btnNext);
        btnNext.setBounds(220, 410, 48, 23);

        btnLast.setBackground(new java.awt.Color(0, 102, 255));
        btnLast.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 102, 51));
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        getContentPane().add(btnLast);
        btnLast.setBounds(280, 410, 47, 23);

        lblBangGhi.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        lblBangGhi.setForeground(new java.awt.Color(255, 51, 51));
        lblBangGhi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBangGhi.setText("Record 1 of 10");
        getContentPane().add(lblBangGhi);
        lblBangGhi.setBounds(340, 410, 88, 23);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel1.setForeground(new java.awt.Color(255, 102, 51));
        jPanel1.setLayout(null);

        btnNew.setBackground(new java.awt.Color(114, 151, 206));
        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 102, 51));
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNew);
        btnNew.setBounds(20, 20, 85, 23);

        btnSave.setBackground(new java.awt.Color(128, 165, 220));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 102, 51));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave);
        btnSave.setBounds(20, 50, 85, 23);

        btnDetele.setBackground(new java.awt.Color(126, 174, 206));
        btnDetele.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDetele.setForeground(new java.awt.Color(255, 102, 51));
        btnDetele.setText("Detele");
        btnDetele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeteleActionPerformed(evt);
            }
        });
        jPanel1.add(btnDetele);
        btnDetele.setBounds(20, 80, 85, 23);

        btnFind.setBackground(new java.awt.Color(122, 172, 206));
        btnFind.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFind.setForeground(new java.awt.Color(255, 102, 51));
        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        jPanel1.add(btnFind);
        btnFind.setBounds(20, 110, 85, 23);

        btnOpen.setBackground(new java.awt.Color(129, 174, 204));
        btnOpen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOpen.setForeground(new java.awt.Color(255, 102, 51));
        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpen);
        btnOpen.setBounds(20, 140, 85, 23);

        btnExit.setBackground(new java.awt.Color(134, 175, 203));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 102, 51));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel1.add(btnExit);
        btnExit.setBounds(20, 170, 85, 23);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(410, 40, 127, 240);

        lblTime.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 0, 0));
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("00:00 AM");
        getContentPane().add(lblTime);
        lblTime.setBounds(400, 10, 152, 19);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 153, 255));
        jLabel7.setText("Giới Tính");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 290, 80, 16);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton1.setText("Nam");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(120, 280, 98, 25);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton2.setText("Nữ");
        getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(220, 280, 98, 25);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/carstoremanager/winner-x-moi-9689-1598190940.png"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, -170, 660, 760);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        ClockThread clockThread = new ClockThread(lblTime);
//        Thread t1 = new Thread(clockThread);
//        t1.start();
//        list.add(new Nhan_Vien("NV01", "Hong Son", "sonphh2323@gmail.com", 4000, 19));
//        list.add(new Nhan_Vien("NV02", "Huu Tinh", "tinhnh1212@gmail.com", 5000, 20));
//        list.add(new Nhan_Vien("NV03", "Hoang Thinh", "thinhbh4953@gmail.com", 2000, 30));
//        list.add(new Nhan_Vien("NV04", "Hoang Nhut", "nhutnh0000@gmail.com", 6000, 18));
//        list.add(new Nhan_Vien("NV05", "Nhat Truong", "truongdn5656@gmail.com", 7500, 19));
        initTable();
           fillToTable();
        lblBangGhi.setText(layThongTinBangGhi());
    }//GEN-LAST:event_formWindowOpened

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        themNV();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        index = tblNhanVien.getSelectedRow();
        fillNhanVienLenFrom(index);
        lblBangGhi.setText(layThongTinBangGhi());
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnDeteleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeteleActionPerformed
        // TODO add your handling code here:
        XoaNV();
    }//GEN-LAST:event_btnDeteleActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        savaFive();
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        if (timTheoMa(txtMaNV.getText()) == null) {
            JOptionPane.showMessageDialog(this, "Khong Tim Thay Nhan Vien");
        } else {
            fillNhanVienLenFrom(timTheoMa(txtMaNV.getText()));
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnFistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFistActionPerformed
        // TODO add your handling code here:
        fistNV();
    }//GEN-LAST:event_btnFistActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        lastNV();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        preNV();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        nextNV();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearFrom();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        readFive();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeeManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetele;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFist;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBangGhi;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtTuoi;
    // End of variables declaration//GEN-END:variables



}
